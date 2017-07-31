package lykrast.defiledlands.common.init;

import lykrast.defiledlands.common.block.BlockStoneDefiledDecoration;
import lykrast.defiledlands.common.util.CorruptionRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {
	
	public static void init() {
		//Blocks
		OreDictionary.registerOre("stoneDefiled", ModBlocks.stoneDefiled);
		OreDictionary.registerOre("stoneDefiled", new ItemStack(ModBlocks.stoneDefiledDecoration, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("dirtDefiled", ModBlocks.dirtDefiled);
		OreDictionary.registerOre("sandDefiled", ModBlocks.sandDefiled);
		OreDictionary.registerOre("sandstoneDefiled", ModBlocks.sandstoneDefiled);
		OreDictionary.registerOre("treeWood", ModBlocks.tenebraLog);
		OreDictionary.registerOre("plankWood", ModBlocks.tenebraPlanks);
		OreDictionary.registerOre("treeLeaves", ModBlocks.tenebraLeaves);
		OreDictionary.registerOre("treeSapling", ModBlocks.tenebraSapling);
		
		//Ore blocks
		OreDictionary.registerOre("oreHephaestite", ModBlocks.hephaestiteOre);
		OreDictionary.registerOre("blockHephaestite", ModBlocks.hephaestiteBlock);
		OreDictionary.registerOre("oreUmbrium", ModBlocks.umbriumOre);
		OreDictionary.registerOre("blockUmbrium", ModBlocks.umbriumBlock);
		OreDictionary.registerOre("oreScarlite", ModBlocks.scarliteOre);
		OreDictionary.registerOre("blockScarlite", ModBlocks.scarliteBlock);
		
		//Ore items
		OreDictionary.registerOre("gemHephaestite", ModItems.hephaestite);
		OreDictionary.registerOre("ingotUmbrium", ModItems.umbriumIngot);
		OreDictionary.registerOre("nuggetUmbrium", ModItems.umbriumNugget);
		OreDictionary.registerOre("gemScarlite", ModItems.scarlite);
		
		//Boss essence
		OreDictionary.registerOre("essenceDestroyer", ModItems.essenceDestroyer);
		
		CorruptionRecipes.init();
		
		//Ores
		GameRegistry.addSmelting(ModBlocks.hephaestiteOre, new ItemStack(ModItems.hephaestite, 2), 0.1F);
		GameRegistry.addSmelting(ModBlocks.umbriumOre, new ItemStack(ModItems.umbriumIngot), 0.7F);
		GameRegistry.addSmelting(ModBlocks.scarliteOre, new ItemStack(ModItems.scarlite), 1);
		
		//Food
		GameRegistry.addSmelting(ModItems.bookWyrmRaw, new ItemStack(ModItems.bookWyrmCooked), 0.35F);
		
		//Golden Book Wyrm Scales
		GameRegistry.addSmelting(ModItems.bookWyrmScaleGolden, new ItemStack(Items.GOLD_NUGGET, 3), 0.7F);
		GameRegistry.addSmelting(ModItems.scaleGoldenHelmet, new ItemStack(Items.GOLD_NUGGET, 15), 0.7F);
		GameRegistry.addSmelting(ModItems.scaleGoldenChestplate, new ItemStack(Items.GOLD_NUGGET, 24), 0.7F);
		GameRegistry.addSmelting(ModItems.scaleGoldenLeggings, new ItemStack(Items.GOLD_NUGGET, 21), 0.7F);
		GameRegistry.addSmelting(ModItems.scaleGoldenBoots, new ItemStack(Items.GOLD_NUGGET, 12), 0.7F);
		
		//Tool recycling
		GameRegistry.addSmelting(ModItems.axeUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.hoeUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.pickaxeUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.shovelUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.swordUmbrium, new ItemStack(ModItems.umbriumNugget), 0.1F);
		//Armor recycling
		GameRegistry.addSmelting(ModItems.umbriumHelmet, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.umbriumChestplate, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.umbriumLeggings, new ItemStack(ModItems.umbriumNugget), 0.1F);
		GameRegistry.addSmelting(ModItems.umbriumBoots, new ItemStack(ModItems.umbriumNugget), 0.1F);
		
		//Decoration
		GameRegistry.addSmelting(new ItemStack(ModBlocks.stoneDefiledDecoration, 1, BlockStoneDefiledDecoration.Variants.BRICKS.ordinal()), 
				new ItemStack(ModBlocks.stoneDefiledDecoration, 1, BlockStoneDefiledDecoration.Variants.BRICKS_CRACKED.ordinal()), 0.1F);
	}

}
