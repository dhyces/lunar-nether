package dev.dhyces.lunarnether.registry;

import com.mojang.serialization.Codec;
import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.worldgen.modifiers.AddCarverBiomeModifier;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BiomeModifierTypes {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> REGISTER = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, LunarNether.MODID);

    public static final RegistryObject<Codec<AddCarverBiomeModifier>> ADD_CARVER = REGISTER.register("add_carver", () -> AddCarverBiomeModifier.CODEC);
}
