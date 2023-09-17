package com.devmaster.random_mob_land.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RMLConfig {
    public static ForgeConfigSpec.IntValue MobSpawnCount;
    public static ForgeConfigSpec.IntValue Mobmin;
    public static ForgeConfigSpec.IntValue Mobmax;
    public static ForgeConfigSpec.IntValue Mobweight;
    public RMLConfig() {
    }

    public static void COMMON(ForgeConfigSpec.Builder builder) {
            builder.comment("How many mobs that spawn in a biome.");
            builder.push("Mob Spawn Count");
            MobSpawnCount = builder.defineInRange("Percentage", 5, 0, 1000);
            builder.pop();

            builder.push("Red Cow Spawn Chances");
            Mobmin = builder.defineInRange("Min in a group", 1, 0, 640);
            Mobmax = builder.defineInRange("Max in a group", 2, 0, 640);
            Mobweight = builder.defineInRange("Weight", 5, 0, 100);
            builder.pop();
        }

        }




