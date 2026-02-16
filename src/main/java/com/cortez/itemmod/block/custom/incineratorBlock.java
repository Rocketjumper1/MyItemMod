package com.cortez.itemmod.block.custom;


import com.cortez.itemmod.ModSounds.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class incineratorBlock extends Block {
    public incineratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        boolean is_powered = pLevel.hasNeighborSignal(pPos);
        if(pEntity instanceof ItemEntity itemEntity && is_powered){
            itemEntity.discard();
            pLevel.playSound(null, pPos, ModSounds.INCINERATOR_SOUND.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
        }
    }
}
