package com.cortez.itemmod.modTags;

import com.cortez.itemmod.ItemMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> UNDESTROYABLE_BLOCKS = createTag("undestroyable_blocks");
        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ItemMod.MOD_ID, name));
        }
    }
    public static class Items{
        public static final TagKey<Item> RANGE_ENCHANTMENT_SUPPORT =
                createTag("range_enchantment_support");
        public static final TagKey<Item> UNDESTROYABLE_ITEMS =
                createTag("undestroyable_items");
        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ItemMod.MOD_ID, name));
        }
    }
}
