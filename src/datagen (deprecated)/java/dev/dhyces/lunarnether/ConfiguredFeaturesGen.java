package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.worldgen.feature.Crater;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ConfiguredFeaturesGen {
    public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_MOUND = key("basalt_mound");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OBSIDIAN_MOUND = key("obsidian_mound");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ILMENITE_ORE_KEY = key("ore_ilmenite");



    static void run(BootstapContext<ConfiguredFeature<?, ?>> pContext) {
        pContext.register(BASALT_MOUND, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.BASALT), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.BASALT), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0)).dirt(BlockStateProvider.simple(Blocks.BASALT)).build()));
        pContext.register(OBSIDIAN_MOUND, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OBSIDIAN), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.OBSIDIAN), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0)).dirt(BlockStateProvider.simple(Blocks.OBSIDIAN)).build()));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, LunarNether.id(id));
    }
}
