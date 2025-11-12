package de.adventurecraft.business.nsb_utils.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CrookItem extends Item {

    public CrookItem(Properties properties) {
        super(properties);
    }


    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide) {


            InteractionHand handUsed = entity.getMainHandItem() == stack
                    ? InteractionHand.MAIN_HAND
                    : InteractionHand.OFF_HAND;

            EquipmentSlot slot = (handUsed == InteractionHand.MAIN_HAND)
                    ? EquipmentSlot.MAINHAND
                    : EquipmentSlot.OFFHAND;


            if (state.is(Blocks.DIRT)) {
                stack.hurtAndBreak(1, entity, slot);
            }
        }


        return true;
    }
}
