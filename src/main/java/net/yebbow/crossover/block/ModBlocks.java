package net.yebbow.crossover.block;

import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.yebbow.crossover.Crossovermod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yebbow.crossover.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Crossovermod.MOD_ID);

    public static final RegistryObject<Block> MARIO_GRASS_BLOCK = registerBlock("mario_grass_block",
            () -> new GrassBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(3f).destroyTime(1f)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> MARIO_DIRT_BLOCK = registerBlock("mario_dirt_block",
            () -> new DirtPathBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(3f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
