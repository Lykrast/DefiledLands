package lykrast.defiledlands.core;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ModBlocks.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}

}
