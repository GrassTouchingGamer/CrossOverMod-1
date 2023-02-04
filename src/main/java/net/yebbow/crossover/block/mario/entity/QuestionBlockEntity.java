package net.yebbow.crossover.block.mario.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yebbow.crossover.block.ModBlockEntities;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import static software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes.PLAY_ONCE;

public class QuestionBlockEntity extends BlockEntity implements Clearable, IAnimatable{

    public boolean animated() {
        return this.animated();
    }
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

    AnimationFactory factory = GeckoLibUtil.createFactory(this);


    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if(this.animated()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.question.bump", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<QuestionBlockEntity> controller = new AnimationController<>(this, "controller", 0, this::predicate);
        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}