package net.yebbow.crossover.block.mario;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.yebbow.crossover.block.ModBlocks;
import net.yebbow.crossover.block.mario.entity.QuestionBlockEntity;

public class EmptyBlock extends Block {
    public EmptyBlock(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        if (!pLevel.isClientSide()) {
            if (!stack.isEmpty()) {
                pLevel.setBlockAndUpdate(pPos, ModBlocks.QUESTION_BLOCK.get().defaultBlockState());
                if (pLevel.getExistingBlockEntity(pPos) instanceof QuestionBlockEntity inventory) {
                    inventory.setStack(stack.copy());
                }
                ((QuestionBlock) ModBlocks.QUESTION_BLOCK.get()).setStack(pLevel, pPos, stack);
                if (!pPlayer.isCreative()) {
                    pPlayer.getItemInHand(pHand).setCount(0);
                }
            }
        }
        return InteractionResult.CONSUME;
    }

}