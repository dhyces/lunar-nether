package dev.dhyces.lunarnether;

import dev.dhyces.biomeextensions.ApiAccess;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = LunarNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen {

    @SubscribeEvent
    static void datagen(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        RegistrySetBuilder builder = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, ConfiguredFeaturesGen::run)
                .add(Registries.PLACED_FEATURE, PlacedFeaturesGen::run)
                .add(Registries.BIOME, BiomeGen::run)
                .add(ApiAccess.EXTENSION_REGISTRY_KEY, BiomeExtensionGen::run);

        DatapackBuiltinEntriesProvider datapackProvider = generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, builder, Set.of(LunarNether.MODID)));
        lookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(event.includeServer(), new RecipeGen(packOutput));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Set.of(), List.of(new LootTableProvider.SubProviderEntry(BlockLootSubGen::new, LootContextParamSets.BLOCK))));
        CompletableFuture<TagsProvider.TagLookup<Block>> getter = generator.addProvider(event.includeServer(), new BlockTagGen(packOutput, lookupProvider, fileHelper)).contentsGetter();
        generator.addProvider(event.includeServer(), new ItemTagGen(packOutput, lookupProvider, getter, fileHelper));
        generator.addProvider(event.includeServer(), new BiomeTagGen(packOutput, lookupProvider, fileHelper));

        generator.addProvider(event.includeClient(), new BlockStateGen(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ItemModelGen(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new EnUsLangGen(packOutput));
    }
}
