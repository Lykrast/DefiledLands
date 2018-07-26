package lykrast.defiledlands.common.item;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.init.ModEnchantments;
import lykrast.defiledlands.common.util.LocUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class ItemGun extends Item {

	public ItemGun(int durability)
	{
        this.maxStackSize = 1;
        this.setMaxDamage(durability);
	}

	protected abstract boolean isAmmo(ItemStack stack);

	protected ItemStack findAmmo(EntityPlayer player) {
	    if (this.isAmmo(player.getHeldItem(EnumHand.OFF_HAND)))
	    {
	        return player.getHeldItem(EnumHand.OFF_HAND);
	    }
	    else if (this.isAmmo(player.getHeldItem(EnumHand.MAIN_HAND)))
	    {
	        return player.getHeldItem(EnumHand.MAIN_HAND);
	    }
	    else
	    {
	        for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
	        {
	            ItemStack itemstack = player.inventory.getStackInSlot(i);
	
	            if (this.isAmmo(itemstack))
	            {
	                return itemstack;
	            }
	        }
	
	        return ItemStack.EMPTY;
	    }
	}
	
	protected void consumeAmmo(ItemStack gun, ItemStack ammo, EntityPlayer playerIn, Random rand)
	{
		int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.economical, gun);
		
		if (level > 0 && rand.nextInt(level + 2) > 1) return;
		
		ammo.shrink(1);

        if (ammo.isEmpty())
        {
        	playerIn.inventory.deleteStack(ammo);
        }
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability() {
	    return 1;
	}
	
	protected float getSharpshooterBonus(ItemStack gun)
	{
		return 1.0F + 0.5F * EnchantmentHelper.getEnchantmentLevel(ModEnchantments.sharpshooter, gun);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(LocUtils.getTooltips(TextFormatting.GRAY.toString() + I18n.format(super.getUnlocalizedName(stack) + ".tooltip")));
	}

}