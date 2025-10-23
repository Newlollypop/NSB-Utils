package de.adventurecraft.business.nsb_utils.blocks;

import de.adventurecraft.business.nsb_utils.Nsb_utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.fml.common.EventBusSubscriber;


@EventBusSubscriber(modid = Nsb_utils.MODID)
public class BlockReplaceEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getLevel() instanceof Level level && !level.isClientSide()) {
            BlockState brokenState = event.getState();
            BlockPos pos = event.getPos();


            if (brokenState.is(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("minecraft", "stone")))) {
                event.setCanceled(true);


                Item dropItem = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("exdeorum", "stone_pebble"));


                if (dropItem != null) {
                    ItemStack dropStack = new ItemStack(dropItem, 1);
                    ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, dropStack);
                    level.addFreshEntity(itemEntity);
                }


                level.setBlock(pos, ModBlocks.CRACKED_STONE.get().defaultBlockState(), 3);
            }
        }
    }
}

