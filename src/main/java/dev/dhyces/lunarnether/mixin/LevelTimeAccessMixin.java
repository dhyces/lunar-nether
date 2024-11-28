package dev.dhyces.lunarnether.mixin;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelTimeAccess;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelTimeAccess.class)
public interface LevelTimeAccessMixin extends LevelReader {

    @Shadow long dayTime();

    @Inject(method = "getTimeOfDay", at = @At("HEAD"), cancellable = true)
    default void lunarnether$getTimeOfDay(float partialTick, CallbackInfoReturnable<Float> cir) {
        if (dimensionType() == registryAccess().registryOrThrow(Registries.DIMENSION_TYPE).get(BuiltinDimensionTypes.NETHER)) {
            cir.setReturnValue(LunarNether.netherTimeOfDay(dayTime()));
        }
    }
}
