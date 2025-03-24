package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import dev.dhyces.lunarnether.registry.ModItems;

public class ModCreativeModTabs { 
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LunarNether.MODID);

        public static final RegistryObject<CreativeModeTab> MOON_TAB = CREATIVE_MODE_TABS.register("moon_tab", () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.LUNAR_STONE.get()))
        .title(Component.translatable("creativetab.lunarnether.main"))
        .displayItems((pParameters, pOutput) ->
                ModItems.REGISTRY.getEntries().forEach((item) -> pOutput.accept(item.get())))
        .build());
}