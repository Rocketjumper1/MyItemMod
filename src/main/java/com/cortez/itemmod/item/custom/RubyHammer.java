package com.cortez.itemmod.item.custom;


import com.cortez.itemmod.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class RubyHammer extends DiggerItem {


    public RubyHammer(Tier tier, Properties properties){

        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }
    // make it work as multiple tools
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(BlockTags.MINEABLE_WITH_PICKAXE) ||
                state.is(BlockTags.MINEABLE_WITH_SHOVEL) ||
                state.is(BlockTags.MINEABLE_WITH_AXE);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return (state.is(BlockTags.MINEABLE_WITH_PICKAXE) ||
                state.is(BlockTags.MINEABLE_WITH_SHOVEL) ||
                state.is(BlockTags.MINEABLE_WITH_AXE)) ? this.getTier().getSpeed() : 1.0F;
    }
    // logic for hammer mining
    private Boolean isValid(int offsetX, int offsetY, int offsetZ, Level level, BlockPos pos){
        BlockState state = level.getBlockState(pos.offset(offsetX, offsetY, offsetZ));
        TagKey<Block> ore_tag = TagKey.create(Registries.BLOCK,
                ResourceLocation.parse("c:ores"));
        if(!state.is(ore_tag)){
            return true;
        }
        else return false;
    }
    private BlockPos getPos(Direction direction, Level level, int x, int y, BlockPos pos){
        if(direction.getAxis() == Direction.Axis.Y){
            if (isValid(x, 0, y, level, pos)) {
                return pos.offset(x, 0, y);
            }

        }
        if(direction.getAxis() == Direction.Axis.X){
            if (isValid(0, y, x, level, pos)) {
                return pos.offset(0, y, x);
            }
        }
        if(direction.getAxis() == Direction.Axis.Z){
            if (isValid(x, y, 0, level, pos)) {
                return pos.offset(x, y, 0);
            }

        }
        return null;
    }
    private List<BlockPos> getBlocks(BlockPos pos, Direction direction, int range, Level level){
        List<BlockPos> BlockPositons = new ArrayList<>();
        for(int i = -range; i <= range; i++){
            for(int j = -range; j <= range; j++){
                    if(getPos(direction, level, i, j, pos) != null) {
                        BlockPositons.add(getPos(direction, level, i, j, pos));
                    }
                }

        }
        return BlockPositons;
    }
    private static final ThreadLocal<Boolean> is_mining = ThreadLocal.withInitial(() -> false);
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        if(is_mining.get()) return false;
        Level level = player.level();
        if(!level.isClientSide() && player instanceof ServerPlayer serverPlayer){
            HitResult ray = player.pick(20, 0, false);
            if(ray.getType() == HitResult.Type.BLOCK){
                var registry_access = level.registryAccess();
                var enchantmentRegistry = registry_access.lookupOrThrow(Registries.ENCHANTMENT);
                var hammerRangeHolder = enchantmentRegistry.getOrThrow(ModEnchantments.HAMMER_RANGE);
                ItemEnchantments itemEnchantments = itemstack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
                int range = itemEnchantments.getLevel(hammerRangeHolder) + 1;
                is_mining.set(true);
                try{
                    Direction side = ((BlockHitResult) ray).getDirection();
                    List<BlockPos> areaToBreak = getBlocks(pos, side, range, level);

                    for(BlockPos targetPos : areaToBreak) {
                        if (targetPos.equals(pos)) continue;
                        BlockState state = level.getBlockState(targetPos);

                        if (this.isCorrectToolForDrops(itemstack, state)) {
                            serverPlayer.gameMode.destroyBlock(targetPos);
                        }
                    }
                }
                finally {
                    is_mining.set(false);
                }
            }

        }
        return false;

    }
}
