package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.particle.ColorRangeParticleOption;
import dev.dhyces.lunarnether.registry.BiomeKeys;
import dev.dhyces.lunarnether.registry.ModParticleTypes;
import dev.dhyces.lunarnether.util.ColorUtil;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BiomeGen {
    static void run(BootstapContext<Biome> context) {
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
        context.register(BiomeKeys.OUTROCKS, outrocks(placedFeatureGetter, carverGetter));
    }

    private static Biome outrocks(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings genSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeaturesGen.BASALT_MOUND)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeaturesGen.OBSIDIAN_MOUND)
                .build();
        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .skyColor(0)
                .fogColor(0xFFFFFF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .build();
        MobSpawnSettings spawnSettings = MobSpawnSettings.EMPTY;

        return new Biome.BiomeBuilder()
                .downfall(0)
                .hasPrecipitation(false)
                .temperature(0.4f)
                .generationSettings(genSettings)
                .specialEffects(specialEffects)
                .mobSpawnSettings(spawnSettings)
                .build();
    }



}
