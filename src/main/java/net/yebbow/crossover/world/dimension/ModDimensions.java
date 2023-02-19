package net.yebbow.crossover.world.dimension;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.yebbow.crossover.Crossovermod;

public class ModDimensions {
    public static final ResourceKey<Level> MARIO_WORLD_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Crossovermod.MOD_ID, "mario_world"));
    public static final ResourceKey<DimensionType> MARIO_WORLD =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, MARIO_WORLD_KEY.getRegistryName());

    public static void register() {
        System.out.println("Registering ModDimensions for " + Crossovermod.MOD_ID);
    }
}