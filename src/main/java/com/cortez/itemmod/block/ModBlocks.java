package com.cortez.itemmod.block;

import com.cortez.itemmod.ItemMod;

import com.cortez.itemmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> Blocks = DeferredRegister.create(ForgeRegistries.BLOCKS , ItemMod.MOD_ID);
    public static final RegistryObject<Block> RUBY_ORE_STONE = registerBlock("ruby_ore_stone", () -> new Block(BlockBehaviour.Properties.
            of().
            strength(3f).
            requiresCorrectToolForDrops().
            sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> RUBY_ORE_DEEPSLATE = registerBlock("ruby_ore_deepslate", () -> new Block(BlockBehaviour.Properties.
            of().
            strength(5f).
            requiresCorrectToolForDrops().
            sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> RUBY_SLAB = registerBlock("ruby_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANVIL)));
    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block", () -> new Block(BlockBehaviour.Properties
            .of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANVIL)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> returnValue = Blocks.register(name, block);
        registerBlockItem(name, returnValue);
        return returnValue;

    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.Items.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus event){
        Blocks.register(event);
    }
}
