package dev.dhyces.lunarnether;

import dev.dhyces.biomeextensions.RegistryHelper;
import dev.dhyces.biomeextensions.extension.BiomeExtension;
import dev.dhyces.biomeextensions.extension.effects.SkyFogExtension;
import dev.dhyces.biomeextensions.extension.effects.TerrainFogExtension;
import dev.dhyces.lunarnether.registry.BiomeKeys;
import net.minecraft.data.worldgen.BootstapContext;

public class BiomeExtensionGen {
    static void run(BootstapContext<BiomeExtension> context) {
        BiomeExtension surfaceFogModifier = BiomeExtension.builder()
                .add(new TerrainFogExtension(0, 800))
                .add(new SkyFogExtension(0, 800))
                .build();
        BiomeExtension ashenPlains = BiomeExtension.single(new TerrainFogExtension(0, 100));
        context.register(RegistryHelper.registryKey(BiomeKeys.OUTROCKS.location()), surfaceFogModifier);
        context.register(RegistryHelper.registryKey(BiomeKeys.ASHEN_PLAINS.location()), ashenPlains);
        context.register(RegistryHelper.registryKey(BiomeKeys.ICY_BARRENS.location()), surfaceFogModifier);
    }
}
