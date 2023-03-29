//package net.code.crossover.item.custom.mario;
//
//import net.minecraft.world.item.Item;
//import software.bernie.geckolib3.core.IAnimatable;
//import software.bernie.geckolib3.core.PlayState;
//import software.bernie.geckolib3.core.builder.AnimationBuilder;
//import software.bernie.geckolib3.core.controller.AnimationController;
//import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
//import software.bernie.geckolib3.core.manager.AnimationData;
//import software.bernie.geckolib3.core.manager.AnimationFactory;
//
//public class CoinItem extends Item implements IAnimatable {
//    public AnimationFactory factory = new AnimationFactory(this);
//
//    public CoinItem(Properties pProperties) {
//        super(pProperties);
//    }
//
//    @Override
//    public void registerControllers(AnimationData data) {
//        data.addAnimationController(new AnimationController(this, "controller",
//                0, this::predicate));
//    }
//
//    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
//        event.getController().setAnimation(new AnimationBuilder().addAnimation("flip", true));
//
//        return PlayState.CONTINUE;
//    }
//
//    @Override
//    public AnimationFactory getFactory() {
//        return this.factory;
//    }
//}