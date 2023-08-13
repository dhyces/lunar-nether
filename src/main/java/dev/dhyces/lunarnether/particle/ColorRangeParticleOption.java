package dev.dhyces.lunarnether.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dhyces.lunarnether.util.ColorUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;

public class ColorRangeParticleOption implements ParticleOptions {
    public static final Deserializer<ColorRangeParticleOption> DESERIALIZER = new Deserializer<>() {
        @Override
        public ColorRangeParticleOption fromCommand(ParticleType<ColorRangeParticleOption> pParticleType, StringReader pReader) throws CommandSyntaxException {
            pReader.expect(' ');
            return new ColorRangeParticleOption(pParticleType, Integer.decode(pReader.readStringUntil(' ')), Integer.decode(pReader.readStringUntil(' ')), ColorUtil.ColorSpace.CODEC.parse(NbtOps.INSTANCE, StringTag.valueOf(pReader.readString())).getOrThrow(false, s -> {}));
        }

        @Override
        public ColorRangeParticleOption fromNetwork(ParticleType<ColorRangeParticleOption> pParticleType, FriendlyByteBuf pBuffer) {
            return new ColorRangeParticleOption(pParticleType, pBuffer.readInt(), pBuffer.readInt(), pBuffer.readEnum(ColorUtil.ColorSpace.class));
        }
    };

    public static Codec<ColorRangeParticleOption> codec(ParticleType<ColorRangeParticleOption> type) {
        return RecordCodecBuilder.create(instance ->
                instance.group(
                        ColorUtil.HEX_COLOR.fieldOf("min").forGetter(option -> option.minRgbColor),
                        ColorUtil.HEX_COLOR.fieldOf("max").forGetter(option -> option.maxRgbColor),
                        ColorUtil.ColorSpace.CODEC.optionalFieldOf("color_space", ColorUtil.ColorSpace.RGB).forGetter(option -> option.colorSpace)
                ).apply(instance, (integer, integer2, colorSpace1) -> new ColorRangeParticleOption(type, integer, integer2, colorSpace1)));
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

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(minRgbColor);
        pBuffer.writeInt(maxRgbColor);
    }

    @Override
    public String writeToString() {
        String min = "[%s,%s,%s]".formatted(FastColor.ARGB32.red(minRgbColor), FastColor.ARGB32.green(minRgbColor), FastColor.ARGB32.blue(minRgbColor));
        String max = "[%s,%s,%s]".formatted(FastColor.ARGB32.red(maxRgbColor), FastColor.ARGB32.green(maxRgbColor), FastColor.ARGB32.blue(maxRgbColor));
        return min + " " + max + " " + colorSpace.getSerializedName();
    }
}
