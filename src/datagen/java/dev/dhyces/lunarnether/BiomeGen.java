package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.particle.ColorRangeParticleOption;
import dev.dhyces.lunarnether.registry.BiomeKeys;
import dev.dhyces.lunarnether.registry.ModParticleTypes;
import dev.dhyces.lunarnether.util.ColorUtil;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.*;

public class BiomeGen {
    static void run(BootstapContext<Biome> context) {
        context.register(BiomeKeys.OUTROCKS, outrocks());
        context.register(BiomeKeys.ASHEN_PLAINS, ashenPlains());
        context.register(BiomeKeys.ICY_BARRENS, icyBarrens());
    }

    private static Biome outrocks() {
        BiomeGenerationSettings genSettings = new BiomeGenerationSettings.PlainBuilder()
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

    private static Biome ashenPlains() {
        BiomeGenerationSettings genSettings = new BiomeGenerationSettings.PlainBuilder()
                .build();
        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .skyColor(0)
                .fogColor(0x222222)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .ambientParticle(new AmbientParticleSettings(new ColorRangeParticleOption(ModParticleTypes.COLORED_ASH.get(), 0x111111, 0x666666, ColorUtil.ColorSpace.RGB), 0.12f))
                .build();
        MobSpawnSettings spawnSettings = MobSpawnSettings.EMPTY;

        return new Biome.BiomeBuilder()
                .downfall(0)
                .hasPrecipitation(false)
                .temperature(0.6f)
                .generationSettings(genSettings)
                .specialEffects(specialEffects)
                .mobSpawnSettings(spawnSettings)
                .build();
    }

    private static Biome icyBarrens() {
        BiomeGenerationSettings genSettings = new BiomeGenerationSettings.PlainBuilder()
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
                .temperature(0.3f)
                .generationSettings(genSettings)
                .specialEffects(specialEffects)
                .mobSpawnSettings(spawnSettings)
                .build();
    }
}
