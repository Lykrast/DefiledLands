package lykrast.defiledlands.core;

import java.io.File;

import lykrast.defiledlands.common.compat.ModCompat;
import lykrast.defiledlands.common.init.ModBiomes;
import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.init.ModEntities;
import lykrast.defiledlands.common.init.ModItems;
import lykrast.defiledlands.common.init.ModRecipes;
import lykrast.defiledlands.common.util.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public static Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "defiled_lands.cfg"));
        Config.readConfig();
        
		ModBlocks.init();
		ModItems.init();
		ModRecipes.init();
		ModEntities.init();
		ModBiomes.registerBiomes();
		
		ModCompat.preInitCompat();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		ModCompat.initCompat();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		ModCompat.postInitCompat();
	}

}
