package com.devmaster.random_mob_land.world.spawns;

import com.devmaster.random_mob_land.misc.Random_Mobs_Land;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Random_Mobs_Land.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnEventHandler {

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        // Clear existing spawns
        for (EntityClassification classification : EntityClassification.values()) {
            event.getSpawns().getSpawner(classification).clear();
        }

        List<EntityType<?>> allEntities = new ArrayList<>(ForgeRegistries.ENTITIES.getValues());
        Random random = new Random();

        int numberOfEntitiesToAdd = 10; // This can be adjusted as per your requirement

        for (EntityClassification classification : EntityClassification.values()) {
            List<EntityType<?>> filteredEntities = new ArrayList<>();

            for (EntityType<?> entityType : allEntities) {
                if (entityType.getClassification() == classification) {
                    filteredEntities.add(entityType);
                }
            }

            for (int i = 0; i < numberOfEntitiesToAdd; i++) {
                if (!filteredEntities.isEmpty()) {
                    EntityType<?> randomEntity = filteredEntities.get(random.nextInt(filteredEntities.size()));
                    event.getSpawns().getSpawner(classification).add(new MobSpawnInfo.Spawners(randomEntity, 5, 1, 2));
                }
            }
        }
        if (!Config.RANDOMIZE_STRUCTURE_SPAWNS) {
            return; // Exit early if the configuration is not set to randomize
        }

        // Check if the spawn is due to a structure or jigsaw piece
        if (event.getSpawnReason() == SpawnReason.STRUCTURE) {
            // Cancel the current spawn
            event.setResult(Event.Result.DENY);

            // Get a random entity from your list
            EntityType<?> randomEntity = getRandomEntity();

            // Spawn the new random entity
            randomEntity.spawn((ServerWorld) event.getWorld(), null, null, event.getPos(), SpawnReason.STRUCTURE, false, false);
        }
    }
    }
}