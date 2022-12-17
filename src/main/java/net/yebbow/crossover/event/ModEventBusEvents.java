package net.yebbow.crossover.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yebbow.crossover.Crossovermod;
import net.yebbow.crossover.entity.ModEntityTypes;
import net.yebbow.crossover.entity.custom.GoombaEntity;

@Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.GOOMBA.get(), GoombaEntity.setAttributes());
    }
}

