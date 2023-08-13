package dev.dhyces.lunarnether;

import dev.dhyces.lunarnether.registry.BiomeKeys;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BiomeTagGen extends TagsProvider<Biome> {
    protected BiomeTagGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, Registries.BIOME, pLookupProvider, LunarNether.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BiomeTags.IS_NETHER)
                .add(
                        BiomeKeys.OUTROCKS,
                        BiomeKeys.ASHEN_PLAINS,
                        BiomeKeys.ICY_BARRENS
                );
    }
}
