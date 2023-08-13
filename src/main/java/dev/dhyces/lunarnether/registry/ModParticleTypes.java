package dev.dhyces.lunarnether.registry;

import com.mojang.serialization.Codec;
import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.particle.ColorRangeParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, LunarNether.MODID);

    public static final RegistryObject<ParticleType<ColorRangeParticleOption>> COLORED_ASH = REGISTRY.register("colored_ash", () -> new ParticleType<>(false, ColorRangeParticleOption.DESERIALIZER) {
        @Override
        public Codec<ColorRangeParticleOption> codec() {
            return ColorRangeParticleOption.codec(this);
        }
    });
}
