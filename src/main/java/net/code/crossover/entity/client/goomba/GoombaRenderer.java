package net.code.crossover.entity.client.goomba;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.code.crossover.entity.custom.GoombaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GoombaRenderer extends GeoEntityRenderer<GoombaEntity> {


    public GoombaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GoombaModel());
        this.shadowRadius = 0.3f;
    }


}