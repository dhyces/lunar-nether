package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.registry.ModBlocks;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BlockStateGen extends BlockStateProvider {
    private final Map<BlockFamily.Variant, BiConsumer<Block, BlockFamily>> variantModels = Util.make(new EnumMap<>(BlockFamily.Variant.class), map -> {
        map.put(BlockFamily.Variant.BUTTON, (block, family) -> {
            buttonBlock((ButtonBlock) block, blockTexture(family.getBaseBlock()));
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.CHISELED, (block, family) -> {
            addCubeAll(block);
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.CRACKED, (block, family) -> {
            addCubeAll(block);
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.CUT, (block, family) -> {
            addCubeAll(block);
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.DOOR, (block, family) -> {
            doorBlockWithRenderType((DoorBlock) block, blockTexture(block).withSuffix("_bottom"), blockTexture(block).withSuffix("_top"), "cutout");
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.CUSTOM_FENCE, (block, family) -> {
            fenceBlock((FenceBlock) block, blockTexture(family.getBaseBlock()));
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.FENCE, (block, family) -> {
            fenceBlock((FenceBlock) block, blockTexture(family.getBaseBlock()));
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.CUSTOM_FENCE_GATE, (block, family) -> {
            fenceGateBlock((FenceGateBlock) block, blockTexture(family.getBaseBlock()));
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.FENCE_GATE, (block, family) -> {
            fenceGateBlock((FenceGateBlock) block, blockTexture(family.getBaseBlock()));
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.MOSAIC, (block, family) -> {
            addCubeAll(block);
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.SIGN, (block, family) -> {
            signBlock(block, family.getBaseBlock());
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.SLAB, (block, family) -> {
            slabBlock((SlabBlock) block, key(family.getBaseBlock()), blockTexture(family.getBaseBlock()));
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.STAIRS, (block, family) -> {
            stairsBlock((StairBlock) block, blockTexture(family.getBaseBlock()));
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.PRESSURE_PLATE, (block, family) -> {
            pressurePlateBlock((PressurePlateBlock) block, blockTexture(family.getBaseBlock()));
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.POLISHED, (block, family) -> {
            addCubeAll(block);
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.TRAPDOOR, (block, family) -> {
            trapdoorBlock((TrapDoorBlock) block, blockTexture(block), true);
            simpleBlockItem(block);
        });
        map.put(BlockFamily.Variant.WALL, (block, family) -> {
            wallBlock((WallBlock) block, blockTexture(family.getBaseBlock()));
            itemModels().wallInventory(name(block), blockTexture(family.getBaseBlock()));
        });
        map.put(BlockFamily.Variant.WALL_SIGN, (block, family) -> {
            signBlock(block, family.getBaseBlock());
            simpleBlockItem(block);
        });
    });
    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LunarNether.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        simpleBlock(ModBlocks.LUNAR_DUST.get());
        simpleBlockItem(ModBlocks.LUNAR_DUST.get());
        randomlyRotated(ModBlocks.LUNAR_STONE.get());
        simpleBlockItem(ModBlocks.LUNAR_STONE.get());
        family(ModBlockFamilies.LUNAR_STONE);
        family(ModBlockFamilies.POLISHED_LUNAR_STONE);
        family(ModBlockFamilies.CUT_POLISHED_LUNAR_STONE);
        randomlyRotated(ModBlocks.ASH_BLOCK.get(), LunarNether.id("block/ash"));
        simpleBlockItem(ModBlocks.ASH_BLOCK.get());
    }

    public void simpleBlockItem(Block block) {
        simpleBlockItem(block, models().getBuilder(blockTexture(block).toString()));
    }

    public void addCubeAll(Block block) {
        getVariantBuilder(block).partialState().addModels(new ConfiguredModel(cubeAll(block)));
    }

    public void family(BlockFamily family) {
        family.getVariants().forEach((variant, block) -> {
            variantModels.get(variant).accept(block, family);
        });
    }

    public void randomlyRotated(Block block) {
        randomlyRotated(block, name(block), blockTexture(block));
    }

    public void randomlyRotated(Block block, ResourceLocation texture) {
        randomlyRotated(block, name(block), texture);
    }

    public void randomlyRotated(Block block, String name, ResourceLocation texture) {
        ModelFile model = models().cubeAll(name, texture);
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.addModels(builder.partialState(), ConfiguredModel.allRotations(model, false));
    }

    public void signBlock(Block block, Block planks) {
        simpleBlock(block, models().sign(name(block), blockTexture(planks)));
    }

    public ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public String name(Block block) {
        return key(block).getPath();
    }
}
