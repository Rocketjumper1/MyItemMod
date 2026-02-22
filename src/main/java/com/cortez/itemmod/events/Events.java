package com.cortez.itemmod.events;

import com.cortez.itemmod.ItemMod;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= ItemMod.MOD_ID)
public class Events {
    @SubscribeEvent
    public static void hammerMiningLogic(BlockEvent.BreakEvent event){

    }
}
