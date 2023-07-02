package net.code.crossover.block.mario;

import net.code.crossover.block.ModBlockEntities;
import net.code.crossover.block.mario.entity.WarpPipeBlockEntity;
import net.code.crossover.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
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

import static software.bernie.example.block.GeckoHabitatBlock.FACING;

public class WarpPipeBlock extends BaseEntityBlock {

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.WARP_PIPE_BLOCK_ENTITY.get().create(pPos, pState);
    }

    public static final BooleanProperty LINKED = BooleanProperty.create("linked");

    public VoxelShape SHAPE_U = Stream.of(
            Block.box(0, 12, 0, 16, 16, 1),
            Block.box(0, 12, 1, 1, 16, 15),
            Block.box(0, 12, 15, 16, 16, 16),
            Block.box(15, 12, 1, 16, 16, 15),
            Block.box(1, 0, 1, 15, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public VoxelShape SHAPE_D = Stream.of(
                Block.box(1, 1, 1, 15, 16, 15),
                Block.box(0, 0, 0, 16, 4, 1),
                Block.box(0, 0, 15, 16, 4, 16),
                Block.box(15, 0, 1, 16, 4, 15),
                Block.box(0, 0, 1, 1, 4, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public VoxelShape SHAPE_N = Stream.of(
                Block.box(0, 0, 0, 16, 1, 4),
                Block.box(0, 1, 0, 1, 15, 4),
                Block.box(0, 15, 0, 16, 16, 4),
                Block.box(15, 1, 0, 16, 15, 4),
                Block.box(1, 1, 1, 15, 15, 16)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public VoxelShape SHAPE_E = Stream.of(
                Block.box(12, 0, 0, 16, 1, 16),
                Block.box(12, 1, 0, 16, 15, 1),
                Block.box(12, 15, 0, 16, 16, 16),
                Block.box(12, 1, 15, 16, 15, 16),
                Block.box(0, 1, 1, 15, 15, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public VoxelShape SHAPE_S = Stream.of(
                Block.box(0, 0, 12, 16, 1, 16),
                Block.box(15, 1, 12, 16, 15, 16),
                Block.box(0, 15, 12, 16, 16, 16),
                Block.box(0, 1, 12, 1, 15, 16),
                Block.box(1, 1, 0, 15, 15, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public VoxelShape SHAPE_W = Stream.of(
                Block.box(0, 0, 0, 4, 1, 16),
                Block.box(0, 1, 15, 4, 15, 16),
                Block.box(0, 15, 0, 4, 16, 16),
                Block.box(0, 1, 0, 4, 15, 1),
                Block.box(1, 1, 1, 16, 15, 15)
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

    public static void warp(Entity entity, BlockPos blockPos ,BlockState pState, Level pLevel, WarpPipeBlockEntity warpPipeBlockEntity) {
        pLevel.gameEvent(GameEvent.TELEPORT, blockPos, GameEvent.Context.of(entity));
        pLevel.broadcastEntityEvent(entity, EntityEvent.FIREWORKS_EXPLODE);
       BlockPos destinPos = warpPipeBlockEntity.destination;
        boolean stop = false;
        if (!(destinPos == null)) {
            if (pLevel.getBlockState(destinPos).is(ModTags.Blocks.WARP_PIPES)) {
                if (pLevel.getBlockState(destinPos).getValue(LINKED)) {
                    entity.setShiftKeyDown(stop);
                    if (pLevel.getBlockState(destinPos).getValue(FACING) == Direction.UP) {
                        BlockPos AbovePos = destinPos.above();
                        if (pLevel.getBlockState(AbovePos).is(ModTags.Blocks.WARP_PASSES))
                            entity.teleportTo(destinPos.getX() + .5, destinPos.getY() + 1, destinPos.getZ() + .5);
                    }
                    if(pLevel.getBlockState(destinPos).getValue(FACING) == Direction.DOWN){
                        BlockPos BelowPos = destinPos.below();
                        if (pLevel.getBlockState(BelowPos).is(ModTags.Blocks.WARP_PASSES)) {
                            entity.teleportTo(destinPos.getX() + .5, destinPos.getY() - 1.5, destinPos.getZ() + .5);}}
                    if(pLevel.getBlockState(destinPos).getValue(FACING) == Direction.NORTH){
                        if(pLevel.getBlockState(destinPos.north()).is(ModTags.Blocks.WARP_PASSES))
                        entity.teleportTo(destinPos.getX() + .5, destinPos.getY(), destinPos.getZ() - .3);
                        entity.setYRot(180);
                    }
                    if(pLevel.getBlockState(destinPos).getValue(FACING) == Direction.SOUTH){
                        if(pLevel.getBlockState(destinPos.south()).is(ModTags.Blocks.WARP_PASSES))
                        entity.teleportTo(destinPos.getX() + .5, destinPos.getY(), destinPos.getZ() + 1.3);
                        entity.setYRot(0);
                    }
                    if(pLevel.getBlockState(destinPos).getValue(FACING) == Direction.EAST){
                        if(pLevel.getBlockState(destinPos.east()).is(ModTags.Blocks.WARP_PASSES))
                        entity.teleportTo(destinPos.getX() + 1.3, destinPos.getY(), destinPos.getZ() + .5);
                        entity.setYRot(-90);
                    }
                    if(pLevel.getBlockState(destinPos).getValue(FACING) == Direction.WEST){
                        if(pLevel.getBlockState(destinPos.west()).is(ModTags.Blocks.WARP_PASSES))
                        entity.teleportTo(destinPos.getX() - .3, destinPos.getY(), destinPos.getZ() + .5);
                        entity.setYRot(90);
                    }
                } else {pLevel.setBlockAndUpdate(blockPos, pState.setValue(LINKED, false));}
            } else {pLevel.setBlockAndUpdate(blockPos, pState.setValue(LINKED, false));}
        } else {pLevel.setBlockAndUpdate(blockPos, pState.setValue(LINKED, false));}
    }

public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
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

    boolean swim = true;
    public void entityInside(BlockState blockState, Level pLevel, BlockPos blockPos, Entity entity) {
        BlockEntity blockentity = pLevel.getBlockEntity(blockPos);
        WarpPipeBlockEntity warpPipeBlockEntity = (WarpPipeBlockEntity) blockentity;
            if (entity.isColliding(blockPos.above(), blockState) && entity.isCrouching() && pLevel.getBlockState(blockPos).getValue(FACING) == Direction.UP ||
                    entity.isColliding(blockPos.north(), blockState) && pLevel.getBlockState(blockPos).getValue(FACING) == Direction.NORTH ||
                    entity.isColliding(blockPos.east(), blockState) && pLevel.getBlockState(blockPos).getValue(FACING) == Direction.EAST ||
                    entity.isColliding(blockPos.south(), blockState) && pLevel.getBlockState(blockPos).getValue(FACING) == Direction.SOUTH ||
                    entity.isColliding(blockPos.west(), blockState) && pLevel.getBlockState(blockPos).getValue(FACING) == Direction.WEST ||
                    entity.isColliding(blockPos.below(), blockState) && pLevel.getBlockState(blockPos).getValue(FACING)== Direction.DOWN) {
                this.warp(entity, blockPos, blockState, pLevel, warpPipeBlockEntity);
            }
        if(pLevel.getBlockState(blockPos).getValue(FACING) == Direction.NORTH){
            if(pLevel.getBlockState(blockPos.north()).isAir() && entity.isCrouching() && entity.isColliding(blockPos.of((long) (blockPos.getZ() + 1)), blockState)) {
                entity.setSwimming(swim);
            }
        }
        if(pLevel.getBlockState(blockPos).getValue(FACING) == Direction.SOUTH){
            if(pLevel.getBlockState(blockPos.south()).isAir() && entity.isColliding(blockPos.south(), blockState) && entity.isCrouching())
            entity.setSwimming(swim);}
        if(pLevel.getBlockState(blockPos).getValue(FACING) == Direction.EAST){
            if(pLevel.getBlockState(blockPos.east()).isAir() && entity.isColliding(blockPos.east(), blockState) && entity.isCrouching())
                entity.setSwimming(swim);}
        if(pLevel.getBlockState(blockPos).getValue(FACING) == Direction.WEST){
            if(pLevel.getBlockState(blockPos.west()).isAir() && entity.isColliding(blockPos.west(), blockState) && entity. isCrouching())
            entity.setSwimming(swim);}
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