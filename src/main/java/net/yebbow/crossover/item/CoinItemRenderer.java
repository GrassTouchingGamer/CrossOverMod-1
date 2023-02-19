package net.yebbow.crossover.item;

import net.yebbow.crossover.item.client.CoinItemModel;
import net.yebbow.crossover.item.custom.mario.CoinItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CoinItemRenderer extends GeoItemRenderer<CoinItem> {

    public CoinItemRenderer() {
        super(new CoinItemModel());
    }}
