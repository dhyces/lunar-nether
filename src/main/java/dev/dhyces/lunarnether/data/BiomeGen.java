package dev.dhyces.lunarnether.data;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

public class BiomeGen {
    public static final ResourceKey<Biome> OUTROCKS = key("outrocks");
    public static final ResourceKey<Biome> ASHEN_PLAINS = key("ashen_plains");
    public static final ResourceKey<Biome> ICY_BARRENS = key("icy_barrens");


    static void run(BootstapContext<Biome> context) {
        context.register(ASHEN_PLAINS, ashenPlains());
    }

    private static Biome outrocks() {
        BiomeGenerationSettings genSettings = new BiomeGenerationSettings.PlainBuilder()
                .build();
        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .skyColor(0)
                .fogColor(0x333333)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .build();
        MobSpawnSettings spawnSettings = MobSpawnSettings.EMPTY;

        return new Biome.BiomeBuilder()
                .downfall(0)
                .hasPrecipitation(false)
                .temperature(0.1f)
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
                .fogColor(0x333333)
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

    private static Biome icyBarrens() {
        BiomeGenerationSettings genSettings = new BiomeGenerationSettings.PlainBuilder()
                .build();
        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .skyColor(0)
                .fogColor(0x333333)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .build();
        MobSpawnSettings spawnSettings = MobSpawnSettings.EMPTY;

        return new Biome.BiomeBuilder()
                .downfall(0)
                .hasPrecipitation(false)
                .temperature(0f)
                .generationSettings(genSettings)
                .specialEffects(specialEffects)
                .mobSpawnSettings(spawnSettings)
                .build();
    }

    private static ResourceKey<Biome> key(String id) {
        return ResourceKey.create(Registries.BIOME, LunarNether.id(id));
    }
}
