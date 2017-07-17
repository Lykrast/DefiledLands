package lykrast.defiledlands.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemScuttlerEye extends ItemFood {

	public ItemScuttlerEye(){
		super(2, 3.2F, false);
		this.setAlwaysEdible();
	}

	@Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200));
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 200));
            player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600));
        }
    }

}
