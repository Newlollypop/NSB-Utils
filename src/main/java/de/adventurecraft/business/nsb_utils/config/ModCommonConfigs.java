package de.adventurecraft.business.nsb_utils.config;

import net.neoforged.neoforge.common.ModConfigSpec;


public class ModCommonConfigs {

    public static final ModConfigSpec COMMON_CONFIG;

    public static final ModConfigSpec.BooleanValue ENABLE_STONE_REPLACEMENT;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("General Settings");

        ENABLE_STONE_REPLACEMENT = builder
                .comment("If true, breaking stone replaces it with cracked stone and drops pebbles.")
                .define("enableStoneReplacement", true);

        builder.pop();

        COMMON_CONFIG = builder.build();
    }
}
