package com.devmaster.random_mob_land.world.spawns;

import com.devmaster.random_mob_land.configs.RMLConfig;
import com.devmaster.random_mob_land.misc.Random_Mobs_Land;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = Random_Mobs_Land.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public record SpawnEventHandler() implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {

        if (phase == Phase.REMOVE) {
            MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();

            for (MobCategory category : MobCategory.values()) {
                spawns.getSpawner(category).clear();
            }

            Random_Mobs_Land.LOGGER.debug("Random Mobs Land: Cleared spawns for biome {}", biome);
        }

        if (phase == Phase.AFTER_EVERYTHING) {
            try {
                MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
                List<net.minecraft.world.entity.EntityType<?>> allEntities = new ArrayList<>(ForgeRegistries.ENTITY_TYPES.getValues());

                int numberOfEntitiesToAdd = RMLConfig.MobSpawnCount.get();
                int weight = RMLConfig.Mobweight.get();
                int minCount = RMLConfig.Mobmin.get();
                int maxCount = RMLConfig.Mobmax.get();

                if (numberOfEntitiesToAdd <= 0 || weight <= 0) {
                    return;
                }

                if (minCount > maxCount) {
                    Random_Mobs_Land.LOGGER.error("Random Mobs Land: Invalid spawn group size configuration. Min: {}, Max: {}", minCount, maxCount);
                    return;
                }

                int added = 0;

                for (MobCategory category : MobCategory.values()) {

                    List<EntityType<?>> filteredEntities = new ArrayList<>();

                    for (EntityType<?> entityType : allEntities) {
                        if (entityType.getCategory() == category) {
                            filteredEntities.add(entityType);
                        }
                    }

                    for (int i = 0; i < numberOfEntitiesToAdd; i++) {

                        if (filteredEntities.isEmpty()) {
                            continue;
                        }

                        EntityType<?> randomEntity = filteredEntities.get(ThreadLocalRandom.current().nextInt(filteredEntities.size()));
                        spawns.addSpawn(category, new MobSpawnSettings.SpawnerData(randomEntity, weight, minCount, maxCount));

                        added++;
                    }
                }

                Random_Mobs_Land.LOGGER.debug("Random Mobs Land: Added {} spawn entries for biome {}", added, biome);

            } catch (Exception e) {
                Random_Mobs_Land.LOGGER.error("Random Mobs Land: Failed to apply random spawns to biome {} during phase {}", biome, phase, e);
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return RegistryHandler.RANDOM_SPAWNS.get();
    }
}