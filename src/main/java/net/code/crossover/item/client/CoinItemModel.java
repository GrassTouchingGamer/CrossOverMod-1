package net.code.crossover.item.client;

import net.minecraft.resources.ResourceLocation;
import net.code.crossover.Crossovermod;
import net.code.crossover.item.custom.mario.CoinItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CoinItemModel extends AnimatedGeoModel<CoinItem> {
    @Override
    public ResourceLocation getModelResource(CoinItem object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "geo/coin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CoinItem object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "textures/item/coin.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CoinItem animatable) {
        return new ResourceLocation(Crossovermod.MOD_ID, "animations/coin.animation.json");
    }
}
