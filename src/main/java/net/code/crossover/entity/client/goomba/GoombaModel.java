package net.code.crossover.entity.client.goomba;

import net.minecraft.resources.ResourceLocation;
import net.code.crossover.Crossovermod;
import net.code.crossover.entity.custom.GoombaEntity;
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