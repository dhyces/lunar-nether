package dev.dhyces.lunarnether.worldgen.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.BiomeModifierTypes;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.Map;

public record AddCarverBiomeModifier(Map<GenerationStep.Carving, HolderSet<ConfiguredWorldCarver<?>>> carvers, HolderSet<Biome> biomes) implements BiomeModifier {
    public static final Codec<AddCarverBiomeModifier> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.simpleMap(GenerationStep.Carving.CODEC, ConfiguredWorldCarver.LIST_CODEC.promotePartial(Util.prefix("Carvers: ", LunarNether.LOGGER::error)), StringRepresentable.keys(GenerationStep.Carving.values())).fieldOf("carvers").forGetter(AddCarverBiomeModifier::carvers),
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddCarverBiomeModifier::biomes)
            ).apply(instance, AddCarverBiomeModifier::new)
    );

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            if (biomes.contains(biome)) {
                carvers.forEach((carving, holders) -> {
                    holders.stream().forEach(holder -> {
                        builder.getGenerationSettings().addCarver(carving, holder);
                    });
                });
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return BiomeModifierTypes.ADD_CARVER.get();
    }
}
