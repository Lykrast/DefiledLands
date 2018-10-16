package lykrast.defiledlands.common.util;

import java.util.HashMap;
import java.util.Map.Entry;

import lykrast.defiledlands.common.compat.ModCompat;
import lykrast.defiledlands.core.CommonProxy;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraftforge.common.config.Configuration;

public class Config {
	private static final String CATEGORY_GENERAL = "General";
	private static final String CATEGORY_MOBS = "Mobs";
	private static final String CATEGORY_BOOK_WYRM = "Book Wyrms";
	private static final String CATEGORY_WORLD = "World";
	private static final String CATEGORY_COMPAT = "Compatibility";
	
	// General
	public static boolean confinedSpread;
	
	// Mobs
	public static boolean scuttlerSpawnInLight;
	public static int weightShambler, weightTwistedShambler, weightScuttler, weightHost;
	
	// Book Wyrms
	public static boolean bookWyrmSpawn;
	public static float conversionRate;
	
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
			DefiledLands.LOGGER.warn("Problem loading config file!", e);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}
	
	private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        cfg.addCustomCategoryComment(CATEGORY_MOBS, "Mobs");
        cfg.addCustomCategoryComment(CATEGORY_BOOK_WYRM, "Book Wyrms");
        cfg.addCustomCategoryComment(CATEGORY_WORLD, "World generation");
        cfg.addCustomCategoryComment(CATEGORY_COMPAT, "Mod Compatibility");
        
        // General
        confinedSpread = cfg.getBoolean("confinedSpread", CATEGORY_GENERAL, true, 
        		"If true, defiled blocks won't spread corruption when outside defiled biomes");
        
        // Mobs
        scuttlerSpawnInLight = cfg.getBoolean("scuttlerSpawnInLight", CATEGORY_MOBS, true, 
        		"If true, Scuttlers will also spawn in lit places");
        weightShambler = cfg.getInt("weightShambler", CATEGORY_MOBS, 80, 0, 1000, 
        		"Weight of Shamblers when spawning with greater weight meaning more common, 0 prevents spawning; this is only relevant in relation to other defiled mobs");
        weightTwistedShambler = cfg.getInt("weightTwistedShambler", CATEGORY_MOBS, 80, 0, 1000, 
        		"Weight of Twisted Shamblers when spawning with greater weight meaning more common, 0 prevents spawning; this is only relevant in relation to other defiled mobs");
        weightScuttler = cfg.getInt("weightScuttler", CATEGORY_MOBS, 100, 0, 1000, 
        		"Weight of Scuttlers when spawning with greater weight meaning more common, 0 prevents spawning; this is only relevant in relation to other defiled mobs");
        weightHost = cfg.getInt("weightHost", CATEGORY_MOBS, 100, 0, 1000, 
        		"Weight of Hosts when spawning with greater weight meaning more common, 0 prevents spawning; this is only relevant in relation to other defiled mobs");
        
        // Book Wyrms
        bookWyrmSpawn = cfg.getBoolean("bookWyrmSpawn", CATEGORY_BOOK_WYRM, true, 
        		"Can Book Wyrms naturally spawn");
        conversionRate = cfg.getFloat("conversionRate", CATEGORY_BOOK_WYRM, 1.0F, 0.01F, 10.0F, 
        		"Multiplier applied to enchanted books's level fed to Book Wyrms, highly recommended to keep below 1");
        
        // World
        weightDesertDefiled = cfg.getInt("weightDesertDefiled", CATEGORY_WORLD, 3, 0, 100, 
        		"Weight of Defiled Deserts in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightPlainsDefiled = cfg.getInt("weightPlainsDefiled", CATEGORY_WORLD, 3, 0, 100, 
        		"Weight of Defiled Plains in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightForestTenebra = cfg.getInt("weightForestTenebra", CATEGORY_WORLD, 3, 0, 100, 
        		"Weight of Tenebra Forests in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightForestVilespine = cfg.getInt("weightForestVilespine", CATEGORY_WORLD, 3, 0, 100, 
        		"Weight of Vilespine Forests in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightHillsDefiled = cfg.getInt("weightHillsDefiled", CATEGORY_WORLD, 3, 0, 100, 
        		"Weight of Defiled Hills in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightSwampDefiled = cfg.getInt("weightSwampDefiled", CATEGORY_WORLD, 3, 0, 100, 
        		"Weight of Defiled Swamps in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        weightIcePlainsDefiled = cfg.getInt("weightIcePlainsDefiled", CATEGORY_WORLD, 3, 0, 100, 
        		"Weight of Defiled Ice Plains in generation with greater weight meaning more common, 10 is most vanilla biomes, 0 prevents generation");
        
        // Compat
        for(Entry<String, Class<? extends ModCompat>> e : ModCompat.compat.entrySet())
		{
        	compatEnabled.put(e.getKey(), cfg.getBoolean(e.getKey(), CATEGORY_COMPAT, true, "Enables compatibility with " + e.getKey()));
		}
	}

}
