package dev.dhyces.lunarnether.worldgen.feature.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.Optional;

public record ExtendedBlockStateConfiguration(BlockState state, Optional<TagKey<Block>> placementTag) implements FeatureConfiguration {
    public static final Codec<ExtendedBlockStateConfiguration> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockState.CODEC.fieldOf("state").forGetter(ExtendedBlockStateConfiguration::state),
                    TagKey.codec(Registries.BLOCK).optionalFieldOf("placement_tag").forGetter(ExtendedBlockStateConfiguration::placementTag)
            ).apply(instance, ExtendedBlockStateConfiguration::new)
    );
}
