package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.worldgen.feature.Crater;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PlacedFeaturesGen {
    public static final ResourceKey<PlacedFeature> BASALT_MOUND = key("basalt_mound");
    public static final ResourceKey<PlacedFeature> OBSIDIAN_MOUND = key("obsidian_mound");

    public static final ResourceKey<PlacedFeature> CRATER = key("crater");


    static void run(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(BASALT_MOUND, new PlacedFeature(getter.getOrThrow(ConfiguredFeaturesGen.BASALT_MOUND), List.of(CountPlacement.of(10), RandomOffsetPlacement.horizontal(UniformInt.of(0, 8)), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING), BiomeFilter.biome())));
        context.register(OBSIDIAN_MOUND, new PlacedFeature(getter.getOrThrow(ConfiguredFeaturesGen.OBSIDIAN_MOUND), List.of(CountPlacement.of(10), RandomOffsetPlacement.horizontal(UniformInt.of(0, 8)), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING), BiomeFilter.biome())));
        context.register(CRATER, new PlacedFeature(getter.getOrThrow(ConfiguredFeaturesGen.CRATER), List.of(CountPlacement.of(10), RandomOffsetPlacement.horizontal(UniformInt.of(0, 8)), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING), BiomeFilter.biome())));
    }


    private static ResourceKey<PlacedFeature> key(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, LunarNether.id(id));
    }
}
