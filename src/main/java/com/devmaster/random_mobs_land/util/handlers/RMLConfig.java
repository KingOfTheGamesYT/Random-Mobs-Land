package com.devmaster.random_mobs_land.util.handlers;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class RMLConfig {

    public static int MobSpawnCount;
    public static int Mobmin;
    public static int Mobmax;
    public static int Mobweight;

    private static Configuration config;

    public static void init(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            loadConfig();
        }
    }

    private static void loadConfig() {
        MobSpawnCount = config.getInt(
                "MobSpawnCount",
                Configuration.CATEGORY_GENERAL,
                5,
                0,
                1000,
                "How many mobs spawn in a biome."
        );

        Mobmin = config.getInt(
                "MobMin",
                Configuration.CATEGORY_GENERAL,
                1,
                0,
                640,
                "Minimum mobs per group"
        );

        Mobmax = config.getInt(
                "MobMax",
                Configuration.CATEGORY_GENERAL,
                2,
                0,
                640,
                "Maximum mobs per group"
        );

        Mobweight = config.getInt(
                "MobWeight",
                Configuration.CATEGORY_GENERAL,
                5,
                0,
                100,
                "Mob spawn weight"
        );

        if (config.hasChanged()) {
            config.save();
        }
    }
}