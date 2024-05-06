package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.registry.ModBlocks;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.Util;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class BlockLootSubGen extends BlockLootSubProvider {
    private final Map<BlockFamily.Variant, Function<Block, LootTable.Builder>> variantGenerators = Util.make(new Object2ObjectArrayMap<>(1), map -> {
        map.put(BlockFamily.Variant.SLAB, this::createSlabItemTable);
        map.put(BlockFamily.Variant.DOOR, this::createDoorTable);
        map.defaultReturnValue(this::createSingleItemTable);
    });
    protected BlockLootSubGen() {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.LUNAR_DUST.get());
        dropSelf(ModBlocks.LUNAR_STONE.get());
        family(ModBlockFamilies.LUNAR_STONE);
        family(ModBlockFamilies.POLISHED_LUNAR_STONE);
        family(ModBlockFamilies.CUT_POLISHED_LUNAR_STONE);
        dropSelf(ModBlocks.ASH_BLOCK.get());
    }

    protected void family(BlockFamily family) {
        family.getVariants().forEach((variant, block) -> add(block, variantGenerators.get(variant).apply(block)));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.REGISTRY.getEntries().stream().map(RegistryObject::get).toList();
    }
}
