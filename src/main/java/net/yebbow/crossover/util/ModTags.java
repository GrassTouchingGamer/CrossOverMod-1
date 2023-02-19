package net.yebbow.crossover.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.yebbow.crossover.Crossovermod;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Crossovermod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Crossovermod.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class ConfiguredStructureFeatures {
        public static final TagKey<ConfiguredStructureFeature<?, ?>> CUSTOM_STRUCTURE_TAG = tag("custom_structure_tag");

        private static TagKey<ConfiguredStructureFeature<?, ?>> tag(String name) {
            return TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(Crossovermod.MOD_ID, name));
        }
    }
}