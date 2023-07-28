package dev.dhyces.lunarnether;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.util.Mth;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import org.joml.Matrix4f;

public final class LunarNetherClient {
    static void register(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(LunarNetherClient::netherSky);
    }

    private static void netherSky(final RegisterDimensionSpecialEffectsEvent event) {
        event.register(BuiltinDimensionTypes.NETHER_EFFECTS, new DimensionSpecialEffects(Float.NaN, true, DimensionSpecialEffects.SkyType.NORMAL, false, true) {
            private static VertexBuffer topHalfBuffer;
            private static VertexBuffer bottomHalfBuffer;

            static {
                topHalfBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                bottomHalfBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);

                Tesselator tesselator = Tesselator.getInstance();
                BufferBuilder builder = tesselator.getBuilder();

                RenderSystem.setShader(GameRenderer::getPositionShader);
                builder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
                builder.vertex(0, 16, 0).endVertex();

                for (int i = -180; i <= 180; i+=45) {
                    builder.vertex(512f * Mth.cos(i * Mth.DEG_TO_RAD), 16, 512f * Mth.sin(i * Mth.DEG_TO_RAD)).endVertex();
                }

                BufferBuilder.RenderedBuffer renderedBuffer = builder.end();
                topHalfBuffer.bind();
                topHalfBuffer.upload(renderedBuffer);
                VertexBuffer.unbind();

                builder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
                builder.vertex(0, -16, 0).endVertex();

                for (int i = -180; i <= 180; i+=45) {
                    builder.vertex(-512f * Mth.cos(i * Mth.DEG_TO_RAD), -16, 512f * Mth.sin(i * Mth.DEG_TO_RAD)).endVertex();
                }

                renderedBuffer = builder.end();
                bottomHalfBuffer.bind();
                bottomHalfBuffer.upload(renderedBuffer);
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
                RenderSystem.setShader(GameRenderer::getPositionShader);
                ShaderInstance shaderinstance = RenderSystem.getShader();
                RenderSystem.setShaderColor(0, 0, 0, 1);
                poseStack.pushPose();
                topHalfBuffer.bind();
                topHalfBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
                VertexBuffer.unbind();
                bottomHalfBuffer.bind();
                bottomHalfBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
                VertexBuffer.unbind();
                poseStack.popPose();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                return true;
            }
        });
    }
}
