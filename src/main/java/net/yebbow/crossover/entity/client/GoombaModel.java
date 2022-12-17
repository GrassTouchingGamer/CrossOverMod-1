package net.yebbow.crossover.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.yebbow.crossover.Crossovermod;
import net.yebbow.crossover.entity.custom.GoombaEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoombaModel extends AnimatedGeoModel<GoombaEntity> {
    @Override
    public ResourceLocation getModelLocation(GoombaEntity object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "geo/goomba.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GoombaEntity object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "textures/entity/goomba/goomba.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GoombaEntity animatable) {
        return new ResourceLocation(Crossovermod.MOD_ID, "animations/goomba.animation.json");
    }
}
