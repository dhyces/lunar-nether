package dev.dhyces.lunarnether.data;

import dev.dhyces.biomeextensions.ApiAccess;
import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, builder, Set.of(LunarNether.MODID)));

        generator.addProvider(event.includeClient(), new BlockStateGen(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ItemModelGen(packOutput, fileHelper));
    }
}
