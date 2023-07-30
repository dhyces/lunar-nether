package dev.dhyces.lunarnether.data;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGen extends BlockStateProvider {
    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LunarNether.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        randomlyRotated(ModBlocks.LUNA_ROCK.get(), "luna_rock", LunarNether.id("block/luna_rock"));
    }

    private void randomlyRotated(Block block, String name, ResourceLocation texture) {
        ModelFile model = models().cubeAll(name, texture);
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.addModels(builder.partialState(), ConfiguredModel.allRotations(model, false));
    }
}
