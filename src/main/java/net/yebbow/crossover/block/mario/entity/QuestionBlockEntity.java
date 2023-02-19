package net.yebbow.crossover.block.mario.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yebbow.crossover.block.ModBlockEntities;

public class QuestionBlockEntity extends BlockEntity implements Clearable {


    private ItemStack stack = ItemStack.EMPTY;

    public QuestionBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.QUESTION_BLOCK.get(), pWorldPosition, pBlockState);
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!this.getStack().isEmpty()) {
            pTag.put("Stack", this.getStack().save(new CompoundTag()));
        }
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("Stack", 10)) {
            this.setStack(ItemStack.of(pTag.getCompound("Stack")));
        }
    }


    public ItemStack getStack() {
        return this.stack;
    }

    public void setStack(ItemStack pStack) {
        this.stack = pStack;
        this.setChanged();
    }


    @Override
    public void clearContent() {
        this.setStack(ItemStack.EMPTY);
    }
}
