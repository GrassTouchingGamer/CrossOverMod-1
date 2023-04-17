package net.code.crossover.block.mario;

import net.code.crossover.block.ModBlockEntities;
import net.code.crossover.block.mario.entity.QuestionBlockEntity;
import net.code.crossover.block.mario.entity.WarpPipeBlockEntity;
import net.code.crossover.block.mario.entity.WarpShape;
import net.code.crossover.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

import static net.minecraft.world.level.block.DirectionalBlock.FACING;

public class WarpPipeBlock extends BaseEntityBlock {


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.WARP_PIPE_BLOCK_ENTITY.get().create(pPos, pState);
    }

    public static final BooleanProperty LINKED = BooleanProperty.create("linked");

    private VoxelShape SHAPE_U = Stream.of(
                Block.box(1, 0, 1, 15, 15, 15),
                Block.box(0, 12, 0, 16, 16, 1),
                Block.box(0, 12, 15, 16, 16, 16),
                Block.box(0, 12, 1, 1, 16, 15),
                Block.box(15, 12, 1, 16, 16, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        private VoxelShape SHAPE_D = Stream.of(
                Block.box(1, 1, 1, 15, 16, 15),
                Block.box(0, 0, 0, 16, 4, 1),
                Block.box(0, 0, 15, 16, 4, 16),
                Block.box(15, 0, 1, 16, 4, 15),
                Block.box(0, 0, 1, 1, 4, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        private VoxelShape SHAPE_N = Stream.of(
                Block.box(15, 0, 0, 16, 16, 4),
                Block.box(0, 0, 0, 1, 16, 4),
                Block.box(1, 15, 0, 15, 16, 4),
                Block.box(1, 0, 0, 15, 1, 4),
                Block.box(1, 1, 4, 15, 1.1, 16),
                Block.box(1, 15, 4, 15, 15.1, 16),
                Block.box(15, 1, 4, 15.1, 15, 16),
                Block.box(1, 1, 4, 1.1, 15, 16),
                Block.box(1, 1, 16, 15, 15, 16.1)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        private VoxelShape SHAPE_E = Stream.of(
                Block.box(12, 0, 0, 16, 16, 1),
                Block.box(12, 15, 1, 16, 16, 15),
                Block.box(12, 0, 15, 16, 16, 16),
                Block.box(12, 0, 1, 16, 1, 15),
                Block.box(0, 1, 1, 12, 1.1, 15),
                Block.box(0, 15, 1, 12, 15.1, 15),
                Block.box(0, 1, 15, 12, 15, 15.1),
                Block.box(0, 1, 1, 12, 15, 1.1),
                Block.box(0, 1, 1, 0.1, 15, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        private VoxelShape SHAPE_S = Stream.of(
                Block.box(0, 0, 12, 1, 16, 16),
                Block.box(15, 0, 12, 16, 16, 16),
                Block.box(1, 15, 12, 15, 16, 16),
                Block.box(1, 0, 12, 15, 1, 16),
                Block.box(1, 1, 0, 15, 1.1, 12),
                Block.box(1, 15, 0, 15, 15.1, 12),
                Block.box(0.8, 1, 0, 1, 15, 12),
                Block.box(14.8, 1, 0, 15, 15, 12),
                Block.box(1, 1, 0, 15, 15, 0.1)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        private VoxelShape SHAPE_W = Stream.of(
                Block.box(0, 0, 0, 4, 16, 1),
                Block.box(0, 0, 15, 4, 16, 16),
                Block.box(0, 15, 1, 4, 16, 15),
                Block.box(0, 0, 1, 4, 1, 15),
                Block.box(4, 1, 1, 16, 1.1, 15),
                Block.box(4, 15, 1, 16, 15.1, 15),
                Block.box(4, 1, 0.9, 16, 15, 1),
                Block.box(4, 1, 14.8, 16, 15, 15),
                Block.box(16, 1, 1, 16.1, 15, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            case UP:
                return SHAPE_U;
            case DOWN:
                return SHAPE_D;
            default:
                return SHAPE_U;
        }
    }

    public static void warp(Entity entity, BlockPos blockPos ,BlockState pState, Level level, WarpPipeBlockEntity warpPipeBlockEntity) {
        level.gameEvent(GameEvent.TELEPORT, blockPos, GameEvent.Context.of(entity));
        level.broadcastEntityEvent(entity, EntityEvent.FIREWORKS_EXPLODE);
        BlockPos destinPos = null;
        destinPos = warpPipeBlockEntity.destination;
                if(!(destinPos==null)) {
                    if (level.getBlockState(destinPos).is(ModTags.Blocks.WARP_PIPES)) {
                        if (level.getBlockState(destinPos).getValue(FACING) == Direction.UP) {
                            entity.teleportTo(destinPos.getX() + .5, destinPos.getY() + 1, destinPos.getZ() + .5);
                        } else {
                            entity.teleportTo(destinPos.getX() + .5, destinPos.getY() + 10, destinPos.getZ() + .5);
                        }
                    } else {
                        level.setBlockAndUpdate(blockPos, pState.setValue(LINKED, false));
                    }
                }
    }
    public void tick(BlockState blockState, ServerLevel p_222544_, BlockPos p_222545_, RandomSource p_222546_) {
        if (!blockState.canSurvive(p_222544_, p_222545_)) {
            p_222544_.destroyBlock(p_222545_, true);
        }

    }


    public void entityInside(BlockState blockState, Level pLevel, BlockPos blockPos, Entity entity) {
        BlockEntity blockentity = pLevel.getBlockEntity(blockPos);
        WarpPipeBlockEntity warpPipeBlockEntity = (WarpPipeBlockEntity) blockentity;
        if (pLevel.getExistingBlockEntity(blockPos) instanceof WarpPipeBlockEntity) {
          if (WarpPipeBlockEntity.entityTeleport(pLevel, blockPos, blockState, entity, (WarpPipeBlockEntity)blockentity)) {
              this.warp(entity, blockPos, blockState, pLevel, warpPipeBlockEntity);
          };
        }
    }


    public static boolean canEnter(Level pLevel, BlockPos blockPos) {
        if (!pLevel.getBlockState(blockPos).is(ModTags.Blocks.WARP_PIPES)) {
            if(!pLevel.getBlockState(blockPos).getValue(LINKED)) {
                return false;
            }
        }
        return pLevel.getBlockState(blockPos).getValue(LINKED);
    }

    public static boolean isLinkable(Level pLevel, BlockPos blockPos) {
        if (!pLevel.getBlockState(blockPos).is(ModTags.Blocks.WARP_PIPES))
            return false;
        return !pLevel.getBlockState(blockPos).getValue(LINKED);
    }

    public void checkConnection(Level level, BlockPos pPos, BlockState pState) {
        if (level.getBlockEntity(pPos) instanceof WarpPipeBlockEntity pipeBlockEntity) {
            if (pipeBlockEntity.destination == null || !level.getBlockState(pipeBlockEntity.destination).is(ModTags.Blocks.WARP_PIPES)) {
                level.setBlockAndUpdate(pPos, pState.setValue(LINKED, false));
                pipeBlockEntity.destination = null;
            }
        }
    }
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos blockPos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (state.getValue(LINKED)) {
            this.checkConnection(level, blockPos, state);
        }
        super.neighborChanged(state, level, blockPos, sourceBlock, sourcePos, notify);
    }


    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public void onPlace(BlockState state, Level level, BlockPos blockPos, BlockState oldState ,boolean remind) {
        super.onPlace(state, level, blockPos, oldState ,remind);
            if (state.getValue(LINKED)) {
                this.checkConnection(level, blockPos, state);
            }
        }

    public boolean triggerEvent(BlockState state, Level level, BlockPos blockPos, int type, int data) {
        super.triggerEvent(state, level, blockPos, type, data);
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity == null) {
            return false;
        }
        return blockEntity.triggerEvent(type, data);
    }

    public WarpPipeBlock(Properties pipe) {
        super(pipe);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP).setValue(LINKED, false));
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING,blockPlaceContext.getNearestLookingDirection().getOpposite()).setValue(LINKED, false);
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState rotate, Mirror mirror) {
        return rotate.rotate(mirror.getRotation(rotate.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(LINKED);
    }
}