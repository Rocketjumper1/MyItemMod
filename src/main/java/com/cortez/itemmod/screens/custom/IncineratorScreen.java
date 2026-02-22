package com.cortez.itemmod.screens.custom;


import com.cortez.itemmod.ItemMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IncineratorScreen extends AbstractContainerScreen<IncineratorMenu> {
    private static final ResourceLocation GUI_LOCATION =
            ResourceLocation.fromNamespaceAndPath(ItemMod.MOD_ID,"textures/gui/incinerator.png");

    public IncineratorScreen(IncineratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, GUI_LOCATION);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(GUI_LOCATION, x, y, 0, 0, imageHeight, imageWidth);
    }
}
