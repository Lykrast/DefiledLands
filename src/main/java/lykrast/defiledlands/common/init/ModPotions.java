package lykrast.defiledlands.common.init;

import java.awt.Color;

import lykrast.defiledlands.common.potion.PotionGeneric;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotions {
	
	public static Potion vulnerability;
	
	public static void init()
	{
		vulnerability = register(new PotionGeneric(true, 0x6f7689, 0), "vulnerability");
	}
	
	public static Potion register(Potion p, String name)
	{
		p.setPotionName("potion.effect." + DefiledLands.MODID + "." + name);
		p.setRegistryName(DefiledLands.MODID, name);
		ForgeRegistries.POTIONS.register(p);
		return p;
	}

}
