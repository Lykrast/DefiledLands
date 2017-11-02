package lykrast.defiledlands.common.compat;

import com.google.common.collect.ImmutableList;

import blusunrize.immersiveengineering.api.crafting.IngredientStack;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import lykrast.defiledlands.common.block.BlockVilespine;
import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.init.ModItems;
import net.minecraft.item.ItemStack;

public class CompatImmersiveEngineering extends ModCompat {
	// https://github.com/BluSunrize/ImmersiveEngineering/tree/master/src/main/java/blusunrize/immersiveengineering/api
	
	@Override
	public void postInit()
	{
		// Garden Cloche
		// Mostly copied from the vanilla registering
		// Uses same numbers as Sugar Cane and Mushrooms
		IngredientStack vilespineSoil = new IngredientStack(ImmutableList.of(
				new ItemStack(ModBlocks.dirtDefiled), 
				new ItemStack(ModBlocks.grassDefiled), 
				new ItemStack(ModBlocks.sandDefiled)));
		BelljarHandler.stackingHandler.register(new ItemStack(ModBlocks.vilespine), new ItemStack[]{new ItemStack(ModBlocks.vilespine,2)}, vilespineSoil, ModBlocks.vilespine.getDefaultState(), ModBlocks.vilespine.getDefaultState().withProperty(BlockVilespine.TOPMOST, true));
		BelljarHandler.cropHandler.register(new ItemStack(ModBlocks.scuronotte), new ItemStack[]{new ItemStack(ModBlocks.scuronotte,2)}, vilespineSoil, ModBlocks.scuronotte.getDefaultState());
		
		IngredientStack blastemSoil = new IngredientStack(ImmutableList.of(
				new ItemStack(ModBlocks.dirtDefiled), 
				new ItemStack(ModBlocks.grassDefiled)));
		BelljarHandler.cropHandler.register(new ItemStack(ModBlocks.blastem), new ItemStack[]{new ItemStack(ModItems.blastemFruit)}, blastemSoil, ModBlocks.blastem.getDefaultState());
	}
}
