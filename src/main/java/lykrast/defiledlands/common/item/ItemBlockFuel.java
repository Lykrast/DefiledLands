package lykrast.defiledlands.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFuel extends ItemBlock {
	protected int burnTime;

	public ItemBlockFuel(Block block, int burnTime){
		super(block);
		this.burnTime = burnTime;
	}
    
    @Override
    public int getItemBurnTime(ItemStack fuel)
    {
    	return burnTime;
    }

}
