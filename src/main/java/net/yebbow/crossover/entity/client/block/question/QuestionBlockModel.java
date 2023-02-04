package net.yebbow.crossover.entity.client.block.question;

import net.minecraft.resources.ResourceLocation;
import net.yebbow.crossover.Crossovermod;
import net.yebbow.crossover.block.mario.entity.QuestionBlockEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class QuestionBlockModel extends AnimatedGeoModel<QuestionBlockEntity> {

    @Override
    public ResourceLocation getModelLocation(QuestionBlockEntity object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "geo/question.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(QuestionBlockEntity object) {
        return new ResourceLocation(Crossovermod.MOD_ID, "textures/block/question_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(QuestionBlockEntity animatable) {
        return new ResourceLocation(Crossovermod.MOD_ID, "animations/question.animation.json");
    }
}
