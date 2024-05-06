package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.registry.ModBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {
    public RecipeGen(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        generateRecipes(pWriter, ModBlockFamilies.LUNAR_STONE);
        generateRecipes(pWriter, ModBlockFamilies.POLISHED_LUNAR_STONE);
        generateRecipes(pWriter, ModBlockFamilies.CUT_POLISHED_LUNAR_STONE);
        stonecutterFamily(ModBlockFamilies.LUNAR_STONE, pWriter);
        stonecutterFamily(ModBlockFamilies.POLISHED_LUNAR_STONE, ModBlocks.LUNAR_STONE.get(), pWriter);
        stonecutterFamily(ModBlockFamilies.CUT_POLISHED_LUNAR_STONE, ModBlocks.LUNAR_STONE.get(), pWriter);
        stonecutterFamily(ModBlockFamilies.POLISHED_LUNAR_STONE, pWriter);
        stonecutterFamily(ModBlockFamilies.CUT_POLISHED_LUNAR_STONE, pWriter);
    }

    private void stonecutterFamily(BlockFamily family, Consumer<FinishedRecipe> pWriter) {
        stonecutterFamily(family, family.getBaseBlock(), pWriter);
    }

    private void stonecutterFamily(BlockFamily family, Block base, Consumer<FinishedRecipe> pWriter) {
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.STAIRS), base);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.SLAB), base);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.WALL), base);
    }
}
