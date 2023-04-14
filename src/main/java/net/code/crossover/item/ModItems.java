package net.code.crossover.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.code.crossover.Crossovermod;
import net.code.crossover.entity.custom.ModEntityTypes;
import net.code.crossover.item.custom.mario.FireFlowerItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Crossovermod.MOD_ID);

  public static final RegistryObject<Item> FIRE_FLOWER = ITEMS.register("fire_flower",
          () -> new FireFlowerItem(new Item.Properties().stacksTo(1).durability(128)));

    public static final RegistryObject<ForgeSpawnEggItem> GOOMBA_SPAWN_EGG = ITEMS.register("goomba_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.GOOMBA, 0x948e8d, 0x3b3635,
                    new Item.Properties()));
    public static final RegistryObject<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WARP_CONNECTOR = ITEMS.register("warp_connecter",
            () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}