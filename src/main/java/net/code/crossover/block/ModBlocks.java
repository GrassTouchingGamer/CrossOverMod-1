package net.code.crossover.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.code.crossover.Crossovermod;
import net.code.crossover.block.mario.EmptyBlock;
import net.code.crossover.block.mario.QuestionBlock;
import net.code.crossover.block.mario.portalwarppipe;
import net.code.crossover.item.ModCreativeModeTab;
import net.code.crossover.item.ModItems;
import net.code.sound.ModSounds;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Crossovermod.MOD_ID);

    public static final RegistryObject<Block> MARIO_GRASS_BLOCK = registerBlock("mario_grass_block",
            () -> new GrassBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(3f).destroyTime(1f)), ModCreativeModeTab.MARIO);

    public static final RegistryObject<Block> MARIO_DIRT_BLOCK = registerBlock("mario_dirt_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MARIO);

    public static final RegistryObject<Block> MARIO_STONE_BLOCK = registerBlock("mario_stone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MARIO);

    public static final RegistryObject<Block> MARIO_SAND_BLOCK = registerBlock("mario_sand",
            () -> new Block(BlockBehaviour.Properties.of(Material.SAND)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MARIO);

    public static final RegistryObject<Block> SMOOTH_TOAD_BRICK = registerBlock("smooth_toad_brick",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MARIO);

    public static final RegistryObject<Block> QUESTION_BLOCK = registerBlock("question_block",
            () -> new QuestionBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion().sound(ModSounds.QUESTIONABLE_SOUND)),
            ModCreativeModeTab.MARIO);

    public static final RegistryObject<Block> EMPTY_BLOCK = registerBlock("empty_block",
            () -> new EmptyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.1f, 5f).requiresCorrectToolForDrops().noOcclusion()),ModCreativeModeTab.MARIO);


    public static final RegistryObject<Block> WARPPIPE = registerBlock("warp_pipe",
            () -> new portalwarppipe(BlockBehaviour.Properties.of(Material.METAL).noOcclusion()),
            ModCreativeModeTab.MARIO);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
