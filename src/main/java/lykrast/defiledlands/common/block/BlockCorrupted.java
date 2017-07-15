package lykrast.defiledlands.common.block;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.util.CorruptionRecipes;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCorrupted extends BlockGeneric {

	public BlockCorrupted() {
		super();
        setTickRandomly(true);
	}

	public BlockCorrupted(Material material, SoundType soundType) {
		super(material, soundType);
        setTickRandomly(true);
	}

	public BlockCorrupted(Material material, SoundType soundType, float hardness, float resistance) {
		super(material, soundType, hardness, resistance);
        setTickRandomly(true);
	}

	public BlockCorrupted(Material material, SoundType soundType, float hardness, float resistance, String tool, int harvestLevel) {
		super(material, soundType, hardness, resistance, tool, harvestLevel);
        setTickRandomly(true);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			for (int i = 0; i < 4; ++i)
			{
				BlockPos blockpos = pos.add(rand.nextInt(5) - 2, rand.nextInt(5) - 2, rand.nextInt(5) - 2);

				if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
				{
					return;
				}

				IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

				if (CorruptionRecipes.isCorruptable(iblockstate1.getBlock()))
				{
					worldIn.setBlockState(blockpos, CorruptionRecipes.getCorrupted(iblockstate1.getBlock()).getDefaultState());
				}
			}
		}
	}

}
