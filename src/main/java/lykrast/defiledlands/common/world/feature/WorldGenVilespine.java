package lykrast.defiledlands.common.world.feature;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVilespine extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 10; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos))
            {
                int j = 1 + rand.nextInt(rand.nextInt(7) + 1);

                for (int k = 0; k < j; ++k)
                {
                    if (ModBlocks.vilespine.canPlaceBlockAt(worldIn, blockpos))
                    {
                        worldIn.setBlockState(blockpos.up(k), ModBlocks.vilespine.getDefaultState(), 2);
                    }
                }
            }
        }

        return true;
    }
}
