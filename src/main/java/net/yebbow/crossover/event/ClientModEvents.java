package net.yebbow.crossover.event;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.yebbow.crossover.Crossovermod;
import net.yebbow.crossover.block.ModBlockEntities;
import net.yebbow.crossover.entity.client.block.question.QuestionBlockRenderer;
import net.yebbow.crossover.entity.client.fireball.FireBallRenderer;
import net.yebbow.crossover.entity.client.goomba.GoombaRenderer;
import net.yebbow.crossover.entity.custom.ModEntityTypes;
@Mod.EventBusSubscriber(modid = Crossovermod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.QUESTION_BLOCK.get(), QuestionBlockRenderer::new);
    }
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityTypes.GOOMBA.get(), GoombaRenderer::new);
        EntityRenderers.register(ModEntityTypes.FIREBALL.get(), FireBallRenderer::new);

    }

}