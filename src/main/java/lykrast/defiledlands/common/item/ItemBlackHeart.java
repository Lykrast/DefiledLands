package lykrast.defiledlands.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBlackHeart extends ItemFood {

	public ItemBlackHeart(){
		super(4, 0.1F, false);
	}

	@Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200));
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 3));
            player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 200, 3));
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 200, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600));
        }
    }

}
