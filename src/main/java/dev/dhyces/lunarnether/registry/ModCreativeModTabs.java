package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModTabs { 
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LunarNether.MODID);

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MOON_TAB = CREATIVE_MODE_TABS.register("moon_tab", () -> CreativeModeTab.builder().icon(ModItems.LUNAR_STONE::toStack)
        .title(Component.translatable("creativetab.lunarnether.main"))
        .displayItems((pParameters, pOutput) -> {
            //all the obtainable items need to be here. Weirdly couldn't find an easy way to do this automatically for every item in the mod.
            pOutput.accept(ModItems.LUNAR_DUST);
            pOutput.accept(ModItems.LUNAR_STONE);
            pOutput.accept(ModItems.LUNAR_STONE_STAIRS);
            pOutput.accept(ModItems.LUNAR_STONE_SLAB);
            pOutput.accept(ModItems.LUNAR_STONE_WALL);

            pOutput.accept(ModItems.POLISHED_LUNAR_STONE);
            pOutput.accept(ModItems.POLISHED_LUNAR_STONE_STAIRS);
            pOutput.accept(ModItems.POLISHED_LUNAR_STONE_SLAB);
            pOutput.accept(ModItems.POLISHED_LUNAR_STONE_WALL);

            pOutput.accept(ModItems.CUT_POLISHED_LUNAR_STONE);
            pOutput.accept(ModItems.CUT_POLISHED_LUNAR_STONE_STAIRS);
            pOutput.accept(ModItems.CUT_POLISHED_LUNAR_STONE_SLAB);
            pOutput.accept(ModItems.CUT_POLISHED_LUNAR_STONE_WALL);

            pOutput.accept(ModItems.ILMENITE_ORE);
            pOutput.accept(ModItems.RAW_ILMENITE_BLOCK);

            pOutput.accept(ModItems.TITANIUM_BLOCK);

            pOutput.accept(ModItems.CUT_TITANIUM);
            pOutput.accept(ModItems.CUT_TITANIUM_STAIRS);
            pOutput.accept(ModItems.CUT_TITANIUM_SLAB);

            pOutput.accept(ModItems.RAW_ILMENITE);
            pOutput.accept(ModItems.TITANIUM_INGOT);
            pOutput.accept(ModItems.TITANIUM_NUGGET);

            pOutput.accept(ModItems.LUNAR_CLOCK);
        })
        .build());
}