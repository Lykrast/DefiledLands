package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.common.item.*;
import lykrast.defiledlands.common.util.CreativeTabDL;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public static Item blastemFruit, defilementPowder, 
		hephaestite,
		scuttlerEye;
	private static final List<Item> itemList = new ArrayList<Item>();
	
	public static void init() {
		//Ores
		hephaestite = registerItem(new ItemHephaestite(), "hephaestite");
		
		//Plants
		blastemFruit = registerItem(new Item(), "blastem_fruit");
		defilementPowder = registerItem(new ItemDefilementPowder(), "defilement_powder");
		
		//Drops
		scuttlerEye = registerItem(new ItemScuttlerEye(), "scuttler_eye");
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		for (Item i : itemList) initModel(i);
	}

	@SideOnly(Side.CLIENT)
    public static void initModel(Item i) {
        ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
    }
	
	public static Item registerItem(Item item, String name)
	{
		return registerItem(item,name,CreativeTabDL.instance);
	}
	
	public static Item registerItem(Item item, String name, CreativeTabs tab)
	{
        item.setRegistryName(name);
		item.setUnlocalizedName(DefiledLands.MODID + "." + name);
        
		ForgeRegistries.ITEMS.register(item);
        
        if (tab != null) item.setCreativeTab(tab);
        
        itemList.add(item);
        
		return item;
	}

}
