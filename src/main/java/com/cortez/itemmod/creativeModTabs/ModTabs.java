package com.cortez.itemmod.creativeModTabs;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.block.ModBlocks;
import com.cortez.itemmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Display;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ItemMod.MOD_ID);
    public static final RegistryObject<CreativeModeTab> RUBY_ITEMS = TABS.register("ruby_items_tab",
            () -> CreativeModeTab.builder().
            icon(() -> new ItemStack(ModItems.RUBY_SWORD.get())).
            title(Component.translatable("itemmod.creativetab.ruby.items")).
            displayItems((ItemDisplayParameters, output) -> {
                output.accept(ModItems.RUBY.get());
                output.accept(ModItems.RUBY_SWORD.get());
                output.accept(ModItems.RUBY_PICKAXE.get());
                output.accept(ModItems.TOMATO_SEEDS.get());

            }).
            build());
    public static final RegistryObject<CreativeModeTab> COOKING = TABS.register("cooking_tab", () -> CreativeModeTab.builder()
            .withTabsAfter(RUBY_ITEMS.getId()).
            icon(() -> new ItemStack(ModItems.CHEESE.get(), 5)).
            title(Component.translatable("itemmod.creativetab.cooking")).
            displayItems((CreativeModeTab.ItemDisplayParameters ItemDisplayParameters, CreativeModeTab.Output output) -> {
                output.accept(ModItems.CHEESE.get());
            }).
            build());
    public static final RegistryObject<CreativeModeTab> RUBY_BLOCKS = TABS.register("ruby_blocks_tab",
            () -> CreativeModeTab.builder().
                    withTabsBefore(RUBY_ITEMS.getId()).
                    icon(() -> new ItemStack(ModBlocks.RUBY_BLOCK.get())).
                    title(Component.translatable("itemmod.creativetab.ruby.blocks")).
                    displayItems((ItemDisplayParameters, output) -> {
                        output.accept(ModBlocks.RUBY_SLAB.get());
                        output.accept(ModBlocks.RUBY_BLOCK.get());
                        output.accept(ModBlocks.RUBY_ORE_DEEPSLATE.get());
                        output.accept(ModBlocks.RUBY_ORE_STONE.get());
                        output.accept(ModBlocks.INCINERATOR_BLOCK.get());
                    }).
                    build());

    public static void register(IEventBus Bus){
        TABS.register(Bus);
    }
}

