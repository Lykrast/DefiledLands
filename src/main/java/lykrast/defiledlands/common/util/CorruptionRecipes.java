package lykrast.defiledlands.common.util;

import java.util.HashMap;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class CorruptionRecipes {

	private static HashMap<Block, Block> corruptionMap = new HashMap<Block, Block>(), 
			reverseMap = new HashMap<Block, Block>();

	public static void init() {
		register(Blocks.STONE, ModBlocks.stoneDefiled);
		register(Blocks.SAND, ModBlocks.sandDefiled);
		register(Blocks.SANDSTONE, ModBlocks.sandstoneDefiled);
		register(Blocks.DIRT, ModBlocks.dirtDefiled);
		register(Blocks.GRASS, ModBlocks.grassDefiled);
		register(Blocks.COAL_ORE, ModBlocks.hephaestiteOre);
		register(Blocks.COAL_BLOCK, ModBlocks.hephaestiteBlock);
		register(Blocks.DIAMOND_ORE, ModBlocks.scarliteOre);
		register(Blocks.DIAMOND_BLOCK, ModBlocks.scarliteBlock);
	}

	public static Block getCorrupted(Block input)
	{
		return corruptionMap.get(input);
	}

	public static Block getReverse(Block input)
	{
		return reverseMap.get(input);
	}

	public static void register(Block input, Block output)
	{		
		corruptionMap.put(input, output);
		//reverseMap.put(output, input);
	}

	public static void unregister(Block input)
	{		
		Block output = getCorrupted(input);

		unregister(input, output);
	}

	public static void unregister(Block input, Block output)
	{		
		corruptionMap.remove(input, output);
		reverseMap.remove(output, input);
	}

}
