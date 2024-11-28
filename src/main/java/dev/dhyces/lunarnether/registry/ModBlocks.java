package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(LunarNether.MODID);

    //Moondust
    public static final DeferredBlock<Block> LUNAR_DUST = REGISTRY.register("lunar_dust", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).strength(0.5f).sound(SoundType.SAND)));
    
    //Moonstone
    public static final DeferredBlock<Block> LUNAR_STONE = REGISTRY.register("lunar_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> LUNAR_STONE_STAIRS = REGISTRY.register("lunar_stone_stairs", () -> new StairBlock(LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> LUNAR_STONE_SLAB = REGISTRY.register("lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> LUNAR_STONE_WALL = REGISTRY.register("lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));

    //Polished Moonstone
    public static final DeferredBlock<Block> POLISHED_LUNAR_STONE = REGISTRY.register("polished_lunar_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> POLISHED_LUNAR_STONE_STAIRS = REGISTRY.register("polished_lunar_stone_stairs", () -> new StairBlock(POLISHED_LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> POLISHED_LUNAR_STONE_SLAB = REGISTRY.register("polished_lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> POLISHED_LUNAR_STONE_WALL = REGISTRY.register("polished_lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    
    //Cut Polished Moonstone
    public static final DeferredBlock<Block> CUT_POLISHED_LUNAR_STONE = REGISTRY.register("cut_polished_lunar_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> CUT_POLISHED_LUNAR_STONE_STAIRS = REGISTRY.register("cut_polished_lunar_stone_stairs", () -> new StairBlock(CUT_POLISHED_LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> CUT_POLISHED_LUNAR_STONE_SLAB = REGISTRY.register("cut_polished_lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final DeferredBlock<Block> CUT_POLISHED_LUNAR_STONE_WALL = REGISTRY.register("cut_polished_lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));

    //Ilmenite
    public static final DeferredBlock<Block> ILMENITE_ORE = REGISTRY.register("ilmenite_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> RAW_ILMENITE_BLOCK = REGISTRY.register("raw_ilmenite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).strength(2.0F, 16.0F)));

    //Titanium
    public static final DeferredBlock<Block> TITANIUM_BLOCK = REGISTRY.register("titanium_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(3.0F, 16.0F).mapColor(MapColor.METAL)));

    //Cut Titanium
    public static final DeferredBlock<Block> CUT_TITANIUM = REGISTRY.register("cut_titanium", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(3.0F, 16.0F).mapColor(MapColor.METAL)));
    public static final DeferredBlock<Block> CUT_TITANIUM_STAIRS = REGISTRY.register("cut_titanium_stairs", () -> new StairBlock(CUT_TITANIUM.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(3.0F, 16.0F).mapColor(MapColor.METAL)));
    public static final DeferredBlock<Block> CUT_TITANIUM_SLAB = REGISTRY.register("cut_titanium_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(3.0F, 16.0F).mapColor(MapColor.METAL)));

    //Astrolith
    public static final DeferredBlock<Block> ASTRALITH = REGISTRY.register("astralith", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).strength(30.0F, 600.0F).mapColor(MapColor.TERRACOTTA_CYAN)));
}
