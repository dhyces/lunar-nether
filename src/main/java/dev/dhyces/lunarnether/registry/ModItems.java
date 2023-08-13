package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Registries.ITEM, LunarNether.MODID);

    public static final RegistryObject<Item> LUNAR_DUST = registerBlockItem("lunar_dust", ModBlocks.LUNAR_DUST);

    public static final RegistryObject<Item> LUNAR_STONE = registerBlockItem("lunar_stone", ModBlocks.LUNAR_STONE);
    public static final RegistryObject<Item> LUNAR_STONE_STAIRS = registerBlockItem("lunar_stone_stairs", ModBlocks.LUNAR_STONE_STAIRS);
    public static final RegistryObject<Item> LUNAR_STONE_SLAB = registerBlockItem("lunar_stone_slab", ModBlocks.LUNAR_STONE_SLAB);
    public static final RegistryObject<Item> LUNAR_STONE_WALL = registerBlockItem("lunar_stone_wall", ModBlocks.LUNAR_STONE_WALL);

    public static final RegistryObject<Item> POLISHED_LUNAR_STONE = registerBlockItem("polished_lunar_stone", ModBlocks.POLISHED_LUNAR_STONE);
    public static final RegistryObject<Item> POLISHED_LUNAR_STONE_STAIRS = registerBlockItem("polished_lunar_stone_stairs", ModBlocks.POLISHED_LUNAR_STONE_STAIRS);
    public static final RegistryObject<Item> POLISHED_LUNAR_STONE_SLAB = registerBlockItem("polished_lunar_stone_slab", ModBlocks.POLISHED_LUNAR_STONE_SLAB);
    public static final RegistryObject<Item> POLISHED_LUNAR_STONE_WALL = registerBlockItem("polished_lunar_stone_wall", ModBlocks.POLISHED_LUNAR_STONE_WALL);

    public static final RegistryObject<Item> CUT_POLISHED_LUNAR_STONE = registerBlockItem("cut_polished_lunar_stone", ModBlocks.CUT_POLISHED_LUNAR_STONE);
    public static final RegistryObject<Item> CUT_POLISHED_LUNAR_STONE_STAIRS = registerBlockItem("cut_polished_lunar_stone_stairs", ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS);
    public static final RegistryObject<Item> CUT_POLISHED_LUNAR_STONE_SLAB = registerBlockItem("cut_polished_lunar_stone_slab", ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB);
    public static final RegistryObject<Item> CUT_POLISHED_LUNAR_STONE_WALL = registerBlockItem("cut_polished_lunar_stone_wall", ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL);

    private static RegistryObject<Item> registerBlockItem(String id, Supplier<Block> blockSupplier) {
        return REGISTRY.register(id, () -> new BlockItem(blockSupplier.get(), new Item.Properties()));
    }
}
