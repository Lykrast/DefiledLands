package lykrast.defiledlands.common.item;

import net.minecraft.item.ItemAxe;

public class ItemAxeGeneric extends ItemAxe {

	//Axes can't behave like normal tools... sigh
	public ItemAxeGeneric(ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
	}

}
