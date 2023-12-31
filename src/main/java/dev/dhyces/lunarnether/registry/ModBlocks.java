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

    public static final RegistryObject<Block> LUNAR_DUST = REGISTRY.register("lunar_dust", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(0.5f).sound(SoundType.SAND)));
    
    public static final RegistryObject<Block> LUNAR_STONE = REGISTRY.register("lunar_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> LUNAR_STONE_STAIRS = REGISTRY.register("lunar_stone_stairs", () -> new StairBlock(() -> LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> LUNAR_STONE_SLAB = REGISTRY.register("lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> LUNAR_STONE_WALL = REGISTRY.register("lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));

    public static final RegistryObject<Block> POLISHED_LUNAR_STONE = REGISTRY.register("polished_lunar_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> POLISHED_LUNAR_STONE_STAIRS = REGISTRY.register("polished_lunar_stone_stairs", () -> new StairBlock(() -> POLISHED_LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> POLISHED_LUNAR_STONE_SLAB = REGISTRY.register("polished_lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> POLISHED_LUNAR_STONE_WALL = REGISTRY.register("polished_lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));

    public static final RegistryObject<Block> CUT_POLISHED_LUNAR_STONE = REGISTRY.register("cut_polished_lunar_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> CUT_POLISHED_LUNAR_STONE_STAIRS = REGISTRY.register("cut_polished_lunar_stone_stairs", () -> new StairBlock(() -> CUT_POLISHED_LUNAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> CUT_POLISHED_LUNAR_STONE_SLAB = REGISTRY.register("cut_polished_lunar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> CUT_POLISHED_LUNAR_STONE_WALL = REGISTRY.register("cut_polished_lunar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).mapColor(MapColor.STONE)));

    public static final RegistryObject<Block> ASH_BLOCK = REGISTRY.register("ash_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.SUSPICIOUS_SAND)));
}
