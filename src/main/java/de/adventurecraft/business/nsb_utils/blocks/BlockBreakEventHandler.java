package de.adventurecraft.business.nsb_utils.blocks;

import de.adventurecraft.business.nsb_utils.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = "nsb_utils")
public class BlockBreakEventHandler {

    private static final List<Item> SAPLINGS = new ArrayList<>();

    static {
        SAPLINGS.add(Blocks.OAK_SAPLING.asItem());
        SAPLINGS.add(Blocks.BIRCH_SAPLING.asItem());
        SAPLINGS.add(Blocks.SPRUCE_SAPLING.asItem());
        SAPLINGS.add(Blocks.DARK_OAK_SAPLING.asItem());
        SAPLINGS.add(Blocks.ACACIA_SAPLING.asItem());
        SAPLINGS.add(Blocks.JUNGLE_SAPLING.asItem());
        SAPLINGS.add(Blocks.CHERRY_SAPLING.asItem());
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Level level = (Level) event.getLevel();
        Player player = event.getPlayer();


        if (level.isClientSide() || player == null) {
            return;
        }

        ItemStack mainHandItem = player.getMainHandItem();

        if (event.getState().is(Blocks.DIRT) && mainHandItem.is(ModItems.CROOK.get())) {

            event.setCanceled(true);

            BlockPos pos = event.getPos();
            RandomSource random = level.getRandom();

            int dropCount = random.nextInt(4) + 2;
            for (int i = 0; i < dropCount; i++) {
                Item randomSapling = SAPLINGS.get(random.nextInt(SAPLINGS.size()));
                Block.popResource(level, pos, new ItemStack(randomSapling));
            }

            mainHandItem.hurtAndBreak(
                    1,
                    player,
                    EquipmentSlot.MAINHAND
            );

            level.destroyBlock(pos, false);
        }
    }
}
