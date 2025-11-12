package de.adventurecraft.business.nsb_utils;

import com.mojang.logging.LogUtils;
import de.adventurecraft.business.nsb_utils.blocks.ModBlocks;
import de.adventurecraft.business.nsb_utils.config.ModCommonConfigs;
import de.adventurecraft.business.nsb_utils.items.ModCreativeModeTabs;
import de.adventurecraft.business.nsb_utils.items.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;





@Mod(Nsb_utils.MODID)
public class Nsb_utils {
    public static final String MODID = "nsb_utils";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Nsb_utils(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);


        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, ModCommonConfigs.COMMON_CONFIG);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }



}
