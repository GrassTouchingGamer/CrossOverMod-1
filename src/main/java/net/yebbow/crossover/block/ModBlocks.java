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
import net.yebbow.crossover.block.mario.EmptyBlock;
import net.yebbow.crossover.block.mario.QuestionBlock;
import net.yebbow.crossover.block.mario.QuestionMultiHitBlock;
import net.yebbow.crossover.block.mario.questionblock.UnderGroundBlock;
import net.yebbow.crossover.item.ModItems;
import net.yebbow.sound.ModSounds;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Crossovermod.MOD_ID);

    public static final RegistryObject<Block> MARIO_GRASS_BLOCK = registerBlock("mario_grass_block",
            () -> new GrassBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(3f).destroyTime(1f)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> MARIO_DIRT_BLOCK = registerBlock("mario_dirt_block",
            () -> new DirtPathBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(3f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> QUESTION_BLOCK = registerBlock("question_block",
            () -> new QuestionBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion().sound(ModSounds.QUESTIONABLE_SOUND)),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> UNDERGROUND = registerBlock("under_ground_question_block",
            () -> new UnderGroundBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion()), CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> EMPTY_BLOCK = registerBlock("empty_block",
            () -> new EmptyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.1f, 5f).requiresCorrectToolForDrops().noOcclusion()),CreativeModeTab.TAB_MISC);


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
