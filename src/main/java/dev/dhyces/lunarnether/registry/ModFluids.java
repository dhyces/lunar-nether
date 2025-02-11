package dev.dhyces.lunarnether.registry;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModFluids {
    public static class Types {
        public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, LunarNether.MODID);

        public static final RegistryObject<FluidType> MOLTEN_TITANIUM = REGISTRY.register("molten_titanium", () -> new FluidType(FluidType.Properties.create().temperature(1300).lightLevel(10).canSwim(false).canDrown(false).pathType(BlockPathTypes.LAVA)) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public ResourceLocation getStillTexture() {
                        return LunarNether.id("block/molten_titanium_still");
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return LunarNether.id("block/molten_titanium_flow");
                    }
                });
            }
        });
    }

    public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(Registries.FLUID, LunarNether.MODID);

    public static final RegistryObject<ForgeFlowingFluid> MOLTEN_TITANIUM = REGISTRY.register("molten_titanium", () -> new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(Types.MOLTEN_TITANIUM, moltenTitanium(), flowingMoltenTitanium()).tickRate(30).slopeFindDistance(2).levelDecreasePerBlock(2).bucket(ModItems.MOLTEN_TITANIUM_BUCKET).block(ModBlocks.MOLTEN_TITANIUM)));
    public static final RegistryObject<ForgeFlowingFluid> MOLTEN_TITANIUM_FLOWING = REGISTRY.register("flowing_molten_titanium", () -> new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(Types.MOLTEN_TITANIUM, MOLTEN_TITANIUM, flowingMoltenTitanium()).tickRate(30).slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.MOLTEN_TITANIUM)));


    private static RegistryObject<ForgeFlowingFluid> moltenTitanium() {
        return MOLTEN_TITANIUM;
    }

    private static RegistryObject<ForgeFlowingFluid> flowingMoltenTitanium() {
        return MOLTEN_TITANIUM_FLOWING;
    }

//    public static final RegistryObject<ForgeFlowingFluid> MOLTEN_TITANIUM = REGISTRY.register("molten_titanium", () -> new ForgeFlowingFluid.Source())
//            .type(hot("molten_titanium").temperature(1300).lightLevel(10)).block(createBurning(MapColor.IRON, 8, 10, 6f)).bucket().commonTag().flowing();
}
