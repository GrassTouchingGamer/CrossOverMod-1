package net.code.crossover.event;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.code.crossover.Crossovermod;
import net.code.crossover.entity.client.fireball.FireBallRenderer;
import net.code.crossover.entity.client.goomba.GoombaRenderer;
import net.code.crossover.entity.custom.ModEntityTypes;
@Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityTypes.GOOMBA.get(), GoombaRenderer::new);
        EntityRenderers.register(ModEntityTypes.FIREBALL.get(), FireBallRenderer::new);

    }

}