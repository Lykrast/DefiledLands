package lykrast.defiledlands.common.item;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.util.LocUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemShovelGeneric extends ItemSpade {

	public ItemShovelGeneric(ToolMaterial material) {
		super(material);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(I18n.canTranslate(this.getUnlocalizedName(stack) + ".tooltip")) {
			tooltip.addAll(LocUtils.getTooltips(TextFormatting.GRAY.toString() + LocUtils.translateRecursive(super.getUnlocalizedName(stack) + ".tooltip")));
		}
	}

}
