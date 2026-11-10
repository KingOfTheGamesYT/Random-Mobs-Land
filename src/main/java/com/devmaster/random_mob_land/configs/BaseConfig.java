package com.devmaster.random_mob_land.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class BaseConfig {
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON;

    static {
        RMLConfig.COMMON(COMMON_BUILDER);
        COMMON= COMMON_BUILDER.build();
    }
}