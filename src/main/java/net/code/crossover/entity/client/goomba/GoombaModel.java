package net.code.crossover.entity.client.goomba;

import net.minecraft.resources.ResourceLocation;
import net.code.crossover.Crossovermod;
import net.code.crossover.entity.custom.GoombaEntity;
import software.bernie.geckolib.model.GeoModel;

public class GoombaModel extends GeoModel<GoombaEntity> {

    @Override
    public ResourceLocation getModelResource(GoombaEntity object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "geo/goomba.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GoombaEntity object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "textures/entity/goomba/goomba.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GoombaEntity animatable) {
        return new ResourceLocation(Crossovermod.MOD_ID, "animations/goomba.animation.json");
    }
}
