package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EnUsLangGen extends LanguageProvider {
    public EnUsLangGen(PackOutput output) {
        super(output, LunarNether.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.LUNAR_DUST.get(), "Lunar Dust");
        add(ModBlocks.LUNAR_STONE.get(), "Lunar Stone");
        add(ModBlocks.LUNAR_STONE_STAIRS.get(), "Lunar Stone Stairs");
        add(ModBlocks.LUNAR_STONE_SLAB.get(), "Lunar Stone Slab");
        add(ModBlocks.LUNAR_STONE_WALL.get(), "Lunar Stone Wall");
        add(ModBlocks.POLISHED_LUNAR_STONE.get(), "Polished Lunar Stone");
        add(ModBlocks.POLISHED_LUNAR_STONE_STAIRS.get(), "Polished Lunar Stone Stairs");
        add(ModBlocks.POLISHED_LUNAR_STONE_SLAB.get(), "Polished Lunar Stone Slab");
        add(ModBlocks.POLISHED_LUNAR_STONE_WALL.get(), "Polished Lunar Stone Wall");
        add(ModBlocks.CUT_POLISHED_LUNAR_STONE.get(), "Cut Lunar Stone");
        add(ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS.get(), "Cut Polished Lunar Stone Stairs");
        add(ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB.get(), "Cut Polished Lunar Stone Slab");
        add(ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL.get(), "Cut Polished Lunar Stone Wall");
        add(ModBlocks.ASH_BLOCK.get(), "Ash Block");
    }
}
