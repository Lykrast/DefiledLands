package lykrast.defiledlands.common.util;

import java.util.HashMap;
import java.util.Map;

import lykrast.defiledlands.common.block.BlockStoneDefiledDecoration;
import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class CorruptionRecipes {

	private static Map<IBlockState, IBlockState> corruptionMap = new HashMap<IBlockState, IBlockState>();

	public static void init() {
		register(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), ModBlocks.stoneDefiled);
		register(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), ModBlocks.stoneDefiled);
		register(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), ModBlocks.stoneDefiled);
		register(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), ModBlocks.stoneDefiled);
		register(Blocks.COBBLESTONE, ModBlocks.stoneDefiled);
		register(Blocks.SAND, ModBlocks.sandDefiled);
		register(Blocks.SANDSTONE, ModBlocks.sandstoneDefiled);
		register(Blocks.DIRT, ModBlocks.dirtDefiled);
		register(Blocks.GRASS, ModBlocks.grassDefiled);
		register(Blocks.GRAVEL, ModBlocks.gravelDefiled);
		register(Blocks.GLASS, ModBlocks.glassObscure);
		
		register(Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT), 
				ModBlocks.stoneDefiledDecoration.getDefaultState().withProperty(
				BlockStoneDefiledDecoration.VARIANT, BlockStoneDefiledDecoration.Variants.BRICKS));
		register(Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED), 
				ModBlocks.stoneDefiledDecoration.getDefaultState().withProperty(
				BlockStoneDefiledDecoration.VARIANT, BlockStoneDefiledDecoration.Variants.BRICKS_CRACKED));
		register(Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY), 
				ModBlocks.stoneDefiledDecoration.getDefaultState().withProperty(
				BlockStoneDefiledDecoration.VARIANT, BlockStoneDefiledDecoration.Variants.BRICKS_MOSSY));
		register(Blocks.MOSSY_COBBLESTONE, ModBlocks.stoneDefiledDecoration.getDefaultState().withProperty(
				BlockStoneDefiledDecoration.VARIANT, BlockStoneDefiledDecoration.Variants.MOSSY));
		
		register(Blocks.COAL_ORE, ModBlocks.hephaestiteOre);
		register(Blocks.COAL_BLOCK, ModBlocks.hephaestiteBlock);
		register(Blocks.IRON_ORE, ModBlocks.umbriumOre);
		register(Blocks.IRON_BLOCK, ModBlocks.umbriumBlock);
		register(Blocks.DIAMOND_ORE, ModBlocks.scarliteOre);
		register(Blocks.DIAMOND_BLOCK, ModBlocks.scarliteBlock);
	}

	public static IBlockState getCorrupted(IBlockState input)
	{
		return corruptionMap.get(input);
	}

	public static void register(IBlockState input, IBlockState output)
	{		
		corruptionMap.put(input, output);
	}

	public static void register(Block input, Block output)
	{		
		corruptionMap.put(input.getDefaultState(), output.getDefaultState());
	}

	public static void register(IBlockState input, Block output)
	{		
		corruptionMap.put(input, output.getDefaultState());
	}

	public static void register(Block input, IBlockState output)
	{		
		corruptionMap.put(input.getDefaultState(), output);
	}


	public static void unregister(IBlockState input)
	{		
		IBlockState output = getCorrupted(input);

		unregister(input, output);
	}

	public static void unregister(IBlockState input, IBlockState output)
	{		
		corruptionMap.remove(input, output);
	}
	
	public static Map<IBlockState, IBlockState> getMap()
	{
		return corruptionMap;
	}

}
