package com.devmaster.random_mobs_land;

import com.devmaster.random_mobs_land.proxy.CommonProxy;
import static com.devmaster.random_mobs_land.Random_Mobs_Land.NAME;
import static com.devmaster.random_mobs_land.Random_Mobs_Land.VERSION;
import static com.devmaster.random_mobs_land.Random_Mobs_Land.MOD_ID;
import com.devmaster.random_mobs_land.util.handlers.RMLConfig;

import com.devmaster.random_mobs_land.world.SpawnEventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = MOD_ID, name = NAME, version = VERSION)
public class Random_Mobs_Land {

	@Instance
	public static Random_Mobs_Land instance;
	public static final String MOD_ID = "random_mobs_land";
	public static final String NAME = "Random Mobs Land";
	public static final String VERSION = "1.0.0";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "com.devmaster.random_mobs_land.proxy.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "com.devmaster.random_mobs_land.proxy.CommonProxy";

	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		RMLConfig.init(event.getSuggestedConfigurationFile());

	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
	}
	
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
	}
	
}