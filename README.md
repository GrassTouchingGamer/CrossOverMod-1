The Problem seems to be in the GoombaEntity Class.
Error: Caused by: java.lang.NullPointerException: Registry Object not present: crossover:entity.goomba.ambient
It seems to be coming from these methods:

    protected SoundEvent getStepSound() {
        return ModSounds.GOOMBA_STEP.get();
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }


    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.GOOMBA_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.GOOMBA_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.GOOMBA_DEATH.get();
    }