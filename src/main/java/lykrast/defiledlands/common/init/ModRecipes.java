package lykrast.defiledlands.common.init;

import lykrast.defiledlands.common.util.CorruptionRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {
	
	public static void init() {
		OreDictionary.registerOre("stoneDefiled", ModBlocks.stoneDefiled);
		OreDictionary.registerOre("stoneDefiled", ModBlocks.stoneDefiledDecoration);
		OreDictionary.registerOre("dirtDefiled", ModBlocks.dirtDefiled);
		OreDictionary.registerOre("sandstoneDefiled", ModBlocks.sandstoneDefiled);
		OreDictionary.registerOre("treeWood", ModBlocks.tenebraLog);
		OreDictionary.registerOre("plankWood", ModBlocks.tenebraPlanks);
		OreDictionary.registerOre("treeLeaves", ModBlocks.tenebraLeaves);
		OreDictionary.registerOre("treeSapling", ModBlocks.tenebraSapling);
		
		OreDictionary.registerOre("oreHephaestite", ModBlocks.hephaestiteOre);
		OreDictionary.registerOre("blockHephaestite", ModBlocks.hephaestiteBlock);
		OreDictionary.registerOre("oreUmbrium", ModBlocks.umbriumOre);
		OreDictionary.registerOre("blockUmbrium", ModBlocks.umbriumBlock);
		OreDictionary.registerOre("oreScarlite", ModBlocks.scarliteOre);
		OreDictionary.registerOre("blockScarlite", ModBlocks.scarliteBlock);
		
		OreDictionary.registerOre("gemHephaestite", ModItems.hephaestite);
		OreDictionary.registerOre("ingotUmbrium", ModItems.umbriumIngot);
		OreDictionary.registerOre("nuggetUmbrium", ModItems.umbriumNugget);
		OreDictionary.registerOre("gemScarlite", ModItems.scarlite);
		
		CorruptionRecipes.init();
		
		GameRegistry.addSmelting(ModBlocks.hephaestiteOre, new ItemStack(ModItems.hephaestite, 2), 0.1F);
		GameRegistry.addSmelting(ModBlocks.umbriumOre, new ItemStack(ModItems.umbriumIngot), 0.7F);
		GameRegistry.addSmelting(ModBlocks.scarliteOre, new ItemStack(ModItems.scarlite), 1);
		
		GameRegistry.addSmelting(ModItems.axeUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.hoeUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.pickaxeUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.shovelUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.swordUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
	}

}
