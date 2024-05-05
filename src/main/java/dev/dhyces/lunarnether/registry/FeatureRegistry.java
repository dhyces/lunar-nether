package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.worldgen.feature.Crater;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, LunarNether.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRATER = FEATURES.register("crater", () -> new Crater(NoneFeatureConfiguration.CODEC));


}
