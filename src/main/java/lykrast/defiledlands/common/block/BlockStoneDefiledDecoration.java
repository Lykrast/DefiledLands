package lykrast.defiledlands.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStoneDefiledDecoration extends BlockVariantCorrupted {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", Variants.class);

	public BlockStoneDefiledDecoration(float hardness, float resistance, int harvestLevel) {
		super(Material.ROCK, SoundType.STONE, hardness, resistance, "pickaxe", harvestLevel, true);
		setDefaultState(blockState.getBaseState().withProperty(VARIANT, Variants.BRICKS));
	}

	@Override
	public Enum[] getVariants() {
		return Variants.values();
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (Enum v : getVariants()) 
			list.add(new ItemStack(this, 1, getMetaFromState(blockState.getBaseState().withProperty(VARIANT, v))));
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{VARIANT});
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Variants) state.getValue(VARIANT)).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, getVariants()[meta]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		for (Enum v : getVariants()) 
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), getMetaFromState(blockState.getBaseState().withProperty(VARIANT, v)), 
					new ModelResourceLocation(getRegistryName() + "_" + v, "inventory"));
	}
	
	public enum Variants implements IStringSerializable {
		
		BRICKS,
		BRICKS_CRACKED,
		BRICKS_MOSSY,
		MOSSY;
		
		@Override
	    public String getName()
	    {
	        return name().toLowerCase();
	    }
	    @Override
	    public String toString()
	    {
	        return getName();
	    }
	}

}
