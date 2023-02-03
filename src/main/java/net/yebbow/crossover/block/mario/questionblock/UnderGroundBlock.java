package net.yebbow.crossover.block.mario.questionblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yebbow.crossover.block.ModBlocks;
import net.yebbow.crossover.block.mario.QuestionBlock;

public class UnderGroundBlock extends QuestionBlock {
    public UnderGroundBlock(Properties properties) {
        super(properties);
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
            pLevel.setBlockAndUpdate(pPos, ModBlocks.EMPTY_BLOCK.get().defaultBlockState());
        }
    }

}
