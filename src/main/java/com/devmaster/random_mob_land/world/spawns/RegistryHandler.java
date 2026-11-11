package com.devmaster.random_mob_land.world.spawns;

import com.devmaster.random_mob_land.misc.Random_Mobs_Land;

import com.mojang.serialization.Codec;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryHandler {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Random_Mobs_Land.MOD_ID);
    public static final RegistryObject<Codec<SpawnEventHandler>> RANDOM_SPAWNS = BIOME_MODIFIER_SERIALIZERS.register("random_spawns", () -> Codec.unit(SpawnEventHandler::new));
}
