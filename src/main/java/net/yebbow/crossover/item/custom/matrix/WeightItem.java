package net.yebbow.crossover.item.custom.matrix;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yebbow.crossover.item.ModItems;
import net.yebbow.sound.ModSounds;

public class WeightItem extends Item {
    public WeightItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pUsedHand) {
        if (!level.isClientSide) {
            if (player.getItemInHand(pUsedHand).is(ModItems.WEIGHT.get())) {
                level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), ModSounds.PUSHUPS.get(), SoundSource.NEUTRAL, 10F, 1F);
                player.getCooldowns().addCooldown(this, 60);
            }


        }
        return super.use(level, player, pUsedHand);
    }

}
