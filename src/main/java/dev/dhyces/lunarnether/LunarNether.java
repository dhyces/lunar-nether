package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.registry.ModBiomeSources;
import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(LunarNether.MODID)
public class LunarNether {
    public static final String MODID = "lunarnether";
    public static ResourceLocation id(String id) {
        return new ResourceLocation(MODID, id);
    }

    public LunarNether() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        ModBlocks.REGISTRY.register(modBus);
        ModItems.REGISTRY.register(modBus);
        ModBiomeSources.REGISTRY.register(modBus);

        if (FMLLoader.getDist().isClient()) {
            LunarNetherClient.register(modBus, forgeBus);
        }
    }
}
