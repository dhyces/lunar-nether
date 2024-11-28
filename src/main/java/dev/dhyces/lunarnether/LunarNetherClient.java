package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.client.LunarNetherDimensionSpecialEffects;
import dev.dhyces.lunarnether.client.particle.ColoredAshParticle;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.registry.ModParticleTypes;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.lang.Math;

@Mod(value = LunarNether.MODID, dist = Dist.CLIENT)
public final class LunarNetherClient {
    private static final ResourceLocation SUN_LOCATION = ResourceLocation.withDefaultNamespace("textures/environment/sun.png");

    public LunarNetherClient(IEventBus modBus) {
        modBus.addListener(this::registerItemProperties);
        modBus.addListener(this::registerParticles);
        modBus.addListener(this::netherSky);
    }

    /**
     * A separate time value for the nether which controls light and sky rendering.
     * Increases 8 times slower than the normal overworld daytime.
     */
    public static long netherDayTime = 0;

    private void registerItemProperties(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.LUNAR_CLOCK.get(), LunarNether.id("moon_phase"), (pStack, pLevel, pEntity, pSeed) ->
                pLevel == null ? 0 : pLevel.getMoonPhase() / 8f
            );
        });
    }

    private void registerParticles(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.COLORED_ASH.get(), ColoredAshParticle.Provider::new);
    }

    private void netherSky(final RegisterDimensionSpecialEffectsEvent event) {
        event.register(BuiltinDimensionTypes.NETHER_EFFECTS, new LunarNetherDimensionSpecialEffects());
    }

    public static final int LENGTH_OF_LUNAR_DAY = 24000*8;

    public static double eclipse() {
        double shiftedEclipse = LunarNetherClient.netherDayTime % LENGTH_OF_LUNAR_DAY - 26875;
        return (20d / 1000000000) * (shiftedEclipse * shiftedEclipse);
    }

    public static double eclipseSlope() {
        double shiftedEclipse = LunarNetherClient.netherDayTime % LENGTH_OF_LUNAR_DAY - 26875;
        return (40d / 1000000000) * shiftedEclipse;
    }

    public static int eclipsePhase(double eclipseValue, boolean isSlopeNegative) {
        // .75 1st phase, .60 - 2nd phase, .35 3rd phase, .142 4th
        if (eclipseValue > 1.3) {
            return 0;
        } else if (eclipseValue > 0.7) {
            return isSlopeNegative ? 1 : 7;
        } else if (eclipseValue > 0.35) {
            return isSlopeNegative ? 2 : 6;
        } else if (eclipseValue > 0.142) {
            return isSlopeNegative ? 3 : 5;
        } else {
            return 4;
        }
    }

    public static float skyDarkness(double eclipseParabola) {
        double decimal = Mth.frac(LunarNetherClient.netherDayTime / (float)LENGTH_OF_LUNAR_DAY - 0.25);
        double d1 = 0.5 - Math.cos(decimal * Math.PI) / 2;
        return (float)(decimal * 2 + Math.min(d1, eclipseParabola)) / 3.0F;
    }
}
