package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(Registries.BLOCK, LunarNether.MODID);

    public static final RegistryObject<Block> LUNA_ROCK = REGISTRY.register("luna_rock", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE)));
}
