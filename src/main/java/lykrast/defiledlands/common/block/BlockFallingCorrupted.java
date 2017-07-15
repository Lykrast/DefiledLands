package lykrast.defiledlands.common.block;

import java.util.Random;

import lykrast.defiledlands.common.util.CorruptionRecipes;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFallingCorrupted extends BlockFalling {

	public BlockFallingCorrupted() {
        this(Material.ROCK, SoundType.STONE);
    }
	
	public BlockFallingCorrupted(Material material, SoundType soundType) {
		this(Material.ROCK, SoundType.STONE, 1.0F, 5.0F);
	}
	
	public BlockFallingCorrupted(Material material, SoundType soundType, float hardness, float resistance) {
		super(material);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
	}
	
	public BlockFallingCorrupted(Material material, SoundType soundType, float hardness, float resistance, String tool, int harvestLevel) {
		this(material, soundType, hardness, resistance);
		this.setHarvestLevel(tool, harvestLevel);
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
		super.updateTick(worldIn, pos, state, rand);
	}

}
