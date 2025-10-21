package de.adventurecraft.business.nsb_utils.items;

import de.adventurecraft.business.nsb_utils.Nsb_utils;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Nsb_utils.MODID);

    public static final DeferredItem<Item> CROOK = ITEMS.register("stone_crook",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STICK = ITEMS.register("stone_stick",
            () -> new Item(new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
