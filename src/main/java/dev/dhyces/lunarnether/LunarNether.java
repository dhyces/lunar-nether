package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.registry.FeatureRegistry;
import dev.dhyces.lunarnether.registry.ModBiomeSources;
import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModCreativeModTabs;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.registry.ModParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(LunarNether.MODID)
public class LunarNether {
    public static final String MODID = "lunarnether";
    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MODID, id);
    }
    public static final Logger LOGGER = LoggerFactory.getLogger("LunarNether");

    public LunarNether(IEventBus modBus) {
        ModCreativeModTabs.CREATIVE_MODE_TABS.register(modBus);
        ModBlocks.REGISTRY.register(modBus);
        ModItems.REGISTRY.register(modBus);
        ModParticleTypes.REGISTRY.register(modBus);
        ModBiomeSources.REGISTRY.register(modBus);
        FeatureRegistry.FEATURES.register(modBus);
    }

    /**
     * A separate time calculation for the nether which controls light and sky rendering.
     * Increases 8 times slower than the normal overworld daytime.
     */
    public static float netherTimeOfDay(long daytime) {
        double decimal = Mth.frac(daytime / (24000.0 * 8) - 0.25);
        double d1 = 0.5 - Math.cos(decimal * Math.PI) / 2;
        return (float)(decimal * 2 + d1) / 3.0F;
    }
}
