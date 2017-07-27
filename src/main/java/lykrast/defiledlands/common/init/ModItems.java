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
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public static Item tenebraDoor,
		hephaestite, umbriumIngot, umbriumNugget, scarlite,
		blastemFruit, blastemFruitBlazing, defilementPowder,
		scuttlerEye, blackHeart, foulSlime, foulCandy,
		axeTenebra, hoeTenebra, pickaxeTenebra, shovelTenebra, swordTenebra,
		axeUmbrium, hoeUmbrium, pickaxeUmbrium, shovelUmbrium, swordUmbrium,
		umbraBlaster,
		swordScarlite;
	private static final List<Item> itemList = new ArrayList<Item>();
	
	public static ToolMaterial materialTenebra, materialUmbrium, materialScarlite;
	
	public static void init() {
		//Decoration
		tenebraDoor = registerItem(new ItemDoor(ModBlocks.tenebraDoor), "tenebra_door");
		
		//Ores
		hephaestite = registerItem(new ItemHephaestite(), "hephaestite");
		umbriumIngot = registerItem(new Item(), "umbrium_ingot");
		umbriumNugget = registerItem(new Item(), "umbrium_nugget");
		scarlite = registerItem(new Item(), "scarlite");
		
		//Plants
		blastemFruit = registerItem(new ItemBlastemFruit(), "blastem_fruit");
		blastemFruitBlazing = registerItem(new ItemBlastemFruitBlazing(), "blastem_fruit_blazing");
		defilementPowder = registerItem(new ItemDefilementPowder(), "defilement_powder");
		
		//Drops
		scuttlerEye = registerItem(new ItemScuttlerEye(), "scuttler_eye");
		blackHeart = registerItem(new ItemBlackHeart(), "black_heart");
		foulSlime = registerItem(new Item(), "foul_slime");
		foulCandy = registerItem(new ItemFoulCandy(), "foul_candy");
		
		//Tools
		//Because axes' stats aren't calculated like normal tools, we have to manually specify them
		materialTenebra = EnumHelper.addToolMaterial("tenebra", 0, 89, 3.0F, 1.0F, 18).setRepairItem(new ItemStack(ModBlocks.tenebraPlanks));
		axeTenebra = registerItem(new ItemAxeGeneric(materialTenebra, 8.0F, -3.2F), "tenebra_axe");
		hoeTenebra = registerItem(new ItemHoeGeneric(materialTenebra), "tenebra_hoe");
		pickaxeTenebra = registerItem(new ItemPickaxeGeneric(materialTenebra), "tenebra_pickaxe");
		shovelTenebra = registerItem(new ItemShovelGeneric(materialTenebra), "tenebra_shovel");
		swordTenebra = registerItem(new ItemSwordGeneric(materialTenebra), "tenebra_sword");
		
		materialUmbrium = EnumHelper.addToolMaterial("umbrium", 2, 302, 5.0F, 2.0F, 20).setRepairItem(new ItemStack(umbriumIngot));
		axeUmbrium = registerItem(new ItemAxeGeneric(materialUmbrium, 8.0F, -3.1F), "umbrium_axe");
		hoeUmbrium = registerItem(new ItemHoeGeneric(materialUmbrium), "umbrium_hoe");
		pickaxeUmbrium = registerItem(new ItemPickaxeGeneric(materialUmbrium), "umbrium_pickaxe");
		shovelUmbrium = registerItem(new ItemShovelGeneric(materialUmbrium), "umbrium_shovel");
		swordUmbrium = registerItem(new ItemSwordGeneric(materialUmbrium), "umbrium_sword");
		
		umbraBlaster = registerItem(new ItemUmbraBlaster(465), "umbra_blaster");
		
		materialScarlite = EnumHelper.addToolMaterial("scarlite", 3, 1561, 6.0F, 2.0F, 14).setRepairItem(new ItemStack(scarlite));
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
