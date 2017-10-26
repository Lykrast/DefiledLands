package lykrast.defiledlands.common.compat.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import lykrast.defiledlands.common.util.CorruptionRecipes;
import mezz.jei.api.IModRegistry;
import net.minecraft.block.state.IBlockState;

public class DefilementMaker {
	public static void registerRecipes(IModRegistry registry)
	{
		List<DefilementWrapper> list = new ArrayList<>();
		
		for (Entry<IBlockState,IBlockState> entry : CorruptionRecipes.getMap().entrySet())
		{
			list.add(new DefilementWrapper(entry.getKey(), entry.getValue()));
		}
		
		registry.addRecipes(list, DefilementCategory.UID);
	}
}
