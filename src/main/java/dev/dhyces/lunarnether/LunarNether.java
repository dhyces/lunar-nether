package dev.dhyces.lunarnether;

import dev.dhyces.biomeextensions.BiomeExtensionsMod;
import dev.dhyces.lunarnether.networking.LunarNetherNetwork;
import dev.dhyces.lunarnether.registry.*;
import dev.dhyces.lunarnether.server.LunarTimeData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(LunarNether.MODID)
public class LunarNether {
    public static final String MODID = "lunarnether";
    public static ResourceLocation id(String id) {
        return new ResourceLocation(MODID, id);
    }
    public static final Logger LOGGER = LoggerFactory.getLogger("LunarNether");

    public LunarNether() {
        BiomeExtensionsMod.init();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        LunarNetherNetwork.register();

        ModCreativeModTabs.CREATIVE_MODE_TABS.register(modBus);
        ModBlocks.REGISTRY.register(modBus);
        ModItems.REGISTRY.register(modBus);
        ModParticleTypes.REGISTRY.register(modBus);
        ModBiomeSources.REGISTRY.register(modBus);
        BiomeModifierTypes.REGISTER.register(modBus);
        FeatureRegistry.FEATURES.register(modBus);

        forgeBus.addListener(this::onLevelLoaded);
        forgeBus.addListener(this::onLevelUnloaded);
        forgeBus.addListener(this::onLevelTick);

        if (FMLLoader.getDist().isClient()) {
            LunarNetherClient.register(modBus, forgeBus);
        }
    }

    private void onLevelLoaded(final LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel level && level.dimension() == Level.NETHER) {
            LunarTimeData.getOrCreate(level);
            LunarTimeData.currentNether = level;
        }
    }

    private void onLevelUnloaded(final LevelEvent.Unload event) {
        if (event.getLevel() instanceof ServerLevel level && level.dimension() == Level.NETHER) {
            LunarTimeData.currentNether = null;
        }
    }

    private void onLevelTick(final TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.level instanceof ServerLevel level && level.dimension() == Level.NETHER) {
            LunarTimeData.getOrCreate(level).update(level.dayTime());
        }
    }
}
