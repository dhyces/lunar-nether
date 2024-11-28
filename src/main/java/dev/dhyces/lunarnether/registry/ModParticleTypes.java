package dev.dhyces.lunarnether.registry;

import com.mojang.serialization.MapCodec;
import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.particle.ColorRangeParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, LunarNether.MODID);

    public static final DeferredHolder<ParticleType<?>, ParticleType<ColorRangeParticleOption>> COLORED_ASH = registerSpecialType("colored_ash", false, ColorRangeParticleOption::codec, ColorRangeParticleOption::streamCodec);

    private static <T extends ParticleOptions> DeferredHolder<ParticleType<?>, ParticleType<T>> registerSpecialType(String name, boolean overrideLimiter, final Function<ParticleType<T>, MapCodec<T>> codecGetter, final Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> streamCodecGetter) {
        return REGISTRY.register(name, () -> new ParticleType<T>(overrideLimiter) {
            @Override
            public MapCodec<T> codec() {
                return codecGetter.apply(this);
            }

            @Override
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return streamCodecGetter.apply(this);
            }
        });
    }
}
