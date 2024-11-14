package dev.dhyces.lunarnether.region;

import com.mojang.datafixers.util.Pair;
import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.BiomeKeys;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class MoonRegion extends Region {
    public MoonRegion() {
        super(LunarNether.id("moon"), RegionType.NETHER, 1000);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        builder.add(
                new Climate.ParameterPoint(
                        Climate.Parameter.span(-2, 2),
                        Climate.Parameter.span(-2, 2),
                        Climate.Parameter.span(0.75f, 2),
                        Climate.Parameter.span(-2, 2),
                        Climate.Parameter.span(-2, 2),
                        Climate.Parameter.span(-2, 2),
                        0
                ),
                BiomeKeys.OUTROCKS
        );

        builder.build().forEach(mapper);
    }
}
