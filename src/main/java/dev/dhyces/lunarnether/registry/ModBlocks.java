package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.block.TitaniumBulbBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(LunarNether.MODID);

    public static final BlockSetType TITANIUM = BlockSetType.register(
            new BlockSetType(
                    "titanium",
                    true,
                    true,
                    false,
                    BlockSetType.PressurePlateSensitivity.EVERYTHING,
                    SoundType.COPPER,
                    SoundEvents.COPPER_DOOR_CLOSE,
                    SoundEvents.COPPER_DOOR_OPEN,
                    SoundEvents.COPPER_TRAPDOOR_CLOSE,
                    SoundEvents.COPPER_TRAPDOOR_OPEN,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON
            )
    );

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

    public static final DeferredBlock<Block> CHISELED_TITANIUM = REGISTRY.register("chiseled_titanium", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(3.0F, 16.0F).mapColor(MapColor.METAL)));
    public static final DeferredBlock<Block> TITANIUM_DOOR = REGISTRY.register("titanium_door", () -> new DoorBlock(TITANIUM, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(3.0F, 16.0F).mapColor(MapColor.METAL)));
    public static final DeferredBlock<Block> TITANIUM_TRAPDOOR = REGISTRY.register("titanium_trapdoor", () -> new TrapDoorBlock(TITANIUM, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(3.0F, 16.0F).mapColor(MapColor.METAL)));
    public static final DeferredBlock<Block> TITANIUM_GRATE = REGISTRY.register("titanium_grate", () -> new WaterloggedTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).isValidSpawn(Blocks::never).isRedstoneConductor(Blocks::never).isSuffocating(Blocks::never).isViewBlocking(Blocks::never).noOcclusion().strength(3.0F, 16.0F).mapColor(MapColor.METAL)));
    public static final DeferredBlock<Block> TITANIUM_BULB = REGISTRY.register("titanium_bulb", () -> new TitaniumBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(3.0F, 16.0F).mapColor(MapColor.METAL)));

    //Astrolith
    public static final DeferredBlock<Block> ASTRALITH = REGISTRY.register("astralith", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).strength(30.0F, 600.0F).mapColor(MapColor.TERRACOTTA_CYAN)));
}
