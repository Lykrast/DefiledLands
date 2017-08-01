package lykrast.defiledlands.common.item;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.entity.passive.EntityBookWyrm;
import lykrast.defiledlands.common.util.LocUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
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
        	player.sendMessage(new TextComponentString(I18n.translateToLocal(String.format("%s%s", base, "health"))
        			+ (int)target.getHealth() + "/" + (int)target.getMaxHealth()));
        	player.sendMessage(new TextComponentString(I18n.translateToLocal(String.format("%s%s", base, "digest_time"))
        			+ target.getDigestTime()));
        	player.sendMessage(new TextComponentString(I18n.translateToLocal(String.format("%s%s", base, "max_level"))
        			+ target.getMaxLevel()));
        	player.sendMessage(new TextComponentString(I18n.translateToLocal(String.format("%s%s", base, "digested"))
        			+ target.digested));
        	if (target.digesting > 0)
            	player.sendMessage(new TextComponentString(I18n.translateToLocal(String.format("%s%s", base, "digesting1"))
            			+ target.digesting + I18n.translateToLocal(String.format("%s%s", base, "digesting2"))));
        	if (target.isGolden()) 
            	player.sendMessage(new TextComponentString(I18n.translateToLocal(String.format("%s%s", base, "golden"))));
        	
            return true;
        }
        
        return false;
    }
    
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(I18n.canTranslate(this.getUnlocalizedName(stack) + ".tooltip")) {
			tooltip.addAll(LocUtils.getTooltips(TextFormatting.GRAY.toString() + LocUtils.translateRecursive(super.getUnlocalizedName(stack) + ".tooltip")));
		}
	}

}
