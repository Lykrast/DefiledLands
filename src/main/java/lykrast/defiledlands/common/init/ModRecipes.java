package lykrast.defiledlands.common.init;

import lykrast.defiledlands.common.util.CorruptionRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {
	
	public static void init() {
		OreDictionary.registerOre("stoneDefiled", ModBlocks.stoneDefiled);
		OreDictionary.registerOre("dirtDefiled", ModBlocks.dirtDefiled);
		OreDictionary.registerOre("sandstoneDefiled", ModBlocks.sandstoneDefiled);
		OreDictionary.registerOre("treeWood", ModBlocks.tenebraLog);
		OreDictionary.registerOre("plankWood", ModBlocks.tenebraPlanks);
		
		CorruptionRecipes.init();
	}

}
