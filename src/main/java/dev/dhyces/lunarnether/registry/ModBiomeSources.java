package dev.dhyces.lunarnether.registry;

import com.mojang.serialization.MapCodec;
import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.worldgen.HeightedBiomeSource;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.BiomeSource;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBiomeSources {
    public static final DeferredRegister<MapCodec<? extends BiomeSource>> REGISTRY = DeferredRegister.create(Registries.BIOME_SOURCE, LunarNether.MODID);

    public static final DeferredHolder<MapCodec<? extends BiomeSource>, MapCodec<HeightedBiomeSource>> HEIGHTED = REGISTRY.register("heighted", () -> HeightedBiomeSource.CODEC);
}