package net.yebbow.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yebbow.crossover.Crossovermod;

import java.util.function.Supplier;

public class ModSounds{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Crossovermod.MOD_ID);

    public static RegistryObject<SoundEvent> GOOMBA_STEP = registerSoundEvent("goomba.step");
    public static RegistryObject<SoundEvent> GOOMBA_AMBIENT = registerSoundEvent("goomba.ambient");
    public static RegistryObject<SoundEvent> GOOMBA_HURT = registerSoundEvent("goomba.hurt");
    public static RegistryObject<SoundEvent> GOOMBA_DEATH = registerSoundEvent("goomba.death");
    public static RegistryObject<SoundEvent> FIREBALL_BOUNCE = registerSoundEvent("fireball.bounce");
    public static RegistryObject<SoundEvent> FIREBALL_HIT = registerSoundEvent("fireball.hit");
    public static RegistryObject<SoundEvent> FIREFLOWER_USE = registerSoundEvent("fireflower.use");
    public static RegistryObject<SoundEvent> TOP_G = registerSoundEvent("top.g");
    public static RegistryObject<SoundEvent> COIN_COLLECT = registerSoundEvent("coin.collect");
    public static RegistryObject<SoundEvent> QUESTION_ACTIVATE = registerSoundEvent("question.activate");
    public static RegistryObject<SoundEvent> QUESTION_BUMP = registerSoundEvent("question.bump");
    public static RegistryObject<SoundEvent> QUESTION_STEP = registerSoundEvent("question.step");
    public static RegistryObject<SoundEvent> NULL = registerSoundEvent("");

    public static final ForgeSoundType QUESTIONABLE_SOUND = new ForgeSoundType(1.0F, 1.0F,
            ModSounds.QUESTION_BUMP, ModSounds.QUESTION_STEP, ModSounds.NULL, ModSounds.NULL, ModSounds.QUESTION_BUMP);

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(Crossovermod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> new SoundEvent(id));
    }
        public static void register (IEventBus eventBus) {
            SOUND_EVENTS.register(eventBus);
        }

}
