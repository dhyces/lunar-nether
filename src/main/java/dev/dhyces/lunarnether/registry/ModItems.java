package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Registries.ITEM, LunarNether.MODID);

    public static final RegistryObject<Item> LUNA_ROCK = REGISTRY.register("luna_rock", () -> new BlockItem(ModBlocks.LUNA_ROCK.get(), new Item.Properties()));
}
