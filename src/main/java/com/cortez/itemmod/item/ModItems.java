package com.cortez.itemmod.item;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, ItemMod.MOD_ID);
    public static final RegistryObject<Item> RUBY_PICKAXE = Items.register("ruby_pickaxe", () -> new PickaxeItem(
            Tiers.NETHERITE,

            new Item.Properties().attributes(PickaxeItem.createAttributes(Tiers.NETHERITE, 1, -2.8f)).
                    rarity(Rarity.EPIC).
                    stacksTo(1).
                    durability(2056)
            ));
    public static final RegistryObject<Item> TOMATO_SEEDS = Items.register("tomato_seeds", () ->
            new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RUBY = Items.register("ruby", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY_SWORD = Items.register("ruby_sword", () -> new SwordItem(
            Tiers.NETHERITE,
            new Item.Properties().attributes(
                    SwordItem.createAttributes(
                            Tiers.NETHERITE,
                            9,
                            5
                            )

            )
                    .rarity(Rarity.EPIC)
                    .stacksTo(1)
                    .durability(2056)
    ));
    public static final RegistryObject<Item> CHEESE = Items.register("cheese", () -> new Item(new Item.Properties().rarity(Rarity.RARE).food( new FoodProperties.
            Builder().
            nutrition(4).
            saturationModifier(0.7f).
            build()).
                stacksTo(64)));
    public static final RegistryObject<Item> TOMATO = Items.register("tomato", () -> new Item(new Item.Properties().rarity(Rarity.RARE).food( new FoodProperties.
                    Builder().
                    nutrition(3).
                    saturationModifier(0.7f).
                    build()).
            stacksTo(64)));
    public static void register(IEventBus eventBus){
        Items.register(eventBus);

    }
}
