package dev.dhyces.lunarnether;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import dev.dhyces.lunarnether.client.particle.ColoredAshParticle;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.registry.ModParticleTypes;
import dev.dhyces.lunarnether.server.LunarTimeData;
import dev.dhyces.lunarnether.util.ColorUtil;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;
import org.joml.*;

import java.lang.Math;

public final class LunarNetherClient {
    private static final ResourceLocation SUN_LOCATION = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation EARTH_LOCATION = LunarNether.id("textures/environment/overworld_phases.png");

    /**
     * A separate time value for the nether which controls light and sky rendering.
     * Increases 8 times slower than the normal overworld daytime.
     */
    public static long netherDayTime = 0;

    static void register(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(LunarNetherClient::registerItemProperties);
        modBus.addListener(LunarNetherClient::registerParticles);
        modBus.addListener(LunarNetherClient::netherSky);

    }

    private static void registerItemProperties(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.LUNAR_CLOCK.get(), LunarNether.id("moon_phase"), (pStack, pLevel, pEntity, pSeed) ->
                pLevel == null ? 0 : pLevel.getMoonPhase() / 8f
            );
        });
    }

    private static void registerParticles(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.COLORED_ASH.get(), ColoredAshParticle.Provider::new);
    }

    private static void netherSky(final RegisterDimensionSpecialEffectsEvent event) {
        event.register(BuiltinDimensionTypes.NETHER_EFFECTS, new DimensionSpecialEffects(Float.NaN, true, DimensionSpecialEffects.SkyType.NORMAL, false, true) {
            private static VertexBuffer skyBuffer;
            private static VertexBuffer starsBuffer;

            static {
                setup();
            }

            static void setup() {
                skyBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                starsBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);

                BufferBuilder builder = Tesselator.getInstance().getBuilder();

                skyBuffer.bind();
                skyBuffer.upload(drawSky(builder));
                VertexBuffer.unbind();

                starsBuffer.bind();
                starsBuffer.upload(drawStars(builder));
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

            @Nullable
            @Override
            public float[] getSunriseColor(float pTimeOfDay, float pPartialTicks) {
                return null;
            }

            @Override
            public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
                if (camera.getPosition().y < 128) {
                    return false;
                }

                // render sky
                RenderSystem.setShader(GameRenderer::getPositionShader);
                ShaderInstance posShader = RenderSystem.getShader();
                if (posShader != null) {
                    RenderSystem.setShaderColor(0, 0, 0, 1);
                    skyBuffer.bind();
                    skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, posShader);
                    VertexBuffer.unbind();
                    RenderSystem.setShaderColor(1, 1, 1, 1);
                }

                Tesselator tesselator = Tesselator.getInstance();
                BufferBuilder builder = tesselator.getBuilder();

                // setup for sun and stars
                poseStack.pushPose();
                poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                // rotate for time of day
                poseStack.mulPose(Axis.XP.rotationDegrees(LunarTimeData.netherTimeOfDay(netherDayTime) * 360.0F));

                // render stars
                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                ShaderInstance posColorShader = RenderSystem.getShader();
                if (posColorShader != null) {
                    starsBuffer.bind();
                    starsBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, posColorShader);
                    VertexBuffer.unbind();
                }

                // render sun
                float sunSize = 30f;
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, SUN_LOCATION);
                Matrix4f sunPose = poseStack.last().pose();
                builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                builder.vertex(sunPose, -sunSize, 101, -sunSize).uv(0, 0).endVertex();
                builder.vertex(sunPose, sunSize, 101, -sunSize).uv(1, 0).endVertex();
                builder.vertex(sunPose, sunSize, 101, sunSize).uv(1, 1).endVertex();
                builder.vertex(sunPose, -sunSize, 101, sunSize).uv(0, 1).endVertex();
                tesselator.end();

                // pop sky rotation
                poseStack.popPose();

                // setup for earth
                poseStack.pushPose();
                //probably something to do with making it appear in the west instead of the north or south -90 = west.
                poseStack.mulPose(Axis.YP.rotationDegrees(-75));
                //probably how many degrees up from the west is it, 0 is below you.
                poseStack.mulPose(Axis.XP.rotationDegrees(150.0F));

                // render earth
                float earthSize = 20f;
                int phase = (int)(netherDayTime / 24000L % 8L + 8L) % 8;
                int x = phase % 4;
                int y = phase / 4 % 2;
                float minU = (float) (x) / 4.0F;
                float minV = (float) (y) / 2.0F;
                float maxU = (float) (x + 1) / 4.0F;
                float maxV = (float) (y + 1) / 2.0F;
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderTexture(0, EARTH_LOCATION);
                Matrix4f earthPose = poseStack.last().pose();
                builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                builder.vertex(earthPose, -earthSize, -100, earthSize).uv(maxU, maxV).endVertex();
                builder.vertex(earthPose, earthSize, -100, earthSize).uv(minU, maxV).endVertex();
                builder.vertex(earthPose, earthSize, -100, -earthSize).uv(minU, minV).endVertex();
                builder.vertex(earthPose, -earthSize, -100, -earthSize).uv(maxU, minV).endVertex();
                tesselator.end();

                // pop earth position
                poseStack.popPose();
                return true;
            }
        });
    }

    private static BufferBuilder.RenderedBuffer drawSky(BufferBuilder builder) {
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        int distance = 2000;

        // draw a black cube instead of the default sky
        builder.vertex(-distance, -distance, -distance).endVertex();
        builder.vertex(-distance, -distance, distance).endVertex();
        builder.vertex(distance, -distance, distance).endVertex();
        builder.vertex(distance, -distance, -distance).endVertex();

        builder.vertex(-distance, distance, -distance).endVertex();
        builder.vertex(-distance, -distance, -distance).endVertex();
        builder.vertex(distance, -distance, -distance).endVertex();
        builder.vertex(distance, distance, -distance).endVertex();

        builder.vertex(-distance, -distance, distance).endVertex();
        builder.vertex(-distance, distance, distance).endVertex();
        builder.vertex(distance, distance, distance).endVertex();
        builder.vertex(distance, -distance, distance).endVertex();

        builder.vertex(distance, -distance, -distance).endVertex();
        builder.vertex(distance, -distance, distance).endVertex();
        builder.vertex(distance, distance, distance).endVertex();
        builder.vertex(distance, distance, -distance).endVertex();

        builder.vertex(-distance, -distance, distance).endVertex();
        builder.vertex(-distance, -distance, -distance).endVertex();
        builder.vertex(-distance, distance, -distance).endVertex();
        builder.vertex(-distance, distance, distance).endVertex();

        builder.vertex(-distance, distance, distance).endVertex();
        builder.vertex(-distance, distance, -distance).endVertex();
        builder.vertex(distance, distance, -distance).endVertex();
        builder.vertex(distance, distance, distance).endVertex();

        return builder.end();
    }

    private static BufferBuilder.RenderedBuffer drawStars(BufferBuilder builder) {
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        int distance = 1000;

        RandomSource random = RandomSource.create(0xAAAAA);
        // copied from vanilla, no clue what all these variables are
        for(int i = 0; i < 4500; ++i) {
            Vector3d vec = new Vector3d(random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1);
            double size = 0.15F + random.nextFloat() * 0.1F;
            size *= (distance / 100F);
            double lenSquared = vec.lengthSquared();
            if (lenSquared < 1.0D && lenSquared > 0.01D) {
                lenSquared = 1.0D / Math.sqrt(lenSquared);
                vec.mul(lenSquared);
                Vector3d scaledVec = new Vector3d(vec).mul(distance);
                double d8 = Math.atan2(vec.x, vec.z);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(vec.x * vec.x + vec.z * vec.z), vec.y);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Mth.TWO_PI;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                int color = ColorUtil.getTemperatureColor(random.nextInt(1000, 40000));

                for(int j = 0; j < 4; ++j) {
                    double d18 = (double)((j & 2) - 1) * size;
                    double d19 = (double)((j + 1 & 2) - 1) * size;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12;
                    double d24 = -d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    builder.vertex(scaledVec.x + d25, scaledVec.y + d23, scaledVec.z + d26).color(color).endVertex();
                }
            }
        }

        return builder.end();
    }
}
