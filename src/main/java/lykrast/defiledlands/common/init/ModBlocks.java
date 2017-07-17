package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.common.block.*;
import lykrast.defiledlands.common.util.CreativeTabDL;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	public static Block stoneDefiled, sandDefiled, sandstoneDefiled, dirtDefiled, grassDefiled, 
		tenebraLog, tenebraLeaves, tenebraPlanks, 
		hephaestiteOre, hephaestiteBlock, 
		vilespine, creepingMoss, blastem, scuronotte;
	private static final List<Block> blockList = new ArrayList<Block>();
	
	public static void init() {
		//Blocks
		stoneDefiled = registerBlock(new BlockCorrupted(Material.ROCK, SoundType.STONE, 1.5F, 30.0F, "pickaxe", 0), "stone_defiled");
		sandDefiled = registerBlock(new BlockFallingCorrupted(Material.SAND, SoundType.SAND, 0.5F, 2.5F, "shovel", 0), "sand_defiled");
		sandstoneDefiled = registerBlock(new BlockCorrupted(Material.ROCK, SoundType.STONE, 0.8F, 4.0F, "pickaxe", 0), "sandstone_defiled");
		dirtDefiled = registerBlock(new BlockCorrupted(Material.GROUND, SoundType.GROUND, 0.5F, 2.5F, "shovel", 0), "dirt_defiled");
		grassDefiled = registerBlock(new BlockGrassCorrupted(0.6F, 2.5F), "grass_defiled");
		tenebraLog = registerBlock(new BlockLogTenebra(3.0F, 15.0F), "tenebra_log");
		//tenebraLeaves = registerBlock(new BlockLeafTenebra(), "tenebra_leaves");
		tenebraPlanks = registerBlock(new BlockCorrupted(Material.WOOD, SoundType.WOOD, 3.0F, 22.5F, "axe", 0), "tenebra_planks");
		
		//Ores
		hephaestiteOre = registerBlock(new BlockOreHephaestite(3.0F, 15.0F), "hephaestite_ore");
		hephaestiteBlock = registerBlock(new BlockHephaestite(5.0F, 30.0F), "hephaestite_block");
		
		//Plants
		vilespine = registerBlock(new BlockVilespine(), "vilespine");
		creepingMoss = registerBlock(new BlockCreepingMoss(), "creeping_moss");
		blastem = registerBlock(new BlockBlastem(), "blastem");
		scuronotte = registerBlock(new BlockScuronotte(), "scuronotte");
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		for (Block b : blockList) initModel(b);
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModel(Block b) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(b.getRegistryName(), "inventory"));
    }
	
	public static Block registerBlock(Block block, String name)
	{
		return registerBlock(block,name,CreativeTabDL.instance);
	}

	public static Block registerBlock(Block block, String name, CreativeTabs tab)
	{
		block.setRegistryName(name);
		block.setUnlocalizedName(DefiledLands.MODID + "." + name);

		ForgeRegistries.BLOCKS.register(block);
		
		
		ItemBlock item;
		if (block instanceof ICustomItemBlock)
		{
			item = ((ICustomItemBlock) block).getItemBlock();
		}
		else
		{
			item = new ItemBlock(block);
		}
		item.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(item);

		if (tab != null) block.setCreativeTab(tab);
		
		blockList.add(block);

		return block;
	}

}
