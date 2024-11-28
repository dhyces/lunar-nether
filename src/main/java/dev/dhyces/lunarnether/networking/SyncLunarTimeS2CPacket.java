package dev.dhyces.lunarnether.networking;

import dev.dhyces.lunarnether.LunarNetherClient;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public record SyncLunarTimeS2CPacket(long time) {
    public SyncLunarTimeS2CPacket(FriendlyByteBuf buf) {
        this(buf.readLong());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeLong(time);
    }

//    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
//        contextSupplier.get().enqueueWork(() -> {
//            LunarNetherClient.netherDayTime = time;
//        });
//    }
}
