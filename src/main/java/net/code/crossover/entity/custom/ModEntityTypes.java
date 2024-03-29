package net.code.crossover.entity.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.code.crossover.Crossovermod;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Crossovermod.MOD_ID);

    public static final RegistryObject<EntityType<GoombaEntity>> GOOMBA = ENTITY_TYPES.register("goomba",
            () -> EntityType.Builder.of(GoombaEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 0.5f)
                    .build(new ResourceLocation(Crossovermod.MOD_ID, "goomba").toString()));

   public static final RegistryObject<EntityType<FireBallEntity>> FIREBALL = ENTITY_TYPES.register("fireball",
           () -> EntityType.Builder.<FireBallEntity>of(FireBallEntity::new, MobCategory.MISC)
                   .sized(0.5f, 0.5f)
                   .build(new ResourceLocation(Crossovermod.MOD_ID, "fireball").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}