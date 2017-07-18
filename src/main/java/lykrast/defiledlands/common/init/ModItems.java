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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public static Item hephaestite, scarlite,
		blastemFruit, blastemFruitBlazing, defilementPowder,
		scuttlerEye, blackHeart,
		axeTenebra, hoeTenebra, pickaxeTenebra, shovelTenebra, swordTenebra,
		swordScarlite;
	private static final List<Item> itemList = new ArrayList<Item>();
	
	public static ToolMaterial materialTenebra, materialScarlite;
	
	public static void init() {
		//Ores
		hephaestite = registerItem(new ItemHephaestite(), "hephaestite");
		scarlite = registerItem(new Item(), "scarlite");
		
		//Plants
		blastemFruit = registerItem(new ItemBlastemFruit(), "blastem_fruit");
		blastemFruitBlazing = registerItem(new ItemBlastemFruitBlazing(), "blastem_fruit_blazing");
		defilementPowder = registerItem(new ItemDefilementPowder(), "defilement_powder");
		
		//Drops
		scuttlerEye = registerItem(new ItemScuttlerEye(), "scuttler_eye");
		blackHeart = registerItem(new ItemBlackHeart(), "black_heart");
		
		//Tools
		materialTenebra = EnumHelper.addToolMaterial("tenebra", 0, 89, 3.0F, 1.0F, 22).setRepairItem(new ItemStack(ModBlocks.tenebraPlanks));
		axeTenebra = registerItem(new ItemAxeGeneric(materialTenebra), "tenebra_axe");
		hoeTenebra = registerItem(new ItemHoeGeneric(materialTenebra), "tenebra_hoe");
		pickaxeTenebra = registerItem(new ItemPickaxeGeneric(materialTenebra), "tenebra_pickaxe");
		shovelTenebra = registerItem(new ItemShovelGeneric(materialTenebra), "tenebra_shovel");
		swordTenebra = registerItem(new ItemSwordGeneric(materialTenebra), "tenebra_sword");
		
		materialScarlite = EnumHelper.addToolMaterial("scarlite", 3, 1561, 6.0F, 2.0F, 14).setRepairItem(new ItemStack(ModItems.scarlite));
		swordScarlite = registerItem(new ItemSwordScarlite(materialScarlite), "scarlite_reaver");
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
