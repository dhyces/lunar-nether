package dev.dhyces.lunarnether;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import dev.dhyces.lunarnether.client.particle.ColoredAshParticle;
import dev.dhyces.lunarnether.registry.ModParticleTypes;
import dev.dhyces.lunarnether.util.ColorUtil;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import org.joml.*;
import org.lwjgl.opengl.GL11C;

import java.lang.Math;

public final class LunarNetherClient {
    static void register(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(LunarNetherClient::registerParticles);
        modBus.addListener(LunarNetherClient::netherSky);
    }

    private static void registerParticles(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.COLORED_ASH.get(), ColoredAshParticle.Provider::new);
    }

    private static void netherSky(final RegisterDimensionSpecialEffectsEvent event) {
        event.register(BuiltinDimensionTypes.NETHER_EFFECTS, new DimensionSpecialEffects(Float.NaN, true, DimensionSpecialEffects.SkyType.NORMAL, false, true) {
            private static VertexBuffer topHalfBuffer;
            private static VertexBuffer bottomHalfBuffer;
            private static VertexBuffer starsBuffer;

            static {
                topHalfBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                bottomHalfBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                starsBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);

                Tesselator tesselator = Tesselator.getInstance();
                BufferBuilder builder = tesselator.getBuilder();

                RenderSystem.setShader(GameRenderer::getPositionShader);
                builder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
                builder.vertex(0, 16, 0).endVertex();
                // TODO: Fix this
                for (int i = -180; i <= 180; i+=45) {
                    builder.vertex(512f * Mth.cos(i * Mth.DEG_TO_RAD), 16, 512f * Mth.sin(i * Mth.DEG_TO_RAD)).endVertex();
                }

                topHalfBuffer.bind();
                topHalfBuffer.upload(builder.end());
                VertexBuffer.unbind();

                builder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
                builder.vertex(0, -32, 0).endVertex();

                for (int i = -180; i <= 180; i+=45) {
                    builder.vertex(-512f * Mth.cos(i * Mth.DEG_TO_RAD), -32, 512f * Mth.sin(i * Mth.DEG_TO_RAD)).endVertex();
                }

                bottomHalfBuffer.bind();
                bottomHalfBuffer.upload(builder.end());
                VertexBuffer.unbind();

                builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
                RandomSource random = RandomSource.create(0xAAAAA);
                PoseStack poseStack = new PoseStack();
                for (int i = 0; i < 1500; i++) {
                    Vector3d vec = new Vector3d(random.nextFloat() * 2d - 1d, random.nextFloat() * 2d - 1d, random.nextFloat() * 2d - 1d);
                    double lenSquared = vec.lengthSquared();
                    if (lenSquared < 1.0 && lenSquared > 0.01) {
                        vec.mul(1.0 / Math.sqrt(lenSquared)).mul(500);

                        poseStack.pushPose();
                        poseStack.translate(vec.x, vec.y, vec.z);
                        Quaterniond rot = new Vector3d(0, vec.y, 0).rotationTo(vec, new Quaterniond());
                        poseStack.mulPose(rot.get(new Quaternionf()));
                        float scale = 5;
                        poseStack.scale(scale, scale, scale);

                        builder.vertex(poseStack.last().pose(), 0, 0, 0).color(0xFFFFFFFF).endVertex();
                        builder.vertex(poseStack.last().pose(), 1, 0, 0).color(0xFFFFFFFF).endVertex();
                        builder.vertex(poseStack.last().pose(), 1, 0, 1).color(0xFFFFFFFF).endVertex();
                        builder.vertex(poseStack.last().pose(), 0, 0, 1).color(0xFFFFFFFF).endVertex();

                        poseStack.popPose();
                    }
                }

                starsBuffer.bind();
                starsBuffer.upload(builder.end());
                VertexBuffer.unbind();
            }

            @Override
            public Vec3 getBrightnessDependentFogColor(Vec3 pFogColor, float pBrightness) {
                return pFogColor;
            }

            @Override
            public boolean isFoggyAt(int pX, int pY) {
                return pY < 128;
            }

            @Override
            public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
                if (camera.getPosition().y < 128) {
                    return false;
                }
                RenderSystem.depthMask(false);
                RenderSystem.setShader(GameRenderer::getPositionShader);
                ShaderInstance shaderinstance = RenderSystem.getShader();
                RenderSystem.setShaderColor(0, 0, 0, 1);

                poseStack.pushPose();

                poseStack.pushPose();
                topHalfBuffer.bind();
                topHalfBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
                VertexBuffer.unbind();
                bottomHalfBuffer.bind();
                bottomHalfBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
                VertexBuffer.unbind();
                poseStack.popPose();

                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                RenderSystem.setShaderColor(1, 1, 1, 1);
                Tesselator tesselator = Tesselator.getInstance();
                BufferBuilder builder = tesselator.getBuilder();

                poseStack.pushPose();
                builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
                RandomSource random = RandomSource.create(0xAAAAA);

                for (int i = 0; i < 1500; i++) {
                    Vector3d vec = new Vector3d(random.nextFloat() * 2d - 1d, random.nextFloat() * 2d - 1d, random.nextFloat() * 2d - 1d);
                    double lenSquared = vec.lengthSquared();
                    if (lenSquared < 1.0 && lenSquared > 0.01) {
                        vec.mul(1.0 / Math.sqrt(lenSquared)).mul(500);

                        poseStack.pushPose();
                        poseStack.translate(vec.x, vec.y, vec.z);
                        Quaterniond rot = new Vector3d(0, vec.y, 0).rotationTo(vec, new Quaterniond());
                        poseStack.mulPose(rot.get(new Quaternionf()));
                        float scale = (random.nextInt(10)+1) % 5;
                        poseStack.scale(scale, scale, scale);
                        int color = ColorUtil.getTemperatureColor(random.nextInt(1000, 40000));

                        builder.vertex(poseStack.last().pose(), 0, 5, 0).color(color).endVertex();
                        builder.vertex(poseStack.last().pose(), 1, 5, 0).color(color).endVertex();
                        builder.vertex(poseStack.last().pose(), 1, 5, 1).color(color).endVertex();
                        builder.vertex(poseStack.last().pose(), 0, 5, 1).color(color).endVertex();

                        poseStack.popPose();
                    }
                }
                tesselator.end();
                poseStack.popPose();

                poseStack.pushPose();
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, LunarNether.id("textures/environment/overworld_phases_glow.png"));

                float size = 20f;
                poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                poseStack.translate(0, 99, 0);
                Matrix4f pose = poseStack.last().pose();
                builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                builder.vertex(pose, -size, 1, -size).uv(0, 0).endVertex();
                builder.vertex(pose, size, 1, -size).uv(1, 0).endVertex();
                builder.vertex(pose, size, 1, size).uv(1, 1).endVertex();
                builder.vertex(pose, -size, 1, size).uv(0, 1).endVertex();
                tesselator.end();

                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, LunarNether.id("textures/environment/overworld_phases.png"));

                float uvWidth = 32f / 128f;
                float uvHeight = 32f / 64f;
                builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                builder.vertex(pose, -size, 1, -size).uv(0, 0).endVertex();
                builder.vertex(pose, size, 1, -size).uv(uvWidth, 0).endVertex();
                builder.vertex(pose, size, 1, size).uv(uvWidth, uvHeight).endVertex();
                builder.vertex(pose, -size, 1, size).uv(0, uvHeight).endVertex();
                tesselator.end();
                poseStack.popPose();

                poseStack.popPose();
                RenderSystem.depthMask(true);
                return true;
            }
        });
    }
}
