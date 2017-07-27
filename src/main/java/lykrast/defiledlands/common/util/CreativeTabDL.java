package lykrast.defiledlands.common.util;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabDL extends CreativeTabs {

	public static final CreativeTabs instance = new CreativeTabDL(CreativeTabs.getNextID(), "tabDefiledLands");

	public CreativeTabDL(int index, String label) {
		super(index, label);
	}
	
	@Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModBlocks.grassDefiled);
    }

}
