package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.client.LunarNetherDimensionSpecialEffects;
import dev.dhyces.lunarnether.client.particle.ColoredAshParticle;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.registry.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
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
    public LunarNetherClient(IEventBus modBus) {
        modBus.addListener(this::registerItemProperties);
        modBus.addListener(this::registerParticles);
        modBus.addListener(this::netherSky);
    }

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
        double shiftedEclipse = Minecraft.getInstance().level.dayTime() % LENGTH_OF_LUNAR_DAY - 26875;
        return (20d / 1000000000) * (shiftedEclipse * shiftedEclipse);
    }

    public static float skyDarkness(double eclipseParabola) {
        double decimal = Mth.frac(Minecraft.getInstance().level.dayTime() / (float)LENGTH_OF_LUNAR_DAY - 0.25);
        double d1 = 0.5 - Math.cos(decimal * Math.PI) / 2;
        return (float)(decimal * 2 + Math.min(d1, eclipseParabola)) / 3.0F;
    }
}
