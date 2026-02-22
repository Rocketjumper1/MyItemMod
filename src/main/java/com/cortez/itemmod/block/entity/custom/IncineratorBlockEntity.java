package com.cortez.itemmod.block.entity.custom;


import com.cortez.itemmod.block.ModBlocks;
import com.cortez.itemmod.block.entity.ModBlockEntitys;
import com.cortez.itemmod.modSounds.ModSounds;
import com.cortez.itemmod.screens.custom.IncineratorMenu;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IncineratorBlockEntity extends BlockEntity implements MenuProvider {

    // constructor
    public IncineratorBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntitys.INCINERATOR_BE.get(), pPos, pBlockState);
    }



    //inventory/destroy manager
    public final ItemStackHandler inventory = new ItemStackHandler(2){
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack){
            return 64;
        }
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
            if (!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public void drops() {
        SimpleContainer drops = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++) {
            drops.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(level, this.worldPosition, drops);
    }
    public static void tick(Level level, BlockPos pos, BlockState state, IncineratorBlockEntity blockEntity) {

        if (!level.isClientSide) {
            blockEntity.doServerTick(level, pos);
        } else {
        }
    }

    private void doServerTick(Level level, BlockPos pos) {
        ItemStack itemStack = inventory.getStackInSlot(0);
        if(!itemStack.isEmpty()){
            itemStack.shrink(1);
            level.playSound(null, pos, ModSounds.INCINERATOR_SOUND.get(), SoundSource.BLOCKS, 0.25f, 2f);

        }

    }
    // Save/packet managing
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag tag = super.getUpdateTag(pRegistries);
        saveAdditional(tag, pRegistries);
        return tag;

    }
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory", inventory.serializeNBT(pRegistries));
        if(!renderDebugItem.isEmpty()) {
            pTag.put("incinerated_last", renderDebugItem.save(pRegistries));
        }

    }
    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        inventory.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        if(pTag.contains("incinerated_last") &&
        !ItemStack.parse(pRegistries, pTag.getCompound("incinerated_last")).orElse(ItemStack.EMPTY).isEmpty()) {
            renderDebugItem = ItemStack.parse(pRegistries, pTag.getCompound("incinerated_last")).orElse(ItemStack.EMPTY);
        }
    }
    //Debug text managing
    private ItemStack renderDebugItem = ItemStack.EMPTY;
    public ItemStack getDebugItem(){
        return renderDebugItem;

    }
    public void setIncinerated(ItemStack item){
        this.renderDebugItem = item;
        this.setChanged();
        if(this.level != null && !this.level.isClientSide()){
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }
    // Menu provider
    @Override
    public Component getDisplayName() {
        return Component.literal("Incinerator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new IncineratorMenu(pContainerId, pPlayerInventory, this);
    }
}
