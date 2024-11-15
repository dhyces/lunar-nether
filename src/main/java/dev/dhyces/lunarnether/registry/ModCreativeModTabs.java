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
        .displayItems((pParameters, pOutput) -> {
            //all the obtainable items need to be here. Weirdly couldn't find an easy way to do this automatically for every item in the mod.
            pOutput.accept(ModItems.LUNAR_DUST.get());
            pOutput.accept(ModItems.LUNAR_STONE.get());
            pOutput.accept(ModItems.LUNAR_STONE_STAIRS.get());
            pOutput.accept(ModItems.LUNAR_STONE_SLAB.get());
            pOutput.accept(ModItems.LUNAR_STONE_WALL.get());

            pOutput.accept(ModItems.POLISHED_LUNAR_STONE.get());
            pOutput.accept(ModItems.POLISHED_LUNAR_STONE_STAIRS.get());
            pOutput.accept(ModItems.POLISHED_LUNAR_STONE_SLAB.get());
            pOutput.accept(ModItems.POLISHED_LUNAR_STONE_WALL.get());

            pOutput.accept(ModItems.CUT_POLISHED_LUNAR_STONE.get());
            pOutput.accept(ModItems.CUT_POLISHED_LUNAR_STONE_STAIRS.get());
            pOutput.accept(ModItems.CUT_POLISHED_LUNAR_STONE_SLAB.get());
            pOutput.accept(ModItems.CUT_POLISHED_LUNAR_STONE_WALL.get());

            pOutput.accept(ModItems.ILMENITE_ORE.get());
            pOutput.accept(ModItems.RAW_ILMENITE_BLOCK.get());

            pOutput.accept(ModItems.TITANIUM_BLOCK.get());

            pOutput.accept(ModItems.CUT_TITANIUM.get());
            pOutput.accept(ModItems.CUT_TITANIUM_STAIRS.get());
            pOutput.accept(ModItems.CUT_TITANIUM_SLAB.get());

            pOutput.accept(ModItems.RAW_ILMENITE.get());
            pOutput.accept(ModItems.TITANIUM_INGOT.get());
            pOutput.accept(ModItems.TITANIUM_NUGGET.get());

            pOutput.accept(ModItems.LUNAR_CLOCK.get());
        })
        .build());
}