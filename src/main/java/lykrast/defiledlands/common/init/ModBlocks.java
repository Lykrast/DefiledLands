package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.common.block.BlockGeneric;
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
	
	public static Block stoneDefiled;
	private static final List<Block> blockList = new ArrayList<Block>();
	
	public static void init() {
		stoneDefiled = registerBlock(new BlockGeneric(Material.ROCK, SoundType.STONE, 1.5F, 30.0F, "pickaxe", 0), "stone_defiled");
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
		
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(item);

		if (tab != null) block.setCreativeTab(tab);
		
		blockList.add(block);

		return block;
	}

}
