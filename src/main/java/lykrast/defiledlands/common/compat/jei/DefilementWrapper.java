package lykrast.defiledlands.common.compat.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class DefilementWrapper implements IRecipeWrapper {
	private ItemStack input, output;
	
	public DefilementWrapper(IBlockState in, IBlockState out)
	{
		input = getItemStack(in);
		output = getItemStack(out);
	}

	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInput(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	private ItemStack getItemStack(IBlockState state)
	{
		Block block = state.getBlock();
		return new ItemStack(block, 1, block.damageDropped(state));
	}

}
