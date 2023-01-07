package net.yebbow.crossover.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.yebbow.sound.ModSounds;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FireBallEntity extends ThrowableProjectile implements IAnimatable {


   private AnimationFactory factory = new AnimationFactory(this);
   private AnimationData data;
    private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
    private SoundEvent soundEvent2 = this.getEntityHitSound();
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return ModSounds.FIREBALL_BOUNCE.get();
    }
    protected SoundEvent getEntityHitSound() {return ModSounds.FIREBALL_HIT.get();}



    public FireBallEntity(EntityType<FireBallEntity> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    public FireBallEntity(Level level, LivingEntity livingEntity) {
        super(ModEntityTypes.FIREBALL.get(), livingEntity, level);
    }



    int bounces = 10;


    @Override
    protected void defineSynchedData() {

    }
    public void tick() {
        super.tick();

        Vec3 vec3;
        vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;
        for (int i = 0; i < 4; ++i) {
            this.level.addParticle(ParticleTypes.CRIT, this.getX() + d5 * (double)i / 4.0D, this.getY() + d6 * (double)i / 4.0D, this.getZ() + d1 * (double)i / 4.0D, -d5, -d6 + 0.2D, -d1);
            }
        }


    protected void onHitEntity(EntityHitResult damage) {
        super.onHitEntity(damage);
        Entity entity = damage.getEntity();
        damage.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 5.0F);
        entity.setSecondsOnFire(5);
        this.discard();
        this.playSound(this.soundEvent2, 1.0F, 1.2F);
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




    /* ANIMATIONS */

   private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

       event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ball.flip", true));
       return PlayState.CONTINUE;
   }

   @Override
   public void registerControllers(AnimationData data) {
       data.addAnimationController(new AnimationController(this, "controller",
               0, this::predicate));
   }

   @Override
   public AnimationFactory getFactory() {
        return factory;
    }
}