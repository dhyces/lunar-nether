package dev.dhyces.lunarnether.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.dhyces.lunarnether.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;

public class Crater extends Feature<NoneFeatureConfiguration> {

    public Crater(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        RandomSource randomsource = context.random();
        BlockPos blockpos = context.origin();
        int[] layerRadii = {7, 6, 4, 2};
        for (int layerRadius : layerRadii) {
            int outerRadius = layerRadius + randomsource.nextInt(3);
            int innerRadius = outerRadius - 1;
            if (layerRadius > layerRadii.length - 2) {
                createLayer(innerRadius, outerRadius, worldgenlevel, randomsource, blockpos, null);
            }
            createLayer(innerRadius, outerRadius, worldgenlevel, randomsource, blockpos, Blocks.AIR.defaultBlockState());
            blockpos = blockpos.below();
        }

        return true;
    }

    public void createLayer(int innerRadius, int outerRadius, WorldGenLevel level, RandomSource random, BlockPos origin, @Nullable BlockState block) {
            for (int x = -outerRadius; x <= outerRadius; x++) {
                for (int z = -outerRadius; z <= outerRadius; z++) {
                    if (x * x + z * z < innerRadius * innerRadius && block != null) {
                        BlockPos pos = origin.offset(x, 0, z);
                        this.setBlock(level, pos, block);
                    } else if (x * x + z * z < outerRadius * outerRadius && block == null) {
                        BlockPos pos = origin.offset(x, 0, z);
                        int randomInt = random.nextInt(4);
                        BlockState blockToPut;
                        if (randomInt == 0) {
                            blockToPut = ModBlocks.ILMENITE_ORE.get().defaultBlockState();
                        } else if (randomInt == 1 || randomInt == 2) {
                            blockToPut = ModBlocks.LUNAR_STONE.get().defaultBlockState();
                        } else {
                            blockToPut = ModBlocks.RAW_ILMENITE_BLOCK.get().defaultBlockState();
                        }
                        this.setBlock(level, pos, blockToPut);
                    }
                }

        }
    }
}

