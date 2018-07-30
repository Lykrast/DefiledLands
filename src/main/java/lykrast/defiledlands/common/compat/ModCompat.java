package lykrast.defiledlands.common.compat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;

import lykrast.defiledlands.common.util.Config;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraftforge.fml.common.Loader;

public abstract class ModCompat {
	public static HashMap<String, Class<? extends ModCompat>> compat = new HashMap<>();
	public static Set<ModCompat> compatLoaded = new HashSet<>();
	
	static
	{
		compat.put("chisel", CompatChisel.class);
		compat.put("projecte", CompatProjectE.class);
		compat.put("immersiveengineering", CompatImmersiveEngineering.class);
		compat.put("baubles", CompatBaubles.class);
		compat.put("tconstruct", CompatTinkersConstruct.class);
	}
	
	public static void preInitCompat() {
		for(Entry<String, Class<? extends ModCompat>> e : compat.entrySet())
		{
			if(Loader.isModLoaded(e.getKey()))
			{
				try
				{
					if (Config.compatEnabled.get(e.getKey()))
					{
						ModCompat c = e.getValue().newInstance();
						compatLoaded.add(c);
						c.preInit();
					}
				}
				catch(Exception ex)
				{
					DefiledLands.logger.log(Level.WARNING, "Compat module for " + e.getKey() + " could not be pre-initialized");
				}
			}
		}
		
	}
	
	public static void initCompat() {
		for(ModCompat compat : compatLoaded)
		{
			try
			{
				compat.init();
			}
			catch(Exception ex)
			{
				DefiledLands.logger.log(Level.WARNING, "Compat module for " + compat + " could not be initialized");
			}
		}
	}
	
	public static void postInitCompat() {
		for(ModCompat compat : compatLoaded)
		{
			try
			{
				compat.postInit();
			}
			catch(Exception ex)
			{
				DefiledLands.logger.log(Level.WARNING, "Compat module for " + compat + " could not be post-initialized");
			}
		}		
	}
	
	public void preInit() {}
	public void init() {}
	public void postInit() {}

}
