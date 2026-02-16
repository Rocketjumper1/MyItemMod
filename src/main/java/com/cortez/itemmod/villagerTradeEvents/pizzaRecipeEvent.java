package com.cortez.itemmod.villagerTradeEvents;

import com.cortez.itemmod.ItemMod;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.network.Filterable;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.network.chat.Component;
import java.awt.*;

import java.util.List;

@Mod.EventBusSubscriber(modid= ItemMod.MOD_ID)
public class pizzaRecipeEvent {
    @SubscribeEvent
    public static void addPizzaRecipe(VillagerTradesEvent event){
        if(event.getType() == VillagerProfession.FARMER){
            List<VillagerTrades.ItemListing> trades = event.getTrades().get(5);
            trades.add((Entity entity, RandomSource random) -> {
                ItemStack pizza_recipe = new ItemStack(Items.WRITTEN_BOOK);
                List<Filterable<Component>> pages = List.of(
                        Filterable.passThrough(Component.literal("Add 2 parts cheese.")),
                        Filterable.passThrough(Component.literal("Then After that mix 2 parts marinara sauce on top")),
                        Filterable.passThrough(Component.literal("Finally add 2 parts cheese and 3 parts dough"))
                        );
                WrittenBookContent bookContent = new WrittenBookContent(
                        Filterable.passThrough("Pizza recipe"),
                        "a mysterious pizza maker",
                        0,
                        pages,
                        true);
                pizza_recipe.set(DataComponents.WRITTEN_BOOK_CONTENT, bookContent);
                return new MerchantOffer(
                        new ItemCost(Items.EMERALD, 40),
                        pizza_recipe,
                        1, 10000, 0.05f);



            });
        }
    }
}
