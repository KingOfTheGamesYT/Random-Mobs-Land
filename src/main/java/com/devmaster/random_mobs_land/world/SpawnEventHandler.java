package com.devmaster.random_mobs_land.world;

import com.devmaster.random_mobs_land.Random_Mobs_Land;
import com.devmaster.random_mobs_land.util.handlers.RMLConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;

import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnEventHandler {

    @SubscribeEvent
    public static void onBiomeLoad(BiomeEvent event) {
        Biome biome = event.getBiome();

        // Clear all existing spawns
        for (EnumCreatureType type : EnumCreatureType.values()) {
            biome.getSpawnableList(type).clear();
        }

        List<Class<? extends EntityLiving>> allEntities = new ArrayList<>();
        ForgeRegistries.ENTITIES.getValues().forEach(entityEntry -> {
            if (EntityLiving.class.isAssignableFrom(entityEntry.getEntityClass())) {
                allEntities.add((Class<? extends EntityLiving>) entityEntry.getEntityClass());
            }
        });

        Random random = new Random();
        int numberOfEntitiesToAdd = RMLConfig.MobSpawnCount; // Adapt this to how you store config in 1.12.2

        for (EnumCreatureType type : EnumCreatureType.values()) {
            List<Class<? extends EntityLiving>> filteredEntities = new ArrayList<>();

            for (Class<? extends EntityLiving> entityClass : allEntities) {
                try {
                    if (entityClass.getConstructor(net.minecraft.world.World.class).newInstance((Object) null)
                            .isCreatureType(type, true)) {
                        filteredEntities.add(entityClass);
                    }
                } catch (Exception ignored) {}
            }

            for (int i = 0; i < numberOfEntitiesToAdd; i++) {
                if (!filteredEntities.isEmpty()) {
                    Class<? extends EntityLiving> randomEntity = filteredEntities.get(random.nextInt(filteredEntities.size()));
                    biome.getSpawnableList(type).add(
                            new Biome.SpawnListEntry(randomEntity, RMLConfig.Mobweight, RMLConfig.Mobmin, RMLConfig.Mobmax)
                    );
                }
            }
        }
    }
}
