package net.code.crossover.block.mario;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.code.crossover.block.ModBlockEntities;
import net.code.crossover.block.ModBlocks;
import net.code.crossover.block.mario.entity.QuestionBlockEntity;
import net.code.crossover.item.ModItems;
import net.code.sound.ModSounds;

public class QuestionBlock extends BaseEntityBlock {
    public QuestionBlock(Properties properties) {
        super(properties);
    }
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.QUESTION_BLOCK.get().create(pPos, pState);
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;

    }


    private void dropitem(Level pLevel, BlockPos pPos) {
        if (!pLevel.isClientSide) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof QuestionBlockEntity) {
                QuestionBlockEntity questionBlockEntity = (QuestionBlockEntity) blockentity;
                ItemStack itemstack = questionBlockEntity.getStack();
                if (!itemstack.isEmpty()) {
                    if (!itemstack.is(ModItems.COIN.get())) {
                        this.summonitementity(pLevel, pPos);
                        questionBlockEntity.clearContent();
                        pLevel.setBlockAndUpdate(pPos, ModBlocks.EMPTY_BLOCK.get().defaultBlockState());
                        pLevel.playSound((Player) null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.QUESTION_ACTIVATE.get(), SoundSource.NEUTRAL, 1F, 1F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
                    } else {
                        this.dropitemmultihit(pLevel, pPos);
                    }

                }
            }
        }
    }

    private void dropitemmultihit(Level pLevel, BlockPos pPos) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            QuestionBlockEntity questionBlockEntity = (QuestionBlockEntity) blockentity;
            ItemStack itemstack = questionBlockEntity.getStack();
        if (itemstack.getCount() <= 1) {
                pLevel.setBlockAndUpdate(pPos, ModBlocks.EMPTY_BLOCK.get().defaultBlockState());
            } else {
            itemstack.shrink(1);
            pLevel.setBlockAndUpdate(pPos, ModBlocks.QUESTION_BLOCK.get().defaultBlockState());
                if (pLevel.getExistingBlockEntity(pPos) instanceof QuestionBlockEntity inventory) {
                    inventory.setStack(itemstack.copy());
                }
            }
            this.summonitementity(pLevel, pPos);
        if(itemstack.is(ModItems.COIN.get())) {
            pLevel.playSound((Player) null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.COIN_COLLECT.get(), SoundSource.NEUTRAL, 1F, 1F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        }
    }
    private void summonitementity(Level pLevel, BlockPos pPos) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        QuestionBlockEntity questionBlockEntity = (QuestionBlockEntity) blockentity;
        ItemStack itemstack = questionBlockEntity.getStack();
        pLevel.levelEvent(1010, pPos, 0);
        float f = 0.7F;
        double d0 = (double) (pLevel.random.nextFloat() * 1.7F) + (double) 0.2F;
        double d1 = (double) (pLevel.random.nextFloat() * 1.7F) + (double) 0.060000002F + 0.6D;
        double d2 = (double) (pLevel.random.nextFloat() * 1.7F) + (double) 0.2F;
            ItemStack itemstack1 = itemstack.copy();
            if(itemstack1.is(ModItems.COIN.get())) {
                itemstack1.setCount(1);
            }
            ItemEntity itementity = new ItemEntity(pLevel, (double) pPos.getX() + d0, (double) pPos.getY() + d1, (double) pPos.getZ() + d2, itemstack1);
            itementity.setDefaultPickUpDelay();
            pLevel.addFreshEntity(itementity);
    }



        public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            this.dropitem(pLevel, pPos);
        }
    }

        public void animated(boolean animate) {

    }

    public void setStack(LevelAccessor pLevel, BlockPos pPos, ItemStack pStack) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof QuestionBlockEntity) {
            ((QuestionBlockEntity)blockentity).setStack(pStack.copy());
        }
    }
}