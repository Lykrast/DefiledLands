package lykrast.defiledlands.common.util;

import java.util.Random;

import lykrast.defiledlands.common.world.biome.BiomeDefiled;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class CorruptionHelper {
	
	public static void spread(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			if (canSpread(worldIn, pos, rand))
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
	
	public static boolean canSpread(World worldIn, BlockPos pos, Random rand)
	{
		Biome biome = worldIn.getBiome(pos);
		
		if (biome instanceof BiomeDefiled || !Config.confinedSpread)
		{
			return true;
		}
		
		return false;
	}

}
