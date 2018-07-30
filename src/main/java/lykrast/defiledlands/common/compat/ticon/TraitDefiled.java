package lykrast.defiledlands.common.compat.ticon;

import java.util.List;

import com.google.common.collect.ImmutableList;

import lykrast.defiledlands.common.entity.IEntityDefiled;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitDefiled extends AbstractTrait {
	//Just like Hellish, but for non defiled mobs instead of non nether mobs
	private static final float DAMAGE = 4.0F;

	public TraitDefiled() {
		super("defiled", 0x8c4aa7);
	}
	
	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (target instanceof IEntityDefiled) return super.damage(tool, player, target, damage, newDamage, isCritical);
		else return super.damage(tool, player, target, damage, newDamage + DAMAGE, isCritical);
	}
	
	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
		String loc = String.format(LOC_Extra, getModifierIdentifier());
		return ImmutableList.of(Util.translateFormatted(loc, Util.df.format(DAMAGE)));
	}

}
