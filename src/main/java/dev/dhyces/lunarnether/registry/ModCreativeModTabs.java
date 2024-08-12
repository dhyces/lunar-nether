package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.registry.ModItems;

public class ModCreativeModTabs { 
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LunarNether.MODID);

        public static final RegistryObject<CreativeModeTab> MOON_TAB = CREATIVE_MODE_TABS.register("moon_tab", () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.Moonstone.get()))
        .title(Component.translatable("creativetab.moon_tab"))
        .displayItems((itemDisplayParameters,output) -> {
            output.accept(ModItems.LUNAR_STONE.get());
        })
        .getItems()
        .build());
}