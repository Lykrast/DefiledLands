package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.common.potion.PotionGeneric;
import lykrast.defiledlands.common.util.LocUtils;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModPotions {
	
	public static Potion vulnerability, grounded, bleeding;
	private static List<Potion> potionList = new ArrayList<>();
	
	static
	{
		vulnerability = register(new PotionGeneric(true, 0x6f7689, 0, -1), "vulnerability");
		grounded = register(new PotionGeneric(true, 0x5f5e57, 1, 0), "grounded");
		bleeding = register(new PotionGeneric(true, 0xff0000, 2, 40), "bleeding");
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event)
	{
		for (Potion p : potionList) event.getRegistry().register(p);
		//We no longer use it afterwards
		potionList = null;
	}
	
	public static Potion register(Potion p, String name)
	{
		p.setPotionName("potion.effect." + LocUtils.prefix(name));
		p.setRegistryName(DefiledLands.MODID, name);
		potionList.add(p);
		return p;
	}

}
