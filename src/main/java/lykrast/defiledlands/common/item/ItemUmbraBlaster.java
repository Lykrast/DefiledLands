package lykrast.defiledlands.common.item;

import lykrast.defiledlands.common.entity.projectile.EntityBlastemFruit;
import lykrast.defiledlands.common.entity.projectile.EntityBlastemFruitBlazing;
import lykrast.defiledlands.common.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemUmbraBlaster extends Item {
	
	public ItemUmbraBlaster(int durability)
	{
        this.maxStackSize = 1;
        this.setMaxDamage(durability);
	}

    private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isBlastemFruit(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isBlastemFruit(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isBlastemFruit(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    protected boolean isBlastemFruit(ItemStack stack)
    {
        return stack.getItem() instanceof ItemBlastemFruit;
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemstack) > 0;
        ItemStack ammo = findAmmo(playerIn);

        if (!ammo.isEmpty() || flag)
        {
            if (ammo.isEmpty())
            {
            	ammo = new ItemStack(ModItems.blastemFruit);
            }
            
            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            
            if (!worldIn.isRemote)
            {
            	EntityBlastemFruit projectile;
            	if (ammo.getItem() == ModItems.blastemFruitBlazing) projectile = new EntityBlastemFruitBlazing(worldIn, playerIn);
            	else projectile = new EntityBlastemFruit(worldIn, playerIn);
            	
                projectile.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.spawnEntity(projectile);

                itemstack.damageItem(1, playerIn);
            }
            
            if (!flag)
            {
            	ammo.shrink(1);

                if (ammo.isEmpty())
                {
                	playerIn.inventory.deleteStack(ammo);
                }
            }

            playerIn.addStat(StatList.getObjectUseStats(this));
            
            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
        	return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }

}
