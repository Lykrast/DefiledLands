package lykrast.defiledlands.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFoulCandy extends ItemFood {

	public ItemFoulCandy(){
		super(2, 0.4F, false);
	}

	@Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
        }
    }
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
		return 16;
    }

}
