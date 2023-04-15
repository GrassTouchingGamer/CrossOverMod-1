package net.code.crossover.item.custom.mario;

import com.mojang.logging.LogUtils;
import net.code.crossover.block.mario.WarpPipeBlock;
import net.code.crossover.block.mario.entity.WarpPipeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Optional;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.MAX_DISTANCE;

public class WarpConnectorItem extends Item {
    private static final String POS_KEY = "PipePos";
    private static final String DIMENSION_KEY = "PipeDimension";
    private static final Logger LOGGER = LogUtils.getLogger();

    public WarpConnectorItem(Properties properties) {
        super(properties);
    }

    public static boolean isConnected(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null && (tag.contains(DIMENSION_KEY) || tag.contains(POS_KEY));
    }

    private static Optional<ResourceKey<net.minecraft.world.level.Level>> getDimension(CompoundTag tag) {
        return net.minecraft.world.level.Level.RESOURCE_KEY_CODEC.parse(NbtOps.INSTANCE, tag.get(DIMENSION_KEY)).result();
    }

    @Nullable
    private static GlobalPos createWarpPos(CompoundTag tag) {
        Optional<ResourceKey<net.minecraft.world.level.Level>> optional;
        boolean b1 = tag.contains(POS_KEY);
        boolean b2 = tag.contains(DIMENSION_KEY);
        if (b1 && b2 && (optional = WarpConnectorItem.getDimension(tag)).isPresent()) {
            BlockPos blockPos = NbtUtils.readBlockPos(tag.getCompound(POS_KEY));
            return GlobalPos.of(optional.get(), blockPos);
        }
        return null;
    }
    private InteractionResult reset(Level pLevel, BlockPos blockPos, CompoundTag pTag, boolean Break) {
        pTag.remove(POS_KEY);
        pTag.remove(DIMENSION_KEY);
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    private void link(BlockPos blockPos, Level pLevel, CompoundTag tag, WarpPipeBlockEntity warpPipeBlockEntity, WarpPipeBlockEntity warpPipeBlockEntity1) {
        warpPipeBlockEntity.setDestination(warpPipeBlockEntity1.getBlockPos());
        warpPipeBlockEntity1.setDestination(blockPos);
        tag.remove(POS_KEY);
        tag.remove(DIMENSION_KEY);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level level, Entity entity, int slot, boolean selected) {
        if (level.isClientSide) {
            return;
        }
        if (WarpConnectorItem.isConnected(pStack)) {
            CompoundTag compoundTag = pStack.getOrCreateTag();
            GlobalPos globalPos = WarpConnectorItem.createWarpPos(compoundTag);
            if (globalPos == null || !(entity instanceof Player)) return;
            boolean bl = ((Player) entity).getOffhandItem() == pStack;
            if (selected || bl) {
                if (!globalPos.pos().closerThan(entity.getOnPos(), MAX_DISTANCE)) {
                    InteractionHand hand = InteractionHand.MAIN_HAND;
                    if (bl)
                        hand = InteractionHand.OFF_HAND;
                    InteractionHand finalHand = hand;
                    pStack.hurtAndBreak(2, (Player) entity, p -> p.broadcastBreakEvent(finalHand));
                    this.reset(level, entity.blockPosition(), compoundTag, true);
                }
            }
        }
    }    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        Level pLevel = context.getLevel();
        InteractionResult result = super.useOn(context);
        if (WarpPipeBlock.isLinkable(pLevel, blockPos)) {
            ItemStack itemStack = context.getItemInHand();
            if (WarpConnectorItem.isConnected(itemStack)) {
                CompoundTag tag = itemStack.getOrCreateTag();
                GlobalPos globalPos = WarpConnectorItem.createWarpPos(tag);
                if (globalPos == null)
                    return result;
                if (globalPos.pos().equals(blockPos)) {
                    return this.reset(pLevel, blockPos, tag, false);
                }
                BlockEntity blockEntity = pLevel.getBlockEntity(blockPos);
                BlockEntity blockEntity1 = pLevel.getBlockEntity(globalPos.pos());
                if (blockEntity instanceof WarpPipeBlockEntity warpPipeBE && blockEntity1 instanceof WarpPipeBlockEntity warpPipeBE1) {
                    if (WarpPipeBlock.isLinkable(pLevel, globalPos.pos())) {
                        if (player != null)
                            itemStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
                        this.link(blockPos, pLevel, tag, warpPipeBE, warpPipeBE1);
                        return InteractionResult.sidedSuccess(pLevel.isClientSide());
                    }
                } else return this.reset(pLevel, blockPos, tag, false);
            } else {
                Player contextPlayer = context.getPlayer();
                if (contextPlayer == null)
                    return result;
                this.writeTag(pLevel.dimension(), blockPos, itemStack.getOrCreateTag());
                return InteractionResult.sidedSuccess(pLevel.isClientSide());
            }
        }
        return result;
    }

    private void writeTag(ResourceKey<Level> worldKey, BlockPos pos, CompoundTag nbt) {
        nbt.put(POS_KEY, NbtUtils.writeBlockPos(pos));
        Level.RESOURCE_KEY_CODEC.encodeStart(NbtOps.INSTANCE, worldKey).resultOrPartial(LOGGER::error).ifPresent(nbtElement -> nbt.put(DIMENSION_KEY, nbtElement));
    }
}