package de.adventurecraft.business.nsb_utils.items;

import de.adventurecraft.business.nsb_utils.Nsb_utils;
import de.adventurecraft.business.nsb_utils.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Nsb_utils.MODID);

    public static final Supplier<CreativeModeTab> NSB_UTILS_ITEMS_TAB = CREATIVE_MODE_TAB.register("nsb_utils_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CROOK.get()))
                    .title(Component.translatable("creativetab.nsbutils.nsb_utils_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.STICK);
                        output.accept(ModItems.CROOK);
                    }).build());

    public static final Supplier<CreativeModeTab> NSB_UTILS_BLOCKS_TAB = CREATIVE_MODE_TAB.register("nsb_utils_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CRACKED_STONE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Nsb_utils.MODID, "nsb_utils_items_tab"))
                    .title(Component.translatable("creativetab.nsbutils.nsb_utils_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CRACKED_STONE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
