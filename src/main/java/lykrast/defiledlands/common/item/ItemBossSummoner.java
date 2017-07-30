package lykrast.defiledlands.common.item;

import lykrast.defiledlands.common.block.BlockConjuringAltar;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class ItemBossSummoner extends Item {
	
	public ItemBossSummoner()
	{
		setMaxStackSize(1);
	}
	
	/**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.Block block = worldIn.getBlockState(pos).getBlock();
        
        if (block instanceof BlockConjuringAltar)
        {
        	if (((BlockConjuringAltar) block).isActive(worldIn, pos) && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL)
        	{
        		if (!worldIn.isRemote)
        		{
        			Entity boss = getBoss(worldIn);
        			boss.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, player.getRotatedYaw(Rotation.CLOCKWISE_180), 0.0F);

                    for (EntityPlayerMP entityplayermp : worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, boss.getEntityBoundingBox().grow(50.0D)))
                    {
                        CriteriaTriggers.SUMMONED_ENTITY.trigger(entityplayermp, boss);
                    }
                    
        			worldIn.spawnEntity(boss);
        		}
        		
        		itemstack.shrink(1);
            	return EnumActionResult.SUCCESS;
        	}
        	else
        	{
            	return EnumActionResult.FAIL;
        	}
        }
        else
        {
        	return EnumActionResult.FAIL;
        }
    }
    
    protected abstract Entity getBoss(World worldIn);

}
