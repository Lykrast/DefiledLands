package lykrast.defiledlands.common.item;

import lykrast.defiledlands.common.block.BlockVariant;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMetadata extends ItemBlock {
	
	private Enum[] variants;
	private boolean differentNames = false;

	public ItemBlockMetadata(Block block)
	{
		this(block, false);
	}
	
	/**
	 * Allows the block to have different names for each variant.
	 * This only works if the block extends BlockVariant.
	 * @param block
	 * @param should each variant have a different name?
	 */
	public ItemBlockMetadata(Block block, boolean names)
	{
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
		if (names && block instanceof BlockVariant)
		{
			differentNames = true;
			variants = ((BlockVariant) block).getVariants();
		}
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		if (differentNames) return super.getUnlocalizedName(itemStack) + "." + variants[itemStack.getMetadata()];
		else return super.getUnlocalizedName(itemStack);
	}

}
