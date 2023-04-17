package net.code.crossover.block.mario.entity;

import net.code.crossover.block.ModBlockEntities;
import net.code.crossover.block.mario.WarpPipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.entity.HopperBlockEntity.addItem;

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

    public static boolean addEntity(Entity entity) {
        boolean flag = false;
        entity.getPose();
        return flag;
    }


    public static boolean entityTeleport(Level level, BlockPos pPos, BlockState pState, Entity entity, WarpPipeBlockEntity warpPipeBlock) {
        if (entity instanceof Entity && Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox()), warpPipeBlock.getWarpShape(pState), BooleanOp.AND)) {
                return addEntity(entity);};
        return true;
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