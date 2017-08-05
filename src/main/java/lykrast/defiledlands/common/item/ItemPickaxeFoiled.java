package lykrast.defiledlands.common.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPickaxeFoiled extends ItemPickaxeGeneric {

	public ItemPickaxeFoiled(ToolMaterial material) {
		super(material);
	}

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

}
