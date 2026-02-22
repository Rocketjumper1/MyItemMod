package com.cortez.itemmod.enchantment;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.enchantment.custom.HammerRangeEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>>
            ENCHANTMENT_ENTITY_EFFECTS = DeferredRegister
            .create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ItemMod.MOD_ID);
    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> HAMMER_RANGE_EFFECT =
            ENCHANTMENT_ENTITY_EFFECTS.register("hammer_range_effect",
                    () -> HammerRangeEffect.CODEC);
    public static void register(IEventBus bus){
        ENCHANTMENT_ENTITY_EFFECTS.register(bus);
    }
}
