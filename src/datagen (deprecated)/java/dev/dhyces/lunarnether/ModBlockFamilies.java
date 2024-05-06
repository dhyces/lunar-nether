package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.registry.ModBlocks;
import net.minecraft.data.BlockFamily;

public class ModBlockFamilies {
    public static final BlockFamily LUNAR_STONE = new BlockFamily.Builder(ModBlocks.LUNAR_STONE.get())
            .stairs(ModBlocks.LUNAR_STONE_STAIRS.get())
            .slab(ModBlocks.LUNAR_STONE_SLAB.get())
            .wall(ModBlocks.LUNAR_STONE_WALL.get())
            .polished(ModBlocks.POLISHED_LUNAR_STONE.get())
            .getFamily();
    public static final BlockFamily POLISHED_LUNAR_STONE = new BlockFamily.Builder(ModBlocks.POLISHED_LUNAR_STONE.get())
            .stairs(ModBlocks.POLISHED_LUNAR_STONE_STAIRS.get())
            .slab(ModBlocks.POLISHED_LUNAR_STONE_SLAB.get())
            .wall(ModBlocks.POLISHED_LUNAR_STONE_WALL.get())
            .cut(ModBlocks.CUT_POLISHED_LUNAR_STONE.get())
            .getFamily();
    public static final BlockFamily CUT_POLISHED_LUNAR_STONE = new BlockFamily.Builder(ModBlocks.CUT_POLISHED_LUNAR_STONE.get())
            .stairs(ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS.get())
            .slab(ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB.get())
            .wall(ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL.get())
            .getFamily();
}
