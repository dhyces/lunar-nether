package dev.dhyces.lunarnether;

import dev.dhyces.biomeextensions.BiomeExtensionsMod;
import dev.dhyces.lunarnether.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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

        ModBlocks.REGISTRY.register(modBus);
        ModItems.REGISTRY.register(modBus);
        ModParticleTypes.REGISTRY.register(modBus);
        ModBiomeSources.REGISTRY.register(modBus);
        BiomeModifierTypes.REGISTER.register(modBus);
        FeatureRegistry.FEATURES.register(modBus);

        modBus.addListener(this::addItemsToTabs);

        if (FMLLoader.getDist().isClient()) {
            LunarNetherClient.register(modBus, forgeBus);
        }
    }

    private void addItemsToTabs(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SEARCH) {
            ModItems.REGISTRY.getEntries().forEach(item -> event.accept(item, CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY));
        }
    }
}
