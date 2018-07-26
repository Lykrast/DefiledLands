package lykrast.defiledlands.common.item;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.entity.passive.EntityBookWyrm;
import lykrast.defiledlands.common.util.LocUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBookWyrmAnalyzer extends Item {
	
	public ItemBookWyrmAnalyzer()
	{
		this.setMaxStackSize(1);
	}
	

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, EntityLivingBase entity, net.minecraft.util.EnumHand hand)
    {
        if (entity.world.isRemote)
        {
            return false;
        }
        if (entity instanceof EntityBookWyrm)
        {
        	EntityBookWyrm target = (EntityBookWyrm)entity;
        	
        	String base = "ui.defiledlands.book_wyrm_analyze.";
        	player.sendMessage(new TextComponentTranslation(base + "health", (int)target.getHealth(), (int)target.getMaxHealth()));
        	player.sendMessage(new TextComponentTranslation(base + "digest_time", target.getDigestTime()));
        	player.sendMessage(new TextComponentTranslation(base + "max_level", target.getMaxLevel()));
        	player.sendMessage(new TextComponentTranslation(base + "digested", target.digested));
        	if (target.digesting > 0)
            	player.sendMessage(new TextComponentTranslation(base + "digesting", target.digesting));
        	if (target.isChild())
        	{
        		int minutes = (int)Math.ceil((-target.getGrowingAge()) / 1200.0D);
            	player.sendMessage(new TextComponentTranslation(base + "maturing", minutes));
        	}
        	else if (target.getGrowingAge() > 0)
        	{
        		int minutes = (int)Math.ceil(target.getGrowingAge() / 1200.0D);
            	player.sendMessage(new TextComponentTranslation(base + "reproduce", minutes));
        	}
        	else player.sendMessage(new TextComponentTranslation(base + "ready"));
        	if (target.isGolden()) 
            	player.sendMessage(new TextComponentTranslation(base + "golden"));
        	
            return true;
        }
        
        return false;
    }
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(LocUtils.getTooltips(TextFormatting.GRAY.toString() + I18n.format(super.getUnlocalizedName(stack) + ".tooltip")));
	}

}
