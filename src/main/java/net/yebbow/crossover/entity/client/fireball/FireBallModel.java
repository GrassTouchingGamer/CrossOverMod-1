package net.yebbow.crossover.entity.client.fireball;

import net.minecraft.resources.ResourceLocation;
import net.yebbow.crossover.Crossovermod;
import net.yebbow.crossover.entity.custom.FireBallEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FireBallModel extends AnimatedGeoModel<FireBallEntity> {
    @Override
    public ResourceLocation getModelLocation(FireBallEntity object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "geo/ball.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FireBallEntity object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "textures/entity/ball/fire.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FireBallEntity animatable) {
        return new ResourceLocation(Crossovermod.MOD_ID, "animations/ball.animation.json");
    }
}