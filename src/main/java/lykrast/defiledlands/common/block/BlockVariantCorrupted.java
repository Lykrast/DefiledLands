package lykrast.defiledlands.common.block;

import java.util.Random;

import lykrast.defiledlands.common.util.CorruptionHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockVariantCorrupted extends BlockVariant {

	public BlockVariantCorrupted(Material material, SoundType soundType, float hardness, float resistance) {
		super(material, soundType, hardness, resistance);
        setTickRandomly(true);
	}

	public BlockVariantCorrupted(Material material, SoundType soundType, float hardness, float resistance, boolean names) {
		super(material, soundType, hardness, resistance);
		variantNames = names;
        setTickRandomly(true);
	}

	public BlockVariantCorrupted(Material material, SoundType soundType, float hardness, float resistance, String tool,
			int harvestLevel) {
		super(material, soundType, hardness, resistance, tool, harvestLevel);
        setTickRandomly(true);
	}

	public BlockVariantCorrupted(Material material, SoundType soundType, float hardness, float resistance, String tool,
			int harvestLevel, boolean names) {
		super(material, soundType, hardness, resistance, tool, harvestLevel);
		variantNames = names;
        setTickRandomly(true);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		CorruptionHelper.spread(worldIn, pos, state, rand);
		super.updateTick(worldIn, pos, state, rand);
	}

}
