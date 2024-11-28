package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.worldgen.feature.RockFeature;
import dev.dhyces.lunarnether.worldgen.feature.configs.ExtendedBlockStateConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, LunarNether.MODID);

    public static final DeferredHolder<Feature<?>, Feature<ExtendedBlockStateConfiguration>> ROCK = FEATURES.register("rock", () -> new RockFeature(ExtendedBlockStateConfiguration.CODEC));


}
