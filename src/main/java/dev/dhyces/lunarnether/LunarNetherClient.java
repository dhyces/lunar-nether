package dev.dhyces.lunarnether;

import com.mojang.blaze3d.vertex.*;

import dev.dhyces.lunarnether.client.level.LunarNetherSpecialEffects;
import dev.dhyces.lunarnether.client.particle.ColoredAshParticle;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.registry.ModParticleTypes;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.util.Mth;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;

import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.Math;

public final class LunarNetherClient {
    /**
     * A separate time value for the nether which controls light and sky rendering.
     * Increases 8 times slower than the normal overworld daytime.
     */
    public static long netherDayTime = 0;

    static void register(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(LunarNetherClient::registerItemProperties);
        modBus.addListener(LunarNetherClient::registerParticles);
        modBus.addListener(LunarNetherClient::netherSky);

    }

    private static void registerItemProperties(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.LUNAR_CLOCK.get(), LunarNether.id("moon_phase"), (pStack, pLevel, pEntity, pSeed) ->
                pLevel == null ? 0 : pLevel.getMoonPhase() / 8f
            );
        });
    }

    private static void registerParticles(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.COLORED_ASH.get(), ColoredAshParticle.Provider::new);
    }

    private static void netherSky(final RegisterDimensionSpecialEffectsEvent event) {
        if(!ModList.get().isLoaded("stellarview"))
            event.register(BuiltinDimensionTypes.NETHER_EFFECTS, new LunarNetherSpecialEffects());
    }

    public static final int LENGTH_OF_LUNAR_DAY = 24000*8;

    public static double eclipse() {
        //this used to be use to make the sky darken during eclipses but I don't know how
        double shiftedEclipse = LunarNetherClient.netherDayTime % LENGTH_OF_LUNAR_DAY - 12000;
        return (20d / 1000000000) * (shiftedEclipse * shiftedEclipse);
    }

    public static float skyDarkness(double eclipseParabola) {
        //the constant at the end of the next line does something related to the "epoch" I think. It used to be 0.25
        double decimal = Mth.frac(LunarNetherClient.netherDayTime / (float)LENGTH_OF_LUNAR_DAY - 0.0);
        double d1 = 0.5 - Math.cos(decimal * Math.PI) / 2;
        return (float)(decimal * 2 + Math.min(d1, eclipseParabola)) / 3.0F;
    }
}
