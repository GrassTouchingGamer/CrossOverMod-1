package net.code.crossover.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.code.crossover.Crossovermod;
import net.code.crossover.entity.custom.GoombaEntity;
import net.code.crossover.entity.custom.ModEntityTypes;

@Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.GOOMBA.get(), GoombaEntity.setAttributes());
    }
}

