package lykrast.defiledlands.common.init;

import lykrast.defiledlands.common.enchantment.*;
import lykrast.defiledlands.common.item.ItemUmbraBlaster;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModEnchantments {
	public static final EnumEnchantmentType UMBRA_BLASTER = EnumHelper.addEnchantmentType("UMBRA_BLASTER", (item) -> item instanceof ItemUmbraBlaster);
	
	public static Enchantment ubSafeguard, ubSharpshooter, ubDestructive, ubBlazing;
	
	public static void init()
	{
		ubSafeguard = register(new EnchantmentUBSafeguard(Enchantment.Rarity.COMMON), "ub_safeguard");
		ubSharpshooter = register(new EnchantmentUBSharpshooter(Enchantment.Rarity.COMMON), "ub_sharpshooter");
		ubDestructive = register(new EnchantmentUBDestructive(Enchantment.Rarity.COMMON), "ub_destructive");
		ubBlazing = register(new EnchantmentUBBlazing(Enchantment.Rarity.VERY_RARE), "ub_blazing");
	}
	
	public static Enchantment register(Enchantment enchant, String name)
	{
		enchant.setName(name).setRegistryName(DefiledLands.MODID, name);
		ForgeRegistries.ENCHANTMENTS.register(enchant);
		return enchant;
	}

}
