package dev.dhyces.lunarnether.mixin;

import dev.dhyces.lunarnether.LunarNetherClient;
import dev.dhyces.lunarnether.server.LunarTimeData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionType.class)
public abstract class DimensionTypeMixin {
    @Inject(method = "timeOfDay", at = @At(value = "HEAD"), cancellable = true)
    public void getTimeOfDay(long pDayTime, CallbackInfoReturnable<Float> cir) {
        Level level = LunarTimeData.currentNether;
        if (level != null && (Object) this == level.dimensionType()) {
            if (level.isClientSide()) {
                cir.setReturnValue(LunarTimeData.netherTimeOfDay(LunarNetherClient.netherDayTime));
            } else {
                cir.setReturnValue(LunarTimeData.netherTimeOfDay(LunarTimeData.getOrCreate((ServerLevel) level).getDaytime()));
            }
        }
    }
}
