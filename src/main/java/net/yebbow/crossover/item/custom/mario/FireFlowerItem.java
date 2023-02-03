package net.yebbow.crossover.item.custom.mario;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yebbow.crossover.entity.custom.FireBallEntity;
import net.yebbow.sound.ModSounds;

public class FireFlowerItem extends Item {
    public FireFlowerItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder use( Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            FireBallEntity fire = new FireBallEntity(level, player);
            level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), ModSounds.FIREFLOWER_USE.get(), SoundSource.NEUTRAL, 0.5F, 1F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            fire.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(fire);
            player.getCooldowns().addCooldown(this, 5);
            itemstack.hurtAndBreak(1, player, (p_41288_) -> {
                p_41288_.broadcastBreakEvent(hand);
            });

        }



        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());

    }




}