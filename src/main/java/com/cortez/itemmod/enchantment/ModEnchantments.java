package com.cortez.itemmod.enchantment;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.enchantment.custom.HammerRangeEffect;
import com.cortez.itemmod.modTags.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> HAMMER_RANGE =
            ResourceKey.create(
                    Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(ItemMod.MOD_ID, "hammer_range")
            );

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);
        register(context,
                HAMMER_RANGE,
                 Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.RANGE_ENCHANTMENT_SUPPORT),
                                items.getOrThrow(ModTags.Items.RANGE_ENCHANTMENT_SUPPORT),
                                5,
                                255,
                                Enchantment.dynamicCost(1, 5),
                                Enchantment.dynamicCost(10, 5),
                                2,
                                EquipmentSlotGroup.ANY

                        )
                ).withEffect(EnchantmentEffectComponents.POST_ATTACK,
                         EnchantmentTarget.ATTACKER,
                         EnchantmentTarget.VICTIM,
                         new HammerRangeEffect()));



    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}

