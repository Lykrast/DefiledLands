package lykrast.defiledlands.common.compat;

import com.google.common.collect.ImmutableList;

import blusunrize.immersiveengineering.api.IEApi;
import blusunrize.immersiveengineering.api.crafting.ArcFurnaceRecipe;
import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import blusunrize.immersiveengineering.api.crafting.FermenterRecipe;
import blusunrize.immersiveengineering.api.crafting.IngredientStack;
import blusunrize.immersiveengineering.api.crafting.SqueezerRecipe;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import lykrast.defiledlands.common.block.BlockVilespine;
import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class CompatImmersiveEngineering extends ModCompat {
	// https://github.com/BluSunrize/ImmersiveEngineering/tree/master/src/main/java/blusunrize/immersiveengineering/api
	
	@Override
	public void init()
	{
		//Crusher
		CrusherRecipe.addRecipe(new ItemStack(ModBlocks.gravelDefiled), "stoneDefiled", 1600);
		CrusherRecipe.addRecipe(new ItemStack(ModBlocks.sandDefiled), "gravelDefiled", 1600);
		ItemStack saltpeter = IEApi.getPreferredOreStack("dustSaltpeter");
		CrusherRecipe.addRecipe(new ItemStack(ModBlocks.sandDefiled, 2), "sandstoneDefiled", 1600)
			.addToSecondaryOutput(saltpeter, 0.5F);
		
		CrusherRecipe.removeRecipesForInput(new ItemStack(ModBlocks.hephaestiteOre));
		ItemStack sulfur = IEApi.getPreferredOreStack("dustSulfur");
		CrusherRecipe.addRecipe(new ItemStack(ModItems.hephaestite, 4), "oreHephaestite", 1600)
			.addToSecondaryOutput(sulfur, 0.5F);
		
		// Fermenter
		Fluid ethanol = FluidRegistry.getFluid("ethanol");
		if (ethanol != null && saltpeter != null)
			FermenterRecipe.addRecipe(new FluidStack(ethanol, 80), saltpeter, ModItems.blastemFruit, 6400);
		
		//Squeezer
		Fluid plantoil = FluidRegistry.getFluid("plantoil");
		SqueezerRecipe.addRecipe(new FluidStack(plantoil, 20), ItemStack.EMPTY, ModItems.blastemSeed, 6400);
		
		//Arc Furnace Recycling
		ArcFurnaceRecipe.allowItemForRecycling(ModBlocks.healingPad);
		ArcFurnaceRecipe.allowItemForRecycling(ModItems.umbraBlaster);
		ArcFurnaceRecipe.allowItemForRecycling(ModItems.concussionSmasher);
		ArcFurnaceRecipe.allowItemForRecycling(ModItems.bookWyrmAnalyzer);
		ArcFurnaceRecipe.allowItemForRecycling(ModItems.theRavager);
		ArcFurnaceRecipe.allowItemForRecycling(ModItems.pelletUmbrium);
		ArcFurnaceRecipe.allowItemForRecycling(ModItems.pelletSpiked);
	}
	
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
		BelljarHandler.cropHandler.register(new ItemStack(ModItems.blastemSeed), new ItemStack[]{new ItemStack(ModItems.blastemFruit)}, blastemSoil, ModBlocks.blastem.getDefaultState());
	}
}
