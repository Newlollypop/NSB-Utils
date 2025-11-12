package de.adventurecraft.business.nsb_utils.blocks;

import de.adventurecraft.business.nsb_utils.Nsb_utils;
import de.adventurecraft.business.nsb_utils.config.ModCommonConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = Nsb_utils.MODID)
public class BlockReplaceEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof Level level)) {
            return;
        }

        // Only server-side
        if (level.isClientSide()) {
            return;
        }

        // Check config toggle: if disabled, do nothing
        if (!ModCommonConfigs.ENABLE_STONE_REPLACEMENT.get()) {
            return;
        }

        BlockState brokenState = event.getState();
        BlockPos pos = event.getPos();

        // Check if the broken block is "minecraft:stone"
        if (brokenState.is(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("minecraft", "stone")))) {
            // Prevent the default drop / behavior
            event.setCanceled(true);

            // Drop exdeorum:stone_pebble if present in the registry
            Item dropItem = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("exdeorum", "stone_pebble"));
            if (dropItem != null) {
                ItemStack dropStack = new ItemStack(dropItem, 1);
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        dropStack);
                level.addFreshEntity(itemEntity);
            }

            // Replace with your cracked stone block (ModBlocks must be available in the project)
            level.setBlock(pos, ModBlocks.CRACKED_STONE.get().defaultBlockState(), 3);
        }
    }
}
