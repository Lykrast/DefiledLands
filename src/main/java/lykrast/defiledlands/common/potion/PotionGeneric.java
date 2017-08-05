package lykrast.defiledlands.common.potion;

import net.minecraft.potion.Potion;

public class PotionGeneric extends Potion {

	public PotionGeneric(boolean isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
	}

    /**
     * Sets the index for the icon displayed in the player's inventory when the status is active.
     */
    public Potion setIconIndex(int p_76399_1_, int p_76399_2_)
    {
        super.setIconIndex(p_76399_1_, p_76399_2_);
        return this;
    }

}
