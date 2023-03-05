package net.code.crossover.item;

import net.code.crossover.item.client.CoinItemModel;
import net.code.crossover.item.custom.mario.CoinItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CoinItemRenderer extends GeoItemRenderer<CoinItem> {

    public CoinItemRenderer() {
        super(new CoinItemModel());
    }}
