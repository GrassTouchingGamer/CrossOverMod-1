package net.code.crossover.world.dimension;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.code.crossover.Crossovermod;

public class ModDimensions {
    public static final ResourceKey<Level> MARIO_WORLD_KEY = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("mario_world"));
    public static final ResourceKey<Level> MARIO_WORLD = ResourceKey.create(Registries.DIMENSION, new  ResourceLocation(Crossovermod.MOD_ID, "mario_world"));

    public static void register() {
        System.out.println("Registering ModDimensions for " + Crossovermod.MOD_ID);
    }
}