package lykrast.defiledlands.common.potion;

import lykrast.defiledlands.common.init.ModPotions;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class PotionHooks {
	
	@SubscribeEvent
	public static void onHurt(LivingHurtEvent e)
	{
		if (e.getEntityLiving().isPotionActive(ModPotions.vulnerability))
		{
			PotionEffect effect = e.getEntityLiving().getActivePotionEffect(ModPotions.vulnerability);
			float mult = 1 + (0.2F * (effect.getAmplifier()+1));
			
			e.setAmount(e.getAmount() * mult);
		}
	}

}
