package com.cortez.itemmod.block.custom;


import com.cortez.itemmod.modSounds.ModSounds;
import com.cortez.itemmod.block.entity.custom.IncineratorBlockEntity;
import com.cortez.itemmod.modTags.ModTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;



public class incineratorBlock extends BaseEntityBlock {
    MapCodec Codec = simpleCodec(incineratorBlock::new);

    @Override
    protected RenderShape getRenderShape(BlockState pState) {

        return RenderShape.MODEL;
    }
    public incineratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return Codec;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        boolean is_powered = pLevel.hasNeighborSignal(pPos);
        if(pEntity instanceof ItemEntity itemEntity && is_powered){
            if(isValid(itemEntity)) {
                if(pLevel.getBlockEntity(pPos) instanceof IncineratorBlockEntity incineratorBlockEntity){
                    incineratorBlockEntity.setIncinerated(itemEntity.getItem());
                }
                itemEntity.discard();

                pLevel.playSound(null, pPos, ModSounds.INCINERATOR_SOUND.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
            }
            else{
                pLevel.playSound(null, pPos, ModSounds.INCINERATOR_SOUND.get(), SoundSource.BLOCKS, 1.0f, 0.25f);
            }
        }

    }
    private boolean isValid(ItemEntity itemEntity){
        return !itemEntity.getItem().is(ModTags.Items.UNDESTROYABLE_ITEMS);
    }
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new IncineratorBlockEntity(pPos, pState);
    }
    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (!pState.is(pNewState.getBlock())) {
            // Your logic here
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof IncineratorBlockEntity incineratorBlockEntity) {
                incineratorBlockEntity.drops();
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        }
    }
    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {

        if(pLevel.getBlockEntity(pPos) instanceof IncineratorBlockEntity incineratorBlockEntity){
            if(!pLevel.isClientSide() && pPlayer.isCrouching()){
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(
                        incineratorBlockEntity,
                        Component.literal("Incinerator")
                        ), pPos);
                return ItemInteractionResult.SUCCESS;
            }
            else if(pPlayer.isCrouching()){
                return ItemInteractionResult.SUCCESS;
            }
            if(!pLevel.isClientSide()) {


                if ((incineratorBlockEntity.inventory.getStackInSlot(0).isEmpty()) || ItemStack.isSameItemSameComponents(
                        incineratorBlockEntity.inventory.getStackInSlot(0),
                        pStack
                )) {
                    incineratorBlockEntity.inventory.insertItem(0, pStack.copyWithCount(1), false);
                    pStack.shrink(1);
                    pLevel.playSound(null, pPos, ModSounds.INCINERATOR_SOUND.get(), SoundSource.BLOCKS, 1f, 1f);

                } else {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                }
            }


        }
        return ItemInteractionResult.SUCCESS;
    }
    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if(!pLevel.isClientSide()){
            if(pLevel.getBlockEntity(pPos) instanceof IncineratorBlockEntity incineratorBlockEntity){
                if(!incineratorBlockEntity.inventory.getStackInSlot(0).isEmpty()){
                    ItemStack incineratorStack = incineratorBlockEntity.inventory.extractItem(0, 1, false);
                    pPlayer.getInventory().add(incineratorStack.copy());
                    pLevel.playSound(null, pPos, ModSounds.INCINERATOR_SOUND.get(), SoundSource.BLOCKS, 1f, 2f);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

}
