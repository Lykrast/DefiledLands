package lykrast.defiledlands.common.util;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import lykrast.defiledlands.common.compat.CompatBaubles;
import lykrast.defiledlands.common.entity.IEntityDefiled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlantUtils {
	private PlantUtils() {}
	
	/**
	 * Is the given entity vulnerable to a mature Blastem ?
	 * @param ent
	 * @return
	 */
	public static boolean vulnerableToBlastem(Entity ent) {
		if (ent instanceof IEntityDefiled)
		{
			if (!((IEntityDefiled) ent).affectedByBlastem()) return false;
		}
		
		if (CompatBaubles.isLoaded() && ent instanceof EntityPlayer)
		{
			if (isWearingPhytoprostasia((EntityPlayer)ent)) return false;
		}
		
		return true;
	}
	/**
	 * Is the given entity vulnerable to Vilespine ?
	 * @param ent
	 * @return
	 */
	public static boolean vulnerableToVilespine(Entity ent) {
		if (ent instanceof IEntityDefiled)
		{
			if (!((IEntityDefiled) ent).affectedByVilespine()) return false;
		}
		
		if (CompatBaubles.isLoaded() && ent instanceof EntityPlayer)
		{
			if (isWearingPhytoprostasia((EntityPlayer)ent)) return false;
		}
		
		return true;
	}
	
	private static boolean isWearingPhytoprostasia(EntityPlayer player) {
		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
		
		for(int i = 0; i < baubles.getSlots(); i++)
		{
			if (baubles.getStackInSlot(i).getItem() == CompatBaubles.phytoprostasiaAmulet) return true;
		}
		
		return false;
	}
}
