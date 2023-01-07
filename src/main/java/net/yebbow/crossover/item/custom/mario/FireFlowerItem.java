package net.yebbow.crossover.item.custom.mario;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yebbow.crossover.entity.custom.FireBallEntity;

public class FireFlowerItem extends Item {
    public FireFlowerItem(Item.Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            FireBallEntity fire = new FireBallEntity(level, player);
            fire.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(fire);
        }


        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());

    }
}
