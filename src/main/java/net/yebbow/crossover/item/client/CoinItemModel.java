package net.yebbow.crossover.item.client;

import net.minecraft.resources.ResourceLocation;
import net.yebbow.crossover.Crossovermod;
import net.yebbow.crossover.item.custom.mario.CoinItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CoinItemModel extends AnimatedGeoModel<CoinItem> {
    @Override
    public ResourceLocation getModelLocation(CoinItem object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "geo/coin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CoinItem object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "textures/item/coin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CoinItem animatable) {
        return new ResourceLocation(Crossovermod.MOD_ID, "animations/coin.animation.json");
    }
}
