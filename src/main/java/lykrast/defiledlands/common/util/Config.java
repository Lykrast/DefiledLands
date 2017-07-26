package lykrast.defiledlands.common.util;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;

import lykrast.defiledlands.common.compat.ModCompat;
import lykrast.defiledlands.core.CommonProxy;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraftforge.common.config.Configuration;

public class Config {
	private static final String CATEGORY_GENERAL = "General";
	private static final String CATEGORY_MOBS = "Mobs";
	private static final String CATEGORY_WORLD = "World";
	private static final String CATEGORY_COMPAT = "Compatibility";
	
	// General
	public static boolean confinedSpread;
	
	// Mobs
	public static boolean scuttlerSpawnInLight;
	
	// World
	public static int weightDesertDefiled, weightPlainsDefiled, weightForestTenebra, weightForestVilespine, weightHillsDefiled, weightSwampDefiled, weightIcePlainsDefiled;
	
	// Compat
	public static HashMap<String, Boolean> compatEnabled = new HashMap<String, Boolean>();

	public static void readConfig() {
		Configuration cfg = CommonProxy.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
		} catch (Exception e) {
			DefiledLands.logger.log(Level.WARNING, "Problem loading config file!", e);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}
	
	private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        cfg.addCustomCategoryComment(CATEGORY_MOBS, "Mobs");
        cfg.addCustomCategoryComment(CATEGORY_WORLD, "World generation");
        cfg.addCustomCategoryComment(CATEGORY_COMPAT, "Mod Compatibility");
        
        // General
        confinedSpread = cfg.getBoolean("confinedSpread", CATEGORY_GENERAL, true, 
        		"If true, defiled blocks won't spread corruption when outside defiled biomes");
        
        // Mobs
        scuttlerSpawnInLight = cfg.getBoolean("scuttlerSpawnInLight", CATEGORY_MOBS, true, 
        		"If true, Scuttlers will also spawn in lit places");
        
        // World
        weightDesertDefiled = cfg.getInt("weightDesertDefiled", CATEGORY_WORLD, 6, 0, 100, 
        		"Weight of Defiled Deserts in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightPlainsDefiled = cfg.getInt("weightPlainsDefiled", CATEGORY_WORLD, 6, 0, 100, 
        		"Weight of Defiled Plains in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightForestTenebra = cfg.getInt("weightForestTenebra", CATEGORY_WORLD, 6, 0, 100, 
        		"Weight of Tenebra Forests in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightForestVilespine = cfg.getInt("weightForestVilespine", CATEGORY_WORLD, 6, 0, 100, 
        		"Weight of Vilespine Forests in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightHillsDefiled = cfg.getInt("weightHillsDefiled", CATEGORY_WORLD, 6, 0, 100, 
        		"Weight of Defiled Hills in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightSwampDefiled = cfg.getInt("weightSwampDefiled", CATEGORY_WORLD, 6, 0, 100, 
        		"Weight of Defiled Swamps in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightIcePlainsDefiled = cfg.getInt("weightIcePlainsDefiled", CATEGORY_WORLD, 6, 0, 100, 
        		"Weight of Defiled Ice Plains in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        
        // Compat
        for(Entry<String, Class<? extends ModCompat>> e : ModCompat.compat.entrySet())
		{
        	compatEnabled.put(e.getKey(), cfg.getBoolean(e.getKey(), CATEGORY_COMPAT, true, "Enables compatibility with " + e.getKey()));
		}
	}

}
