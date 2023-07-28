package dev.dhyces.lunarnether.registry;

import com.mojang.serialization.Codec;
import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.worldgen.HeightedBiomeSource;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeSources {
    public static final DeferredRegister<Codec<? extends BiomeSource>> REGISTRY = DeferredRegister.create(Registries.BIOME_SOURCE, LunarNether.MODID);

    public static final RegistryObject<Codec<HeightedBiomeSource>> HEIGHTED = REGISTRY.register("heighted", () -> HeightedBiomeSource.CODEC);
}
