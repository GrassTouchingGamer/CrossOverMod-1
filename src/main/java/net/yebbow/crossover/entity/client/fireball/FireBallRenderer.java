package net.yebbow.crossover.entity.client.fireball;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.yebbow.crossover.entity.custom.FireBallEntity;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class FireBallRenderer extends GeoProjectilesRenderer<FireBallEntity> {


    public FireBallRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FireBallModel());
        this.shadowRadius = 0.3f;
    }


    @Override
    public RenderType getRenderType(FireBallEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(0.8F, 0.8F, 0.8F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
