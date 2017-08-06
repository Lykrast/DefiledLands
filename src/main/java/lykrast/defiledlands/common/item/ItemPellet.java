package lykrast.defiledlands.common.item;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.entity.projectile.EntityRavagerProjectile;
import lykrast.defiledlands.common.util.LocUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemPellet extends Item {
	private int damage;
	
	public ItemPellet(int damage)
	{
		super();
		this.damage = damage;
	}
	
	public EntityRavagerProjectile applyAttributes(EntityRavagerProjectile projectile)
	{
		projectile.setItem(new ItemStack(this));
		projectile.setDamage(damage);
		return projectile;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.GRAY.toString() + damage + " " + I18n.translateToLocal("ui.defiledlands.pellet.damage"));
		if(I18n.canTranslate(this.getUnlocalizedName(stack) + ".tooltip")) {
			tooltip.addAll(LocUtils.getTooltips(TextFormatting.GRAY.toString() + LocUtils.translateRecursive(super.getUnlocalizedName(stack) + ".tooltip")));
		}
	}

}
