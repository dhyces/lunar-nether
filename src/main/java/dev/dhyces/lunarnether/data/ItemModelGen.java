package dev.dhyces.lunarnether.data;

import dev.dhyces.lunarnether.LunarNether;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGen extends ItemModelProvider {
    public ItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LunarNether.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("luna_rock", LunarNether.id("block/luna_rock"));
    }
}
