package net.code.crossover.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
@Mixin(Entity.class)
public abstract class EntityMixin {
    public abstract double getPosX();
    public abstract double getPosY();
    public abstract double getPosZ();
}
