package net.code.crossover.entity.custom;

import net.code.crossover.item.ModItems;
import net.code.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FireBallEntity extends AbstractArrow {
    private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
    private SoundEvent soundEvent2 = this.getEntityHitSound();
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return ModSounds.FIREBALL_BOUNCE.get();
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    protected SoundEvent getEntityHitSound() {return ModSounds.FIREBALL_HIT.get();}

    public FireBallEntity(EntityType<FireBallEntity> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    public FireBallEntity(Level level, LivingEntity livingEntity) {
        super(ModEntityTypes.FIREBALL.get(), livingEntity, level);
    }
    int bounces = 5;

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    protected boolean tryPickup(Player player) {
        return false;
    }
    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            if (this.random.nextFloat() > 0.1F) {
                level.addParticle(ParticleTypes.FLAME, this.getX() + (random.nextFloat() * 0.2), this.getY() + (random.nextFloat() * 0.2), this.getZ() + (random.nextFloat() * 0.2), 0.0D, 0.0D, 0.0D);
            }
        } else if (this.inGround && random.nextInt(100) == 0) {
            this.discard();
         }
    }
    protected void onHitEntity(EntityHitResult damage) {
        super.onHitEntity(damage);
        Entity entity = damage.getEntity();
        if (!entity.fireImmune()) {
            damage.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 5.0F);
        entity.setSecondsOnFire(5); }
        this.discard();
        this.playSound(this.soundEvent2, 1.0F, 1F);
    }
   protected void onHitBlock(BlockHitResult result) {
       if (result.getDirection()== Direction.UP && bounces>=0) {
           this.setDeltaMovement(this.getDeltaMovement().add(.0, .3, 0));
           this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
           bounces--;
       } else if (result.getDirection() == Direction.DOWN && bounces>=0) {
           this.setDeltaMovement(this.getDeltaMovement().subtract(0, .3 ,0));
           bounces--;
       } else {this.discard(); this.playSound(this.soundEvent2, 1.0F, 1.2F);
           BlockPos blockpos = result.getBlockPos().relative(result.getDirection());
           if (this.level.isEmptyBlock(blockpos)) {
               this.level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level, blockpos));
           }
       }
   }
}