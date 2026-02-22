package com.cortez.itemmod.datagen;

import com.cortez.itemmod.block.ModBlocks;
import com.cortez.itemmod.item.ModItems;
import com.cortez.itemmod.modTags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {


    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.PICKAXES)
                .add(ModItems.RUBY_PICKAXE.get())
                .add(ModItems.RUBY_HAMMER.get());



        tag(ItemTags.SWORDS)
                .add(ModItems.RUBY_SWORD.get());
        tag(ModTags.Items.RANGE_ENCHANTMENT_SUPPORT)
                .add(ModItems.RUBY_HAMMER.get());
        tag(ModTags.Items.UNDESTROYABLE_ITEMS)
                .add(Items.NETHERITE_BLOCK)
                .add(ModBlocks.RUBY_BLOCK.get().asItem());


    }
}
