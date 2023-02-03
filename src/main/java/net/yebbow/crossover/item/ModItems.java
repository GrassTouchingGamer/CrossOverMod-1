package net.yebbow.crossover.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yebbow.crossover.Crossovermod;
import net.yebbow.crossover.entity.custom.ModEntityTypes;
import net.yebbow.crossover.item.custom.mario.FireFlowerItem;
import net.yebbow.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Crossovermod.MOD_ID);

  public static final RegistryObject<Item> FIRE_FLOWER = ITEMS.register("fire_flower",
          () -> new FireFlowerItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).durability(128)));

    public static final RegistryObject<ForgeSpawnEggItem> GOOMBA_SPAWN_EGG = ITEMS.register("goomba_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.GOOMBA, 0x948e8d, 0x3b3635,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> TOPG_DISC = ITEMS.register("top_g_music_disc",
            () -> new RecordItem(4, ModSounds.TOP_G, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}