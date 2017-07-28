package lykrast.defiledlands.common.item;

import lykrast.defiledlands.common.init.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public interface IEnchantDestructive {
	
	default float getDestructiveBonus(ItemStack gun)
	{
		int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.destructive, gun);
		
		if (i > 0) return 1.0F + (i + 1) * 0.25F;
		else return 1.0F;
	}

}
