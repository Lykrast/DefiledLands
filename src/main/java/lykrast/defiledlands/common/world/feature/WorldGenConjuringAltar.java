package lykrast.defiledlands.common.world.feature;

import java.util.Random;

import lykrast.defiledlands.common.block.BlockStoneDefiledDecoration;
import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenConjuringAltar extends WorldGenerator {
    private final IBlockState bricks = ModBlocks.stoneDefiledDecoration.getDefaultState().withProperty(BlockStoneDefiledDecoration.VARIANT, BlockStoneDefiledDecoration.Variants.BRICKS),
    		cracked = ModBlocks.stoneDefiledDecoration.getDefaultState().withProperty(BlockStoneDefiledDecoration.VARIANT, BlockStoneDefiledDecoration.Variants.BRICKS_CRACKED),
    		sandstone = ModBlocks.sandstoneDefiled.getDefaultState(),
    		altar = ModBlocks.conjuringAltar.getDefaultState();

	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		//Mostly copied from Desert Wells
		//Double checking for biome, to be sure the altar is active
		if (!(worldIn.getBiome(position) instanceof lykrast.defiledlands.common.world.biome.BiomeDefiled)) return false;
		
		//Finding the ground
		while (worldIn.isAirBlock(position) && position.getY() > 2)
		{
			position = position.down();
		}

		//Checking the ground
		for (int i = -2; i <= 2; ++i)
		{
			for (int j = -2; j <= 2; ++j)
			{
				if (worldIn.isAirBlock(position.add(i, -1, j)) && worldIn.isAirBlock(position.add(i, -2, j)))
				{
					return false;
				}
			}
		}

		//Making the ground
		for (int l1 = -2; l1 <= 2; ++l1)
		{
			for (int k = -2; k <= 2; ++k)
			{
				worldIn.setBlockState(position.add(l1, -1, k), getRandomBrick(rand), 2);
				if (l1 <= 1 && l1 >= -1 && k <= 1 && k >= -1) worldIn.setBlockState(position.add(l1, 0, k), sandstone, 2);
				else worldIn.setBlockState(position.add(l1, 0, k), getRandomBrick(rand), 2);
			}
		}
		
		//Pillars
		for (int i = 1; i <= 3; i++)
		{
			for (int l1 = -2; l1 <= 2; ++l1)
			{
				for (int k = -2; k <= 2; ++k)
				{
					if ((l1 == -2 || l1 == 2) && (k == -2 || k == 2)) worldIn.setBlockState(position.add(l1, i, k), getRandomBrick(rand), 2);
					else worldIn.setBlockToAir(position.add(l1, i, k));
				}
			}
		}
		
		worldIn.setBlockState(position.add(0, 1, 0), altar, 2);

		return true;
	}
	
	private IBlockState getRandomBrick(Random rand)
	{
		if (rand.nextInt(4) == 0) return cracked;
		else return bricks;
	}

}
