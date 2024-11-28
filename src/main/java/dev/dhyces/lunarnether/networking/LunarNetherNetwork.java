package dev.dhyces.lunarnether.networking;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class LunarNetherNetwork {
    private static final String PROTOCOL_VERSION = "1";
//    private static SimpleChannel CHANNEL;

//    public static void register() {
//        int id = 0;
//
//        CHANNEL = NetworkRegistry.ChannelBuilder
//                .named(new ResourceLocation(LunarNether.MODID, "messages"))
//                .networkProtocolVersion(() -> PROTOCOL_VERSION)
//                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
//                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
//                .simpleChannel();
//
//        CHANNEL.messageBuilder(SyncLunarTimeS2CPacket.class, id++, NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(SyncLunarTimeS2CPacket::new)
//                .encoder(SyncLunarTimeS2CPacket::encode)
//                .consumerMainThread(SyncLunarTimeS2CPacket::handle)
//                .add();
//    }
//
//    public static <T> void sendToPlayersInLevel(ResourceKey<Level> dimension, T message) {
//        CHANNEL.send(PacketDistributor.DIMENSION.with(() -> dimension), message);
//    }
}
