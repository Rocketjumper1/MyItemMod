package com.cortez.itemmod.modSounds;

import com.cortez.itemmod.ItemMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ItemMod.MOD_ID);
    public static final RegistryObject<SoundEvent> INCINERATOR_SOUND = SOUNDS.register("incinerator",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ItemMod.MOD_ID, "incinerator")));
    public static void register(IEventBus bus){
        SOUNDS.register(bus);
    }

}
