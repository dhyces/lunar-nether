package dev.dhyces.lunarnether;

import dev.dhyces.biomeextensions.BiomeExtensionsMod;
import dev.dhyces.lunarnether.networking.LunarNetherNetwork;
import dev.dhyces.lunarnether.registry.*;
import dev.dhyces.lunarnether.server.LunarTimeData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
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
        BiomeExtensionsMod.init();

//        LunarNetherNetwork.register();

        ModCreativeModTabs.CREATIVE_MODE_TABS.register(modBus);
        ModBlocks.REGISTRY.register(modBus);
        ModItems.REGISTRY.register(modBus);
        ModParticleTypes.REGISTRY.register(modBus);
        ModBiomeSources.REGISTRY.register(modBus);
        FeatureRegistry.FEATURES.register(modBus);

//        NeoForge.EVENT_BUS.addListener(this::onLevelLoaded);
//        NeoForge.EVENT_BUS.addListener(this::onLevelUnloaded);
//        NeoForge.EVENT_BUS.addListener(this::onLevelTick);
    }

//    private void onLevelLoaded(final LevelEvent.Load event) {
//        if (event.getLevel() instanceof ServerLevel level && level.dimension() == Level.NETHER) {
//            LunarTimeData.getOrCreate(level);
//            LunarTimeData.currentNether = level;
//        }
//    }
//
//    private void onLevelUnloaded(final LevelEvent.Unload event) {
//        if (event.getLevel() instanceof ServerLevel level && level.dimension() == Level.NETHER) {
//            LunarTimeData.currentNether = null;
//        }
//    }

//    private void onLevelTick(final LevelTickEvent.Post event) {
//        if (event.getLevel() instanceof ServerLevel level && level.dimension() == Level.NETHER) {
//            LunarTimeData.getOrCreate(level).update(level.dayTime());
//        }
//    }
}
