package lykrast.defiledlands.common.util;

import java.util.HashMap;

import lykrast.defiledlands.common.block.BlockStoneDefiledDecoration;
import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class CorruptionRecipes {

	private static HashMap<Block, IBlockState> corruptionMap = new HashMap<Block, IBlockState>(), 
			reverseMap = new HashMap<Block, IBlockState>();

	public static void init() {
		register(Blocks.STONE, ModBlocks.stoneDefiled);
		register(Blocks.SAND, ModBlocks.sandDefiled);
		register(Blocks.SANDSTONE, ModBlocks.sandstoneDefiled);
		register(Blocks.DIRT, ModBlocks.dirtDefiled);
		register(Blocks.GRASS, ModBlocks.grassDefiled);
		
		register(Blocks.STONEBRICK, ModBlocks.stoneDefiledDecoration.getDefaultState().withProperty(
				BlockStoneDefiledDecoration.VARIANT, BlockStoneDefiledDecoration.Variants.BRICKS));
		
		register(Blocks.COAL_ORE, ModBlocks.hephaestiteOre);
		register(Blocks.COAL_BLOCK, ModBlocks.hephaestiteBlock);
		register(Blocks.IRON_ORE, ModBlocks.umbriumOre);
		register(Blocks.IRON_BLOCK, ModBlocks.umbriumBlock);
		register(Blocks.DIAMOND_ORE, ModBlocks.scarliteOre);
		register(Blocks.DIAMOND_BLOCK, ModBlocks.scarliteBlock);
	}

	public static IBlockState getCorrupted(Block input)
	{
		return corruptionMap.get(input);
	}

	public static IBlockState getReverse(Block input)
	{
		return reverseMap.get(input);
	}

	public static void register(Block input, IBlockState output)
	{		
		corruptionMap.put(input, output);
		//reverseMap.put(output, input);
	}

	public static void register(Block input, Block output)
	{		
		corruptionMap.put(input, output.getDefaultState());
	}

	public static void unregister(Block input)
	{		
		IBlockState output = getCorrupted(input);

		unregister(input, output);
	}

	public static void unregister(Block input, IBlockState output)
	{		
		corruptionMap.remove(input, output);
		reverseMap.remove(output, input);
	}

}
