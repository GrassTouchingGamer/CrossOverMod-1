package net.code.crossover.item;

import net.code.crossover.Crossovermod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab MARIO_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        MARIO_TAB = event.registerCreativeModeTab(new ResourceLocation(Crossovermod.MOD_ID, "mario_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.FIRE_FLOWER.get()))
                        .title(Component.translatable("creativemodetab.mario_tab")));
    }
}