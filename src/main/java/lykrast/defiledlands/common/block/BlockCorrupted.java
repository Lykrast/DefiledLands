package lykrast.defiledlands.common.block;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.util.CorruptionHelper;
import lykrast.defiledlands.common.util.CorruptionRecipes;
import lykrast.defiledlands.common.world.biome.BiomeDefiled;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

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
		CorruptionHelper.spread(worldIn, pos, state, rand);
		super.updateTick(worldIn, pos, state, rand);
	}

}
