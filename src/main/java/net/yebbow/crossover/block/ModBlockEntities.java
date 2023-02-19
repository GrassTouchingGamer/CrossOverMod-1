package net.yebbow.crossover.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yebbow.crossover.Crossovermod;
import net.yebbow.crossover.block.mario.entity.QuestionBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Crossovermod.MOD_ID);

    public static final RegistryObject<BlockEntityType<QuestionBlockEntity>> QUESTION_BLOCK =
            BLOCK_ENTITIES.register("question_block", () -> BlockEntityType.Builder.of(QuestionBlockEntity::new, ModBlocks.QUESTION_BLOCK.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}