package net.code.crossover.block.mario.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;
public interface WarpShape {


    VoxelShape WARP_UP = Stream.of(Block.box(1, 15, 1, 15, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    VoxelShape WARP_NORTH = Stream.of(Block.box(0, 0, 0, 0, 0, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    VoxelShape WARP_EAST = Stream.of(Block.box(1, 2, 2, 3, 13, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    VoxelShape WARP_SOUTH = Stream.of(Block.box(2, 2, 1, 14, 13, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    VoxelShape WARP_WEST = Stream.of(Block.box(13, 2, 2, 15, 13, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    default VoxelShape getWarpUp() {
                return WARP_UP;
    }
      default VoxelShape getWarpNorth() {
                return WARP_NORTH;
    }

    void readCompound(CompoundTag tag);

    void writeCompound(CompoundTag tag);
}