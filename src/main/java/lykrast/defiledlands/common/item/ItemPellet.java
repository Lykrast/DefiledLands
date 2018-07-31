package lykrast.defiledlands.common.item;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.entity.projectile.EntityRavagerProjectile;
import lykrast.defiledlands.common.init.ModPotions;
import lykrast.defiledlands.common.util.LocUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPellet extends Item implements IPellet {
	private int damage;
	
	public ItemPellet(int damage) {
		super();
		this.damage = damage;
	}
	
	public EntityRavagerProjectile applyAttributes(EntityRavagerProjectile projectile, ItemStack stack) {
		projectile.setItem(new ItemStack(this));
		projectile.setDamage(damage);
		return projectile;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(LocUtils.getTooltips(TextFormatting.GRAY.toString() + I18n.format(super.getUnlocalizedName(stack) + ".tooltip")));
	}
	
	public static class Spiked extends ItemPellet {
		public Spiked(int damage) {
			super(damage);
		}

		@Override
		public void onHit(EntityLivingBase target, EntityLivingBase source, ItemStack pellet) {
			int i = 0;
			if (target.isPotionActive(ModPotions.bleeding))
			{
				i = target.getActivePotionEffect(ModPotions.bleeding).getAmplifier() + 1;
				i = Math.min(i, 255);
			}
			target.addPotionEffect(new PotionEffect(ModPotions.bleeding, 200, i));
		}
	}
	
	public static class Ravaging extends ItemPellet {
		public Ravaging(int damage) {
			super(damage);
		}

		@Override
		public void onHit(EntityLivingBase target, EntityLivingBase source, ItemStack pellet) {
			target.addPotionEffect(new PotionEffect(ModPotions.vulnerability, 200));
		}
		
	    @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack stack) {
	        return true;
	    }
	}
}
