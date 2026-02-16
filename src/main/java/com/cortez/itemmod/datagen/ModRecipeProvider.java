package com.cortez.itemmod.datagen;

import com.cortez.itemmod.block.ModBlocks;
import com.cortez.itemmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
         ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                 .pattern("RRR")
                 .pattern("RRR")
                 .pattern("RRR")
                 .define('R', ModItems.RUBY.get())
                 .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                 .save(pRecipeOutput);
         ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.INCINERATOR_BLOCK.get())
                     .pattern("IRI")
                     .pattern("RSR")
                     .pattern("IRI")
                     .define('I', Items.IRON_INGOT)
                     .define('R', Items.REDSTONE)
                     .define('S', ModItems.RUBY.get())
                     .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                     .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()));


    }
}
