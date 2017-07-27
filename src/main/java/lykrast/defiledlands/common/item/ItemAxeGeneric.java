package lykrast.defiledlands.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAxeGeneric extends ItemAxe {

	//Axes can't behave like normal tools... sigh
	public ItemAxeGeneric(ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
	}

}
