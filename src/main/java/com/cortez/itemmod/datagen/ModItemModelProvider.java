package com.cortez.itemmod.datagen;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.item.ModItems;
import net.minecraft.ResourceLocationException;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ItemMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CHEESE.get());
        basicItem(ModItems.RUBY.get());
        basicItem(ModItems.RUBY_SWORD.get());
        basicItem(ModItems.TOMATO_SEEDS.get());
        basicItem(ModItems.TOMATO.get());
        handheldItem(ModItems.RUBY_PICKAXE);
        handheldItem(ModItems.RUBY_HAMMER);
        handheldItem(ModItems.RUBY_SWORD);


    }
    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ItemMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
