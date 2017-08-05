package lykrast.defiledlands.common.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAxeFoiled extends ItemAxeGeneric {

	public ItemAxeFoiled(ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
	}

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

}
