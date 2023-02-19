package net.yebbow.crossover.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab MARIO = new CreativeModeTab("super_mario") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.COIN.get());
        }
    };

}