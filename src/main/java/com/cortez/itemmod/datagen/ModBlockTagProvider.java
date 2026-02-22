package com.cortez.itemmod.datagen;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ItemMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RUBY_ORE_STONE.get())
                .add(ModBlocks.RUBY_ORE_DEEPSLATE.get())
                .add(ModBlocks.RUBY_SLAB.get())
                .add(ModBlocks.RUBY_BLOCK.get())
                .add(ModBlocks.INCINERATOR_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RUBY_ORE_STONE.get())
                .add(ModBlocks.RUBY_ORE_DEEPSLATE.get())
                .add(ModBlocks.RUBY_BLOCK.get())
                .add(ModBlocks.RUBY_SLAB.get())
                .add(ModBlocks.INCINERATOR_BLOCK.get());



    }
}
