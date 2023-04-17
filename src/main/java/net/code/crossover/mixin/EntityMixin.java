package net.code.crossover.mixin;

import net.code.crossover.block.mario.WarpPipeBlock;
import net.code.crossover.block.mario.entity.WarpPipeBlockEntity;
import net.code.crossover.block.mario.entity.WarpShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
@Mixin(Entity.class)
public abstract class EntityMixin extends LivingEntity {

    private int WarpCooldown;

    protected EntityMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

  //  private void warpToPipe(BlockPos originPos, WarpShape warpShape, Direction direction, WarpPipeBlockEntity warpPipeBlockEntity) {
  //      BlockPos destinPos = null;
  //      BlockEntity blockEntity = level.getBlockEntity(originPos);
  //      if (blockEntity instanceof WarpPipeBlockEntity pipeBlockEntity) {
  //          destinPos = pipeBlockEntity.destination;
  //      }
  //      if (destinPos == null) return;
  //      WarpPipeBlock.warp((Player) (Object) this, destinPos, direction, level, warpShape, warpPipeBlockEntity);
  //      this.setWarpCooldown(20);
  //  }

    public int getWarpCooldown() {
        return WarpCooldown;
    }

    public void setWarpCooldown(int cooldown) {
        this.WarpCooldown = cooldown;
    }
}
