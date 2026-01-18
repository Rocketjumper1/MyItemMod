package com.cortez.itemmod.item;

import com.cortez.itemmod.ItemMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, ItemMod.MOD_ID);
    public static final RegistryObject<Item> RUBY = Items.register("ruby", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY_SWORD = Items.register("ruby_sword", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        Items.register(eventBus);

    }
}
