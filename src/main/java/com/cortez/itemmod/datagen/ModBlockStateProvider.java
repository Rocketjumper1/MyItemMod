package com.cortez.itemmod.datagen;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ItemMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.RUBY_ORE_STONE);
        slabBlock((SlabBlock) ModBlocks.RUBY_SLAB.get(), modLoc("block/ruby_block"), modLoc("block/ruby_block"));
        blockWithItem(ModBlocks.RUBY_ORE_DEEPSLATE);
        simpleBlockItem(ModBlocks.RUBY_SLAB.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/ruby_slab_top")));
        threeTextureBlock(ModBlocks.INCINERATOR_BLOCK, modLoc("block/incinerator_side"), modLoc("block/incinerator_bottom"), modLoc("block/incinerator_top"));
        makeCrop(((CropBlock) ModBlocks.TOMATO_CROP.get()), "tomato_stage", "tomato_stage");




    }
    public void makeCrop(CropBlock block, String modelName, String textureName) {
        // We use the generic 'block' variable so it works for ANY crop
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {

        IntegerProperty ageProperty = (IntegerProperty) state.getBlock().getStateDefinition().getProperty("age");
        int age = state.getValue(ageProperty);


        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(
                modelName + age, // Model name (e.g., kohlrabi_stage0)
                ResourceLocation.fromNamespaceAndPath(ItemMod.MOD_ID, "block/" + textureName + age) // Texture path
        ).renderType("cutout"));

        return models;
    }

    private void threeTextureBlock(RegistryObject<Block> block, ResourceLocation side,  ResourceLocation bottom, ResourceLocation top){
        simpleBlockWithItem(block.get(), models().cubeBottomTop(block.getId().getPath(), side, bottom, top));
    }
    private void blockWithItem(RegistryObject<Block> registryBlock){
        simpleBlockWithItem(registryBlock.get(), cubeAll(registryBlock.get()));
    }
}
