package lykrast.defiledlands.common.block;

import lykrast.defiledlands.common.item.ItemBlockMetadata;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockVariant extends BlockGeneric implements ICustomItemBlock {
	
	protected boolean variantNames = false;

	public BlockVariant(Material material, SoundType soundType, float hardness, float resistance) {
		super(material, soundType, hardness, resistance);
	}

	public BlockVariant(Material material, SoundType soundType, float hardness, float resistance, boolean names) {
		super(material, soundType, hardness, resistance);
		variantNames = names;
	}

	public BlockVariant(Material material, SoundType soundType, float hardness, float resistance, String tool,
			int harvestLevel) {
		super(material, soundType, hardness, resistance, tool, harvestLevel);
	}

	public BlockVariant(Material material, SoundType soundType, float hardness, float resistance, String tool,
			int harvestLevel, boolean names) {
		super(material, soundType, hardness, resistance, tool, harvestLevel);
		variantNames = names;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}
	
	public boolean hasVariantNames()
	{
		return variantNames;
	}
	
	@Override
	public ItemBlock getItemBlock()
	{
		return new ItemBlockMetadata(this, hasVariantNames());
	}
	
	// Those methods needs to be redefined using your variant properties, check BlockAnimiteOre for an example
	public abstract Enum[] getVariants();
	
	@Override
	public abstract void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list);

	@Override
	public abstract BlockStateContainer createBlockState();

	@Override
	public abstract int getMetaFromState(IBlockState state);

	@Override
	public abstract IBlockState getStateFromMeta(int meta);

	@SideOnly(Side.CLIENT)
	public abstract void initModel();

}