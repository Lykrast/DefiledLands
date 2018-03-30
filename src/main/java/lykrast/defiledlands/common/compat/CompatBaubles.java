package lykrast.defiledlands.common.compat;

import baubles.api.BaubleType;
import lykrast.defiledlands.common.init.ModItems;
import lykrast.defiledlands.common.item.ItemBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompatBaubles extends ModCompat {
	private static boolean loaded = false;
	
	public static Item scarliteRing, phytoprostasiaAmulet;
	
	@Override
	public void preInit()
	{
		loaded = true;
		
		scarliteRing = ModItems.registerItem(new ItemBauble(BaubleType.RING) {
			@Override
			public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
				if (player.ticksExisted % 200 == 0) {
					player.heal(1);
				}
			}
		}, "scarlite_ring");
		phytoprostasiaAmulet = ModItems.registerItem(new ItemBauble(BaubleType.AMULET), "phytoprostasia_amulet");
	}
	
	public static boolean isLoaded() {
		return loaded;
	}
}
