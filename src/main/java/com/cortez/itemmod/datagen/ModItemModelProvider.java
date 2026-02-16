package com.cortez.itemmod.datagen;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ItemMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    basicItem(ModItems.CHEESE.get());
    basicItem(ModItems.RUBY_PICKAXE.get());
    basicItem(ModItems.RUBY.get());
    basicItem(ModItems.RUBY_SWORD.get());
    basicItem(ModItems.TOMATO_SEEDS.get());
    basicItem(ModItems.TOMATO.get());
    }
}
