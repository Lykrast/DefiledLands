package lykrast.defiledlands.common.potion;

import lykrast.defiledlands.common.init.ModPotions;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionGeneric extends Potion {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(DefiledLands.MODID, "textures/gui/potion_effects.png");
	protected int tickrate;

	public PotionGeneric(boolean isBadEffectIn, int liquidColorIn, int icon, int rate) {
		super(isBadEffectIn, liquidColorIn);
		setIconIndex(icon % 8, icon / 8);
		tickrate = rate;
	}

	@Override
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		return super.getStatusIconIndex();
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
		if (this == ModPotions.grounded)
		{
			if (!entityLivingBaseIn.onGround)
			{
				if (entityLivingBaseIn.motionY > 0) entityLivingBaseIn.motionY /= (double)(amplifier + 2);
				entityLivingBaseIn.motionY -= 0.05D * (amplifier + 1);
			}
		}
    }
	
	@Override
    public boolean isReady(int duration, int amplifier)
    {
		if (tickrate < 0) return false;
		
		int k = tickrate >> amplifier;
		return k <= 0 || duration % k == 0;
    }

}
