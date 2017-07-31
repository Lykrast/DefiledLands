package lykrast.defiledlands.common.init;

import lykrast.defiledlands.common.enchantment.EnchantmentBlazing;
import lykrast.defiledlands.common.enchantment.EnchantmentDestructive;
import lykrast.defiledlands.common.enchantment.EnchantmentEconomical;
import lykrast.defiledlands.common.enchantment.EnchantmentSafeguard;
import lykrast.defiledlands.common.enchantment.EnchantmentSharpshooter;
import lykrast.defiledlands.common.item.IEnchantDestructive;
import lykrast.defiledlands.common.item.ItemGun;
import lykrast.defiledlands.common.item.ItemUmbraBlaster;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModEnchantments {
	public static final EnumEnchantmentType GUN = EnumHelper.addEnchantmentType("GUN", (item) -> item instanceof ItemGun),
			DESTRUCTIVE = EnumHelper.addEnchantmentType("DESTRUCTIVE", (item) -> item instanceof IEnchantDestructive),
			UMBRA_BLASTER = EnumHelper.addEnchantmentType("UMBRA_BLASTER", (item) -> item instanceof ItemUmbraBlaster);
	
	public static Enchantment sharpshooter, economical,
	destructive, safeguard, blazing;
	
	public static void init()
	{
		sharpshooter = register(new EnchantmentSharpshooter(Enchantment.Rarity.COMMON), "sharpshooter");
		economical = register(new EnchantmentEconomical(Enchantment.Rarity.UNCOMMON), "economical");

		destructive = register(new EnchantmentDestructive(Enchantment.Rarity.COMMON), "destructive");
		safeguard = register(new EnchantmentSafeguard(Enchantment.Rarity.COMMON), "safeguard");
		blazing = register(new EnchantmentBlazing(Enchantment.Rarity.VERY_RARE), "blazing");
	}
	
	public static Enchantment register(Enchantment enchant, String name)
	{
		enchant.setName(name).setRegistryName(DefiledLands.MODID, name);
		ForgeRegistries.ENCHANTMENTS.register(enchant);
		return enchant;
	}

}
