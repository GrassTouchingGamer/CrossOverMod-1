package net.code.crossover.mixin;

import net.code.crossover.block.mario.WarpPipeBlock;
import net.code.crossover.block.mario.entity.WarpPipeBlockEntity;
import net.code.crossover.block.mario.entity.WarpShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow
    @Final
    private Abilities abilities;

    private int WarpCooldown;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    private void PipeTeleportation(WarpShape warpShape, Direction direction) {
        if (this.getWarpCooldown() != 0) return;
        BlockPos originPos = null;
        if (WarpPipeBlock.canEnter(level, blockPosition())) originPos = blockPosition();
        if (originPos != null) {
            this.warpToPipe(originPos, warpShape, direction);
        }
    }

    private void warpToPipe(BlockPos originPos, WarpShape warpShape, Direction direction) {
        BlockPos destinPos = null;
        BlockEntity blockEntity = level.getBlockEntity(originPos);
        if (blockEntity instanceof WarpPipeBlockEntity warpPipeBE) {
            destinPos = warpPipeBE.destination;
        }
    }

    public int getWarpCooldown() {
        return WarpCooldown;
    }

    public void setWarpCooldown(int cooldown) {
        this.WarpCooldown = cooldown;
    }
}
