package dev.dhyces.lunarnether.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.dhyces.lunarnether.worldgen.feature.configs.ExtendedBlockStateConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class RockFeature extends Feature<ExtendedBlockStateConfiguration> {
    public RockFeature(Codec<ExtendedBlockStateConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ExtendedBlockStateConfiguration> featurePlaceContext) {
        BlockPos pos = featurePlaceContext.origin();
        WorldGenLevel level = featurePlaceContext.level();
        RandomSource randomSource = featurePlaceContext.random();
        ExtendedBlockStateConfiguration config = featurePlaceContext.config();

        for (BlockPos checkPos = pos.mutable(); checkPos.getY() > level.getMinBuildHeight()+3; checkPos.below()) {
            if (!level.isEmptyBlock(checkPos.below())) {
                BlockState state = level.getBlockState(checkPos);
                checkPos.above();
                if (config.placementTag().map(state::is).orElse(true)) {
                    pos = checkPos;
                    break;
                }
            }
        }

        if (pos.getY() <= level.getMinBuildHeight()+3) {
            return false;
        } else {
            for (int layer = 0; layer < 3; layer++) {
                int xOffset = randomSource.nextInt(2);
                int yOffset = randomSource.nextInt(2);
                int zOffset = randomSource.nextInt(2);
                float magicNumberIGuessTooLazyToLookIntoIt = (float)(xOffset + yOffset + zOffset) * 0.333F + 0.5F;

                for (BlockPos placementPos : BlockPos.betweenClosed(pos.offset(-xOffset, -yOffset, -zOffset), pos.offset(xOffset, yOffset, zOffset))) {
                    if (placementPos.distSqr(pos) <= (double)(magicNumberIGuessTooLazyToLookIntoIt * magicNumberIGuessTooLazyToLookIntoIt)) {
                        level.setBlock(placementPos, config.state(), 3);
                    }
                }
                pos = pos.offset(-1 + randomSource.nextInt(2), -randomSource.nextInt(2), -1 + randomSource.nextInt(2));
            }
        }
        return true;
    }
}
