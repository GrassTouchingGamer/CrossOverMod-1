package net.code.crossover.block.mario.entity;

import net.code.crossover.block.ModBlockEntities;
import net.code.crossover.block.mario.WarpPipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class WarpPipeBlockEntity extends BlockEntity implements WarpShape {

    private static final String DESTINATION = "Destination";
    @Nullable
    public BlockPos destination;
    public WarpPipeBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.WARP_PIPE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }


    private boolean hasDestination() {
        return this.destination != null;
    }

    public void setDestination(@Nullable BlockPos pPos) {
        this.destination = pPos;
        if (this.level != null) {
            BlockState blockState = this.getBlockState();
            this.level.setBlockAndUpdate(this.getBlockPos(), blockState.setValue(WarpPipeBlock.LINKED, destination != null));
        }
    }
    @Override
    public void readCompound(CompoundTag tag) {
        super.load(tag);
        this.destination = null;
        if (tag.contains(DESTINATION)) {
            this.setDestination(NbtUtils.readBlockPos(tag.getCompound(DESTINATION)));
        }
    }
    @Override
    public void writeCompound(CompoundTag tag) {
        super.load(tag);
        if (this.hasDestination()) {
            tag.put(DESTINATION, NbtUtils.writeBlockPos(this.destination));
        }
    }
}