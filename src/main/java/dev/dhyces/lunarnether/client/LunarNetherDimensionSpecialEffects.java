package dev.dhyces.lunarnether.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.LunarNetherClient;
import dev.dhyces.lunarnether.util.ColorUtil;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class LunarNetherDimensionSpecialEffects extends DimensionSpecialEffects {
    public static final ResourceLocation EARTH_LOCATION = LunarNether.id("textures/environment/overworld_phases.png");
    public static final ResourceLocation EARTH_GLOW_LOCATION = LunarNether.id("textures/environment/overworld_glow.png");
    private static VertexBuffer skyBuffer;
    private static VertexBuffer starsBuffer;

    static {
        setup();
    }

    static void setup() {
        skyBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
        starsBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);

        Tesselator tesselator = Tesselator.getInstance();
        
        skyBuffer.bind();
        skyBuffer.upload(drawSky(tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION)));
        VertexBuffer.unbind();

        starsBuffer.bind();
        starsBuffer.upload(drawStars(tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR)));
        VertexBuffer.unbind();
    }

    public LunarNetherDimensionSpecialEffects() {
        super(Float.NaN, true, DimensionSpecialEffects.SkyType.NORMAL, false, true);
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
    public float @Nullable [] getSunriseColor(float pTimeOfDay, float pPartialTicks) {
        return null;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        if (camera.getPosition().y < 128) {
            return false;
        }

        Tesselator tesselator = Tesselator.getInstance();

        PoseStack poseStack = new PoseStack();
        
        // setup for sun and stars
        poseStack.pushPose();
        poseStack.mulPose(modelViewMatrix);
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
        // rotate for time of day
        poseStack.mulPose(Axis.XP.rotationDegrees(LunarNether.netherTimeOfDay(level.dayTime()) * 360.0F));

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
        RenderSystem.setShaderTexture(0, LevelRenderer.SUN_LOCATION);
        Matrix4f sunPose = poseStack.last().pose();
        BufferBuilder builder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        builder.addVertex(sunPose, -sunSize, 100, -sunSize).setUv(0, 0);
        builder.addVertex(sunPose, sunSize, 100, -sunSize).setUv(1, 0);
        builder.addVertex(sunPose, sunSize, 100, sunSize).setUv(1, 1);
        builder.addVertex(sunPose, -sunSize, 100, sunSize).setUv(0, 1);
        BufferUploader.drawWithShader(builder.buildOrThrow());

        // pop sky rotation
        poseStack.popPose();

        // setup for earth
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
        //probably how many degrees up from the west is it, 0 is below you.
        poseStack.mulPose(Axis.XP.rotationDegrees(150.0F));

        // render earth
        float earthSize = 30f;
        int phase = (int)(level.dayTime() / 24000L % 8L + 8L) % 8;
        int x = phase % 4;
        int y = phase / 4 % 2;
        float minU = (float) (x) / 4.0F;
        float minV = (float) (y) / 2.0F;
        float maxU = (float) (x + 1) / 4.0F;
        float maxV = (float) (y + 1) / 2.0F;
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, EARTH_LOCATION);
        Matrix4f earthPose = poseStack.last().pose();
        builder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        builder.addVertex(earthPose, -earthSize, -100, earthSize).setUv(maxU, maxV);
        builder.addVertex(earthPose, earthSize, -100, earthSize).setUv(minU, maxV);
        builder.addVertex(earthPose, earthSize, -100, -earthSize).setUv(minU, minV);
        builder.addVertex(earthPose, -earthSize, -100, -earthSize).setUv(maxU, minV);
        BufferUploader.drawWithShader(builder.buildOrThrow());

        int eclipsePhase = LunarNetherClient.eclipsePhase(LunarNetherClient.eclipse(), LunarNetherClient.eclipseSlope() < 0);
        int eclipseX = eclipsePhase % 4;
        int eclipseY = eclipsePhase / 4 % 2;
        float eclipseMinU = (float) (eclipseX) / 4.0F;
        float eclipseMinV = (float) (eclipseY) / 2.0F;
        float eclipseMaxU = (float) (eclipseX + 1) / 4.0F;
        float eclipseMaxV = (float) (eclipseY + 1) / 2.0F;
        RenderSystem.setShaderTexture(0, EARTH_GLOW_LOCATION);
        builder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        builder.addVertex(earthPose, -earthSize, -100, earthSize).setUv(eclipseMaxU, eclipseMaxV);
        builder.addVertex(earthPose, earthSize, -100, earthSize).setUv(eclipseMinU, eclipseMaxV);
        builder.addVertex(earthPose, earthSize, -100, -earthSize).setUv(eclipseMinU, eclipseMinV);
        builder.addVertex(earthPose, -earthSize, -100, -earthSize).setUv(eclipseMaxU, eclipseMinV);
        BufferUploader.drawWithShader(builder.buildOrThrow());

        // pop earth position
        poseStack.popPose();
        poseStack.popPose();
        return true;
    }

    private static MeshData drawSky(BufferBuilder builder) {
        int distance = 2000;

        // draw a black cube instead of the default sky
        builder.addVertex(-distance, -distance, -distance);
        builder.addVertex(-distance, -distance, distance);
        builder.addVertex(distance, -distance, distance);
        builder.addVertex(distance, -distance, -distance);

        builder.addVertex(-distance, distance, -distance);
        builder.addVertex(-distance, -distance, -distance);
        builder.addVertex(distance, -distance, -distance);
        builder.addVertex(distance, distance, -distance);

        builder.addVertex(-distance, -distance, distance);
        builder.addVertex(-distance, distance, distance);
        builder.addVertex(distance, distance, distance);
        builder.addVertex(distance, -distance, distance);

        builder.addVertex(distance, -distance, -distance);
        builder.addVertex(distance, -distance, distance);
        builder.addVertex(distance, distance, distance);
        builder.addVertex(distance, distance, -distance);

        builder.addVertex(-distance, -distance, distance);
        builder.addVertex(-distance, -distance, -distance);
        builder.addVertex(-distance, distance, -distance);
        builder.addVertex(-distance, distance, distance);

        builder.addVertex(-distance, distance, distance);
        builder.addVertex(-distance, distance, -distance);
        builder.addVertex(distance, distance, -distance);
        builder.addVertex(distance, distance, distance);

        return builder.build();
    }

    private static MeshData drawStars(BufferBuilder builder) {
        int distance = 500;

        RandomSource random = RandomSource.create(0xAAAAA);
        // copied from vanilla, no clue what all these variables are
        for(int i = 0; i < 4500; ++i) {
            Vector3f vec = new Vector3f(random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1);
            double size = 0.15F + random.nextFloat() * 0.1F;
            size *= (distance / 100F);
            float lenSquared = vec.lengthSquared();
            if (lenSquared < 1.0f && lenSquared > 0.01f) {
                lenSquared = 1.0f / Mth.sqrt(lenSquared);
                vec.mul(lenSquared);
                Vector3f scaledVec = new Vector3f(vec).mul(distance);
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
                    float d23 = (float) (d21 * d12);
                    double d24 = -d21 * d13;
                    float d25 = (float) (d24 * d9 - d22 * d10);
                    float d26 = (float) (d22 * d9 + d24 * d10);
                    builder.addVertex(scaledVec.x + d25, scaledVec.y + d23, scaledVec.z + d26).setColor(color);
                }
            }
        }

        return builder.build();
    }
}
