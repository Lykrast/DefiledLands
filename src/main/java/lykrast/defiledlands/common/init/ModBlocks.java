package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.common.block.BlockBlastem;
import lykrast.defiledlands.common.block.BlockConjuringAltar;
import lykrast.defiledlands.common.block.BlockCorrupted;
import lykrast.defiledlands.common.block.BlockDoorGeneric;
import lykrast.defiledlands.common.block.BlockDoubleSlabCorruptedStone;
import lykrast.defiledlands.common.block.BlockDoubleSlabCorruptedWood;
import lykrast.defiledlands.common.block.BlockFallingCorrupted;
import lykrast.defiledlands.common.block.BlockGrassCorrupted;
import lykrast.defiledlands.common.block.BlockHalfSlabCorruptedStone;
import lykrast.defiledlands.common.block.BlockHalfSlabCorruptedWood;
import lykrast.defiledlands.common.block.BlockHealingPad;
import lykrast.defiledlands.common.block.BlockHephaestite;
import lykrast.defiledlands.common.block.BlockHephaestiteOre;
import lykrast.defiledlands.common.block.BlockLeafTenebra;
import lykrast.defiledlands.common.block.BlockLogTenebra;
import lykrast.defiledlands.common.block.BlockSaplingTenebra;
import lykrast.defiledlands.common.block.BlockScarliteOre;
import lykrast.defiledlands.common.block.BlockScuronotte;
import lykrast.defiledlands.common.block.BlockStairsGeneric;
import lykrast.defiledlands.common.block.BlockStoneDefiledDecoration;
import lykrast.defiledlands.common.block.BlockVilespine;
import lykrast.defiledlands.common.block.ICustomItemBlock;
import lykrast.defiledlands.common.block.ICustomModel;
import lykrast.defiledlands.common.tileentity.TileConjuringAltar;
import lykrast.defiledlands.common.util.CreativeTabDL;
import lykrast.defiledlands.common.util.LocUtils;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	public static Block stoneDefiled, sandDefiled, sandstoneDefiled, dirtDefiled, grassDefiled,
		tenebraLog, tenebraLeaves, tenebraSapling, tenebraPlanks, 
		stoneDefiledDecoration, tenebraDoor,
		slabDoubleStone, slabHalfStone, slabDoubleTenebra, slabHalfTenebra,
		stairsStoneDefiled, stairsSandstoneDefiled, stairsStoneDefiledBricks, stairsTenebra, 
		hephaestiteOre, hephaestiteBlock, umbriumOre, umbriumBlock, scarliteOre, scarliteBlock, 
		vilespine, blastem, scuronotte,
		healingPad, conjuringAltar;
	private static final List<Block> blockList = new ArrayList<Block>();
	
	public static void init() {
		//Blocks
		stoneDefiled = registerBlock(new BlockCorrupted(Material.ROCK, SoundType.STONE, 1.5F, 30.0F, "pickaxe", 0), "stone_defiled");
		sandDefiled = registerBlock(new BlockFallingCorrupted(Material.SAND, SoundType.SAND, 0.5F, 2.5F, "shovel", 0), "sand_defiled");
		sandstoneDefiled = registerBlock(new BlockCorrupted(Material.ROCK, SoundType.STONE, 0.8F, 4.0F, "pickaxe", 0), "sandstone_defiled");
		dirtDefiled = registerBlock(new BlockCorrupted(Material.GROUND, SoundType.GROUND, 0.5F, 2.5F, "shovel", 0), "dirt_defiled");
		grassDefiled = registerBlock(new BlockGrassCorrupted(0.6F, 2.5F), "grass_defiled");
		tenebraLog = registerBlock(new BlockLogTenebra(3.0F, 15.0F), "tenebra_log");
		tenebraLeaves = registerBlock(new BlockLeafTenebra(), "tenebra_leaves");
		tenebraSapling = registerBlock(new BlockSaplingTenebra(0.0F), "tenebra_sapling");
		tenebraPlanks = registerBlock(new BlockCorrupted(Material.WOOD, SoundType.WOOD, 3.0F, 22.5F, "axe", 0), "tenebra_planks");
		
		//Decoration
		stoneDefiledDecoration = registerBlock(new BlockStoneDefiledDecoration(1.5F, 30.0F, 0), "stone_defiled_decoration");
		tenebraDoor = registerBlock(new BlockDoorGeneric(Material.WOOD, SoundType.WOOD, 3.0F, 22.5F, "axe", 0), "tenebra_door");
		slabDoubleStone = registerBlock(new BlockDoubleSlabCorruptedStone(), "stone_defiled_slab_double");
		slabHalfStone = registerBlock(new BlockHalfSlabCorruptedStone(), "stone_defiled_slab");
		slabDoubleTenebra = registerBlock(new BlockDoubleSlabCorruptedWood(), "wood_defiled_slab_double");
		slabHalfTenebra = registerBlock(new BlockHalfSlabCorruptedWood(), "wood_defiled_slab");
		stairsStoneDefiled = registerBlock(new BlockStairsGeneric(stoneDefiled.getDefaultState()), "stone_defiled_stairs");
		stairsSandstoneDefiled = registerBlock(new BlockStairsGeneric(sandstoneDefiled.getDefaultState()), "sandstone_defiled_stairs");
		stairsStoneDefiledBricks = registerBlock(new BlockStairsGeneric(stoneDefiledDecoration.getDefaultState().withProperty(BlockStoneDefiledDecoration.VARIANT, 
				BlockStoneDefiledDecoration.Variants.BRICKS)), "stone_defiled_bricks_stairs");
		stairsTenebra = registerBlock(new BlockStairsGeneric(tenebraPlanks.getDefaultState()), "tenebra_stairs");
		
		//Ores
		hephaestiteOre = registerBlock(new BlockHephaestiteOre(3.0F, 15.0F), "hephaestite_ore");
		hephaestiteBlock = registerBlock(new BlockHephaestite(5.0F, 30.0F), "hephaestite_block");
		umbriumOre = registerBlock(new BlockCorrupted(Material.ROCK, SoundType.STONE, 3.0F, 15.0F, "pickaxe", 1), "umbrium_ore");
		umbriumBlock = registerBlock(new BlockCorrupted(Material.IRON, SoundType.METAL, 5.0F, 30.0F, "pickaxe", 1), "umbrium_block");
		scarliteOre = registerBlock(new BlockScarliteOre(3.0F, 15.0F), "scarlite_ore");
		scarliteBlock = registerBlock(new BlockCorrupted(Material.IRON, SoundType.METAL, 5.0F, 30.0F, "pickaxe", 2), "scarlite_block");
		
		//Plants
		vilespine = registerBlock(new BlockVilespine(), "vilespine");
		blastem = registerBlock(new BlockBlastem(), "blastem");
		scuronotte = registerBlock(new BlockScuronotte(), "scuronotte");
		
		//Useful stuff
		healingPad = registerBlock(new BlockHealingPad(3.0F, 15.0F), "healing_pad");
		conjuringAltar = registerBlock(new BlockConjuringAltar(3.0F, 15.0F), "conjuring_altar");
		GameRegistry.registerTileEntity(TileConjuringAltar.class, LocUtils.prefix("conjuring_altar"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		for (Block b : blockList) initModel(b);
		ModelLoader.setCustomStateMapper(tenebraDoor, (new StateMap.Builder()).ignore(BlockDoorGeneric.POWERED).build());
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModel(Block b) {
		if (b instanceof ICustomModel) ((ICustomModel) b).initModel();
		else ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(b.getRegistryName(), "inventory"));
    }
	
	public static Block registerBlock(Block block, String name)
	{
		return registerBlock(block,name,CreativeTabDL.instance);
	}

	public static Block registerBlock(Block block, String name, CreativeTabs tab)
	{
		block.setRegistryName(name);
		block.setUnlocalizedName(LocUtils.prefix(name));

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
		
		if (item != null)
		{
			item.setRegistryName(block.getRegistryName());
			ForgeRegistries.ITEMS.register(item);
		}

		if (tab != null) block.setCreativeTab(tab);
		
		blockList.add(block);

		return block;
	}

}
