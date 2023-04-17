package net.code.crossover.block.mario.entity;

import net.minecraft.nbt.CompoundTag;
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
    DirectionProperty WARPSHAPE = BlockStateProperties.FACING;


    VoxelShape WARP_UP = Stream.of(Block.box(1, 15, 1, 16, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    VoxelShape WARP_NORTH = Stream.of(Block.box(1, 15, 1, 16, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    VoxelShape WARP_EAST = Stream.of(Block.box(1, 2, 2, 3, 13, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    VoxelShape WARP_SOUTH = Stream.of(Block.box(2, 2, 1, 14, 13, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    VoxelShape WARP_WEST = Stream.of(Block.box(13, 2, 2, 15, 13, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


      default VoxelShape getWarpShape(BlockState pState) {
        switch (pState.getValue(WARPSHAPE)) {
            case UP:
                return WARP_UP;
            case NORTH:
                return WARP_NORTH;
            case EAST:
                return WARP_EAST;
            case SOUTH:
                return WARP_SOUTH;
            case WEST:
                return WARP_WEST;
            default:
                return WARP_UP;
        }
    }

    void readCompound(CompoundTag tag);

    void writeCompound(CompoundTag tag);

    double getLevelX();

    double getLevelY();

    double getLevelZ();

}