package lykrast.defiledlands.common.init;

import lykrast.defiledlands.common.util.CorruptionRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {
	
	public static void init() {
		OreDictionary.registerOre("stoneDefiled", ModBlocks.stoneDefiled);
		OreDictionary.registerOre("dirtDefiled", ModBlocks.dirtDefiled);
		OreDictionary.registerOre("sandstoneDefiled", ModBlocks.sandstoneDefiled);
		OreDictionary.registerOre("treeWood", ModBlocks.tenebraLog);
		OreDictionary.registerOre("plankWood", ModBlocks.tenebraPlanks);
		OreDictionary.registerOre("oreHephaestite", ModBlocks.hephaestiteOre);
		OreDictionary.registerOre("blockHephaestite", ModBlocks.hephaestiteBlock);
		OreDictionary.registerOre("oreScarlite", ModBlocks.scarliteOre);
		OreDictionary.registerOre("blockScarlite", ModBlocks.scarliteBlock);
		
		OreDictionary.registerOre("gemHephaestite", ModItems.hephaestite);
		OreDictionary.registerOre("gemScarlite", ModItems.scarlite);
		
		CorruptionRecipes.init();
		
		GameRegistry.addSmelting(ModBlocks.hephaestiteOre, new ItemStack(ModItems.hephaestite, 2), 1);
		GameRegistry.addSmelting(ModBlocks.scarliteOre, new ItemStack(ModItems.scarlite), 1);
	}

}
