package com.cortez.itemmod.block.entity.render.renderers;

import com.cortez.itemmod.block.entity.custom.IncineratorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class IncineratorRenderer implements BlockEntityRenderer<IncineratorBlockEntity> {
    Font font;
    public IncineratorRenderer(BlockEntityRendererProvider.Context context){
        this.font = context.getFont();
    }

    @Override
    public void render(IncineratorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource,
                       int pPackedLight, int pPackedOverlay) {
        ItemStack item = pBlockEntity.getDebugItem();
        if(!item.isEmpty()) {
            Component text = item.getHoverName();

            pPoseStack.pushPose();

            pPoseStack.translate(0.5D, 2.0D, 0.5D);

            pPoseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());

            pPoseStack.scale(0.025f, -0.025f, 0.025f);
            float xOffset = (float) (-font.width(text) / 2);
            font.drawInBatch(text, xOffset, 0, 0xFFFFFF, true, pPoseStack.last().pose(),
                    pBufferSource, Font.DisplayMode.NORMAL, 0, 15728880);

            pPoseStack.popPose();

            pPoseStack.pushPose();

            pPoseStack.translate(0.5D, 1.0D, 0.5D);

            float rotationSpeed = 4.0f;
            float rotation = (pBlockEntity.getLevel().getGameTime() + pPartialTick) * rotationSpeed;

            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotation));

            Minecraft.getInstance().getItemRenderer().renderStatic(
                    item,
                    ItemDisplayContext.GROUND,
                    pPackedLight,
                    pPackedOverlay,
                    pPoseStack,
                    pBufferSource,
                    pBlockEntity.getLevel(),
                    0

            );

            pPoseStack.popPose();
        }
    }
}
