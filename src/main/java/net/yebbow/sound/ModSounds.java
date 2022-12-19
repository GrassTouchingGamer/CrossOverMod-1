package net.yebbow.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yebbow.crossover.Crossovermod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Crossovermod.MOD_ID);

    public static final RegistryObject<SoundEvent> GOOMBA_STEP = registerSoundEvent("entity.goomba.step");
    public static RegistryObject<SoundEvent> GOOMBA_AMBIENT = registerSoundEvent("entity.goomba.ambient");
    public static RegistryObject<SoundEvent> GOOMBA_HURT = registerSoundEvent("entity.goomba.hurt");
    public static RegistryObject<SoundEvent> GOOMBA_DEATH = registerSoundEvent("entity.goomba.death");



    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(Crossovermod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> new SoundEvent(id));
    }
        public static void register (IEventBus eventBus) {
            SOUND_EVENTS.register(eventBus);
        }

}
