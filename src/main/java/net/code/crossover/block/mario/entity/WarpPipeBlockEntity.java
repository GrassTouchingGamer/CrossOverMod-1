package net.code.crossover.block.mario.entity;

import net.code.crossover.block.ModBlockEntities;
import net.code.crossover.block.mario.WarpPipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class WarpPipeBlockEntity extends BlockEntity implements WarpPipe {

    private static final String DESTINATION = "Destination";
    @Nullable
    public BlockPos destination;
    public WarpPipeBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.WARP_PIPE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

        public static List<Entity> getEntities(Level level, WarpPipe warp, BlockState pState) {
            return warp.getWarpShape(pState).toAabbs().stream().flatMap((p_155558_) -> {
                return level.getEntitiesOfClass(Entity.class, p_155558_.move(warp.getLevelX() - 0.5D, warp.getLevelY() - 0.5D, warp.getLevelZ() - 0.5D), EntitySelector.LIVING_ENTITY_STILL_ALIVE).stream();
            }).collect(Collectors.toList());
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
    public double getLevelX() {
        return (double)this.worldPosition.getX() + 0.5D;
    }
    public double getLevelY() {
        return (double)this.worldPosition.getY() + 0.5D;
    }
    public double getLevelZ() {
        return (double)this.worldPosition.getZ() + 0.5D;
    }


}