package com.cortez.itemmod.enchantment.custom;



import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record HammerRangeEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<HammerRangeEffect> CODEC = MapCodec.unit(HammerRangeEffect::new);
    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        /*
        for(int i = 0; i < pEnchantmentLevel; i++) {
            EnderMan enderMan = EntityType.ENDERMAN.create(pLevel);

            if (enderMan != null) {
                enderMan.setPos(pEntity.getPosition(0));
                var scaleAttribute = enderMan.getAttribute(Attributes.SCALE);
                if (scaleAttribute != null) {
                    scaleAttribute.setBaseValue(i);
                }
                pLevel.addFreshEntity(enderMan);

            }
        }
        */



    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
