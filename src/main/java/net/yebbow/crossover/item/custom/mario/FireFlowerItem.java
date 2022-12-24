package net.yebbow.crossover.item.custom.mario;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.yebbow.crossover.entity.custom.FireBallEntity;

public class FireFlowerItem extends Item {
    public FireFlowerItem(Properties properties) {
        super(properties);
    }
    public FireBallEntity createArrow(Level worldIn, LivingEntity shooter) {
        FireBallEntity fireBallEntity = new FireBallEntity(worldIn, shooter);
        return fireBallEntity;
    }

    public static float getFireBallVelocity(int charge) {
        float f = (float) charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

}
