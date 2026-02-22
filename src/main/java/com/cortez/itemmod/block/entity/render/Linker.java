package com.cortez.itemmod.block.entity.render;

import com.cortez.itemmod.ItemMod;

import com.cortez.itemmod.block.entity.ModBlockEntitys;
import com.cortez.itemmod.block.entity.render.renderers.IncineratorRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= ItemMod.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Linker {
    @SubscribeEvent
    public static void renderLinker(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntitys.INCINERATOR_BE.get(), IncineratorRenderer::new);

    }
}
