package dev.dhyces.lunarnether.server;

import dev.dhyces.lunarnether.networking.LunarNetherNetwork;
import dev.dhyces.lunarnether.networking.SyncLunarTimeS2CPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import javax.annotation.Nullable;

public class LunarTimeData extends SavedData {
    @Nullable
    public static Level currentNether;

    private long daytime;
    private ResourceKey<Level> dimension;

    private LunarTimeData() {
        this(0);
    }

    private LunarTimeData(CompoundTag tag) {
        this(tag.getLong("daytime"));
    }

    private LunarTimeData(long daytime) {
        this.daytime = daytime;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putLong("daytime", daytime);
        return tag;
    }

    public static LunarTimeData getOrCreate(ServerLevel level) {
        LunarTimeData data = level.getDataStorage().computeIfAbsent(factory(level), "lunarnether_time");
        data.dimension = level.dimension();
        return data;
    }

    public static SavedData.Factory<LunarTimeData> factory(ServerLevel level) {
        return new Factory<>(LunarTimeData::new, (compoundTag, provider) -> new LunarTimeData(compoundTag));
    }

    public long getDaytime() {
        return daytime;
    }

    public void update(long overworldDaytime) {
        if (daytime != overworldDaytime) {
            daytime = overworldDaytime;
//            LunarNetherNetwork.sendToPlayersInLevel(dimension, new SyncLunarTimeS2CPacket(daytime));
            setDirty();
        }
    }

    public static float netherTimeOfDay(long daytime) {
        double decimal = Mth.frac(daytime / (24000.0 * 8) - 0.25);
        double d1 = 0.5 - Math.cos(decimal * Math.PI) / 2;
        return (float)(decimal * 2 + d1) / 3.0F;
    }
}
