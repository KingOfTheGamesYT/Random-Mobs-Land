package com.devmaster.random_mob_land.misc;

import com.devmaster.random_mob_land.configs.BaseConfig;
import com.devmaster.random_mob_land.world.spawns.RegistryHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("random_mobs_land")
public class Random_Mobs_Land {

    public static final Logger LOGGER = LogManager.getLogger("Random Mobs Land");
    public static final String MOD_ID = "random_mobs_land";

    public Random_Mobs_Land() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        RegistryHandler.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BaseConfig.COMMON);

    }

    private void setup(final FMLCommonSetupEvent event) {
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
    }
}



