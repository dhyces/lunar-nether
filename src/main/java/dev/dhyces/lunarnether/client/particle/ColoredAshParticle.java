package dev.dhyces.lunarnether.client.particle;

import dev.dhyces.lunarnether.particle.ColorRangeParticleOption;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.RandomSource;

import java.util.function.ToIntFunction;

public class ColoredAshParticle extends BaseAshSmokeParticle {
    protected ColoredAshParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, float pQuadSizeMultiplier, SpriteSet pSprites, ToIntFunction<RandomSource> rgbColorFunc) {
        super(pLevel, pX, pY, pZ, 0.1f, -0.1f, 0.1f, pXSpeed, pYSpeed, pZSpeed, pQuadSizeMultiplier, pSprites, 0, 20, 0.0125f, false);
        int rgbColor = rgbColorFunc.applyAsInt(random);
        this.rCol = ((rgbColor >> 16) & 255) / 255f;
        this.gCol = ((rgbColor >> 8) & 255) / 255f;
        this.bCol = (rgbColor & 255) / 255f;
    }

    public static final class Provider implements ParticleProvider<ColorRangeParticleOption> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        @Override
        public Particle createParticle(ColorRangeParticleOption option, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            RandomSource randomsource = pLevel.random;
            double xSpeed = (double)randomsource.nextFloat() * -1.9D * (double)randomsource.nextFloat() * 0.1D;
            double ySpeed = (double)randomsource.nextFloat() * -0.5D * (double)randomsource.nextFloat() * 0.1D * 5.0D;
            double zSpeed = (double)randomsource.nextFloat() * -1.9D * (double)randomsource.nextFloat() * 0.1D;
            return new ColoredAshParticle(pLevel, pX, pY, pZ, xSpeed, ySpeed, zSpeed, 1, sprites, option::interpolate);
        }
    }
}
