package lykrast.defiledlands.core;

import java.util.logging.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DefiledLands.MODID, version = DefiledLands.VERSION, acceptedMinecraftVersions = "[1.12,1.13)")
public class DefiledLands {
	
	public static final String MODID = "defiledlands";
    public static final String VERSION = "1.2.0";
    
    @Instance(MODID)
    public static DefiledLands instance;
    
    public static Logger logger;
    
    @SidedProxy(clientSide = "lykrast.defiledlands.core.ClientProxy", serverSide = "lykrast.defiledlands.core.CommonProxy")
	public static CommonProxy proxy;
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

}
