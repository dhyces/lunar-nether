package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGen extends BlockTagsProvider {
    public BlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LunarNether.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.WALLS)
                .add(
                        ModBlocks.LUNAR_STONE_WALL.get(),
                        ModBlocks.POLISHED_LUNAR_STONE_WALL.get(),
                        ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL.get()
                );
        tag(BlockTags.STAIRS)
                .add(
                        ModBlocks.LUNAR_STONE_STAIRS.get(),
                        ModBlocks.POLISHED_LUNAR_STONE_STAIRS.get(),
                        ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS.get()
                );
        tag(BlockTags.SLABS)
                .add(
                        ModBlocks.LUNAR_STONE_SLAB.get(),
                        ModBlocks.POLISHED_LUNAR_STONE_SLAB.get(),
                        ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB.get()
                );
    }
}
