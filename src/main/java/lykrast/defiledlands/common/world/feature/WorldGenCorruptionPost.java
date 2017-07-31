package lykrast.defiledlands.common.world.feature;

import java.util.Random;

import lykrast.defiledlands.common.util.CorruptionHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCorruptionPost extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		BlockPos pos = position;
		while (pos.getY() > 0)
		{
			CorruptionHelper.corrupt(worldIn, pos, worldIn.getBlockState(pos));
			pos = pos.down();
		}
		
		return true;
	}

}
