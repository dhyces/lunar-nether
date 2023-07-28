package dev.dhyces.lunarnether.data;

import com.mojang.blaze3d.shaders.FogShape;
import dev.dhyces.biomeextensions.RegistryHelper;
import dev.dhyces.biomeextensions.extension.BiomeExtension;
import dev.dhyces.biomeextensions.extension.effects.FogExtension;
import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;

import java.util.Optional;

public class BiomeExtensionGen {
    static void run(BootstapContext<BiomeExtension> context) {
        BiomeExtension surfaceFogModifier = BiomeExtension.single(new FogExtension(0, 800, Float.NaN, Optional.of(FogShape.CYLINDER)));
        context.register(RegistryHelper.registryKey(BiomeGen.OUTROCKS.location()), surfaceFogModifier);
        context.register(RegistryHelper.registryKey(BiomeGen.ASHEN_PLAINS.location()), surfaceFogModifier);
        context.register(RegistryHelper.registryKey(BiomeGen.ICY_BARRENS.location()), surfaceFogModifier);
    }

    private static ResourceKey<BiomeExtension> key(String id) {
        return RegistryHelper.registryKey(LunarNether.id(id));
    }
}
