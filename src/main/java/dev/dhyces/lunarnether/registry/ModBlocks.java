package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(Registries.BLOCK, LunarNether.MODID);

    //Moondust
    public static final RegistryObject<Block> LUNAR_DUST = REGISTRY.register("lunar_dust", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).strength(0.5f).sound(SoundType.SAND)));
    
    //Moonstone
    public static final RegistryObject<Block> LUNAR_STONE = REGISTRY.register("lunar_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> LUNAR_STONE_STAIRS = REGISTRY.register("lunar_stone_stairs", () -> new StairBlock(() -> LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> LUNAR_STONE_SLAB = REGISTRY.register("lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> LUNAR_STONE_WALL = REGISTRY.register("lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));

    //Polished Moonstone
    public static final RegistryObject<Block> POLISHED_LUNAR_STONE = REGISTRY.register("polished_lunar_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> POLISHED_LUNAR_STONE_STAIRS = REGISTRY.register("polished_lunar_stone_stairs", () -> new StairBlock(() -> POLISHED_LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> POLISHED_LUNAR_STONE_SLAB = REGISTRY.register("polished_lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> POLISHED_LUNAR_STONE_WALL = REGISTRY.register("polished_lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    
    //Cut Polished Moonstone
    public static final RegistryObject<Block> CUT_POLISHED_LUNAR_STONE = REGISTRY.register("cut_polished_lunar_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> CUT_POLISHED_LUNAR_STONE_STAIRS = REGISTRY.register("cut_polished_lunar_stone_stairs", () -> new StairBlock(() -> CUT_POLISHED_LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> CUT_POLISHED_LUNAR_STONE_SLAB = REGISTRY.register("cut_polished_lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));
    public static final RegistryObject<Block> CUT_POLISHED_LUNAR_STONE_WALL = REGISTRY.register("cut_polished_lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(1.6F, 8.0F).mapColor(MapColor.QUARTZ)));

    //Ilmenite
    public static final RegistryObject<Block> ILMENITE_ORE = REGISTRY.register("ilmenite_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> RAW_ILMENITE_BLOCK = REGISTRY.register("raw_ilmenite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).strength(2.0F, 10.0F)));

    //Titanium
    public static final RegistryObject<Block> TITANIUM_BLOCK = REGISTRY.register("titanium_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(3.0F, 10.0F).mapColor(MapColor.METAL)));

    //Cut Titanium
    public static final RegistryObject<Block> CUT_TITANIUM = REGISTRY.register("cut_titanium", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(3.0F, 10.0F).mapColor(MapColor.METAL)));
    public static final RegistryObject<Block> CUT_TITANIUM_STAIRS = REGISTRY.register("cut_titanium_stairs", () -> new StairBlock(() -> CUT_TITANIUM.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(3.0F, 10.0F).mapColor(MapColor.METAL)));
    public static final RegistryObject<Block> CUT_TITANIUM_SLAB = REGISTRY.register("cut_titanium_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(3.0F, 10.0F).mapColor(MapColor.METAL)));
    //Removed for consistiency with copper
    //public static final RegistryObject<Block> CUT_TITANIUM_WALL = REGISTRY.register("cut_titanium_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(3.0F, 10.0F).mapColor(MapColor.METAL)));

}
