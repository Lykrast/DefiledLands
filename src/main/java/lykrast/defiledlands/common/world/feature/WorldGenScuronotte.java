package lykrast.defiledlands.common.world.feature;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenScuronotte extends WorldGenerator {

	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 8; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < worldIn.getHeight() - 1) && ((BlockBush) ModBlocks.scuronotte).canBlockStay(worldIn, blockpos, ModBlocks.scuronotte.getDefaultState()))
            {
                worldIn.setBlockState(blockpos, ModBlocks.scuronotte.getDefaultState(), 2);
            }
        }

        return true;
    }
}
