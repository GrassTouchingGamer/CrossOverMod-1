package net.yebbow.crossover.block.mario;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;
public class portalwarppipe extends Block {
    public portalwarppipe(Properties properties) {
        super(properties);
        //      this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
        //  }
        // public static final DirectionProperty FACING = BlockStateProperties.FACING;

        // public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        //     switch (pState.getValue(FACING)) {
        //         case NORTH:
        //             return PIPEUP;
        //         case SOUTH:
        //             return PIPEUP;
        //         case WEST:
        //             return PIPEUP;
        //         case EAST:
        //             return PIPEUP;
        //         case UP:
        //             return PIPEUP;
        //         case DOWN:
        //             return PIPEUP;
        //         default:
        //             return PIPEUP;
        //     }
        // }
        // public static final VoxelShape PIPEUP = Stream.of(
        //         Block.box(0, 13, 0, 1, 17, 16),
        //         Block.box(15, 13, 0, 16, 17, 16),
        //         Block.box(1, 13, 15, 15, 17, 16),
        //         Block.box(1, 13, 0, 15, 17, 1)
        // ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    }
}