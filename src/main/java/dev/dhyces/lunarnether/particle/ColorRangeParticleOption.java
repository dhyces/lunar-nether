package dev.dhyces.lunarnether.particle;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dhyces.lunarnether.util.ColorUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;

import java.util.Optional;

public class ColorRangeParticleOption implements ParticleOptions {
    public static MapCodec<ColorRangeParticleOption> codec(ParticleType<ColorRangeParticleOption> type) {
        return RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        ColorUtil.HEX_COLOR.fieldOf("min").forGetter(option -> option.minRgbColor),
                        ColorUtil.HEX_COLOR.fieldOf("max").forGetter(option -> option.maxRgbColor),
                        ColorUtil.ColorSpace.CODEC.optionalFieldOf("color_space", ColorUtil.ColorSpace.RGB).forGetter(option -> option.colorSpace)
                ).apply(instance, (integer, integer2, colorSpace1) -> new ColorRangeParticleOption(type, integer, integer2, colorSpace1)));
    }

    public static StreamCodec<RegistryFriendlyByteBuf, ColorRangeParticleOption> streamCodec(ParticleType<ColorRangeParticleOption> type) {
        return StreamCodec.composite(
                ByteBufCodecs.VAR_INT, colorRangeParticleOption -> colorRangeParticleOption.minRgbColor,
                ByteBufCodecs.VAR_INT, colorRangeParticleOption -> colorRangeParticleOption.maxRgbColor,
                ByteBufCodecs.optional(ColorUtil.ColorSpace.STREAM_CODEC), colorRangeParticleOption -> Optional.ofNullable(colorRangeParticleOption.colorSpace == ColorUtil.ColorSpace.RGB ? null : colorRangeParticleOption.colorSpace),
                (min, max, colorSpace) -> new ColorRangeParticleOption(type, min, max, colorSpace.orElse(ColorUtil.ColorSpace.RGB))
        );
    }

    private final ParticleType<ColorRangeParticleOption> particleType;
    private final int minRgbColor;
    private final int maxRgbColor;
    private final ColorUtil.ColorSpace colorSpace;

    public ColorRangeParticleOption(ParticleType<ColorRangeParticleOption> particleType, int minRgbColor, int maxRgbColor, ColorUtil.ColorSpace colorSpace) {
        this.particleType = particleType;
        this.minRgbColor = minRgbColor;
        this.maxRgbColor = maxRgbColor;
        this.colorSpace = colorSpace;
    }

    public int interpolate(RandomSource randomSource) {
        return colorSpace.interpolate(minRgbColor, maxRgbColor, randomSource.nextFloat());
    }

    @Override
    public ParticleType<?> getType() {
        return particleType;
    }
}
