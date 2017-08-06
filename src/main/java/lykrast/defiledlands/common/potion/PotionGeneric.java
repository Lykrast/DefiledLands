package lykrast.defiledlands.common.potion;

import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionGeneric extends Potion {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(DefiledLands.MODID, "textures/gui/potion_effects.png");

	public PotionGeneric(boolean isBadEffectIn, int liquidColorIn, int icon) {
		super(isBadEffectIn, liquidColorIn);
		setIconIndex(icon % 8, icon / 8);
	}

	@Override
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		return super.getStatusIconIndex();
	}

}
