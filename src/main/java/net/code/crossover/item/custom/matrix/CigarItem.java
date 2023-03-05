package net.code.crossover.item.custom.matrix;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.code.crossover.item.ModItems;
import net.code.sound.ModSounds;

public class CigarItem extends Item {
    public CigarItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pUsedHand) {
        if (!level.isClientSide) {
            if (player.getItemInHand(pUsedHand).is(ModItems.CIGAR.get())) {
                level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), ModSounds.CIGARS.get(), SoundSource.NEUTRAL, 10F, 1F);
                player.getCooldowns().addCooldown(this, 100);
            }
        }
        return super.use(level, player, pUsedHand);
    }

}