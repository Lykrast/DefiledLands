package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.common.entity.boss.EntityDestroyer;
import lykrast.defiledlands.common.item.*;
import lykrast.defiledlands.common.util.CreativeTabDL;
import lykrast.defiledlands.common.util.LocUtils;
import lykrast.defiledlands.common.init.ModRecipes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ModItems {
	
	public static Item tenebraDoor,
		hephaestite, umbriumIngot, umbriumNugget, scarlite,
		blastemFruit, blastemFruitBlazing, defilementPowder,
		scuttlerEye, blackHeart, foulSlime, foulCandy, bookWyrmRaw, bookWyrmCooked, bookWyrmScale, bookWyrmScaleGolden,
		axeUmbrium, hoeUmbrium, pickaxeUmbrium, shovelUmbrium, swordUmbrium,
		swordScarlite, razorScarlite, 
		umbraBlaster, concussionSmasher,
		umbriumHelmet, umbriumChestplate, umbriumLeggings, umbriumBoots,
		scaleHelmet, scaleChestplate, scaleLeggings, scaleBoots, 
		scaleGoldenHelmet, scaleGoldenChestplate, scaleGoldenLeggings, scaleGoldenBoots,
		bookWyrmAnalyzer,
		callingStone,
		essenceDestroyer, ravagingIngot, axeRavaging, pickaxeRavaging, shovelRavaging, theRavager,
		pelletUmbrium, pelletSpiked, pelletRavaging;
	private static List<Item> itemList = new ArrayList<>();
	static List<Item> itemBlockList = new ArrayList<>();
	
	public static ToolMaterial materialUmbrium, materialScarlite, materialScarliteRazor, materialRavaging;
	public static ArmorMaterial materialUmbriumA, materialScales, materialScalesGolden;
	
	static {
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
		bookWyrmRaw = registerItem(new ItemFood(3, 0.3F, true).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.5F), "book_wyrm_raw");
		bookWyrmCooked = registerItem(new ItemFood(8, 0.8F, true), "book_wyrm_cooked");
		bookWyrmScale = registerItem(new Item(), "book_wyrm_scale");
		bookWyrmScaleGolden = registerItem(new Item(), "book_wyrm_scale_golden");
		
		//Tools
		//Because axes' stats aren't calculated like normal tools, we have to manually specify them		
		materialUmbrium = EnumHelper.addToolMaterial("umbrium", 2, 302, 5.0F, 2.0F, 20).setRepairItem(new ItemStack(umbriumIngot));
		axeUmbrium = registerItem(new ItemAxeGeneric(materialUmbrium, 8.0F, -3.1F), "umbrium_axe");
		hoeUmbrium = registerItem(new ItemHoeGeneric(materialUmbrium), "umbrium_hoe");
		pickaxeUmbrium = registerItem(new ItemPickaxeGeneric(materialUmbrium), "umbrium_pickaxe");
		shovelUmbrium = registerItem(new ItemShovelGeneric(materialUmbrium), "umbrium_shovel");
		swordUmbrium = registerItem(new ItemSwordGeneric(materialUmbrium), "umbrium_sword");
		
		materialScarlite = EnumHelper.addToolMaterial("scarlite", 3, 1561, 6.0F, 2.0F, 14).setRepairItem(new ItemStack(scarlite));
		swordScarlite = registerItem(new ItemSwordScarlite(materialScarlite), "scarlite_reaver");
		
		materialScarliteRazor = EnumHelper.addToolMaterial("scarlite_razor", 3, 31, 6.0F, -1.0F, 14).setRepairItem(new ItemStack(umbriumIngot));
		razorScarlite = registerItem(new ItemRazorScarlite(materialScarliteRazor), "scarlite_razor");
		
		//Fun stuff
		umbraBlaster = registerItem(new ItemUmbraBlaster(465), "umbra_blaster");
		concussionSmasher = registerItem(new ItemConcussionSmasher(178), "concussion_smasher");
		
		//Armor
		materialUmbriumA = EnumHelper.addArmorMaterial("umbrium", "umbrium", 18, new int[]{1, 4, 5, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
		materialUmbriumA.setRepairItem(new ItemStack(umbriumIngot));
		umbriumHelmet = registerItem(new ItemArmorGeneric("umbrium", materialUmbriumA, EntityEquipmentSlot.HEAD), "umbrium_helmet");
		umbriumChestplate = registerItem(new ItemArmorGeneric("umbrium", materialUmbriumA, EntityEquipmentSlot.CHEST), "umbrium_chestplate");
		umbriumLeggings = registerItem(new ItemArmorGeneric("umbrium", materialUmbriumA, EntityEquipmentSlot.LEGS), "umbrium_leggings");
		umbriumBoots = registerItem(new ItemArmorGeneric("umbrium", materialUmbriumA, EntityEquipmentSlot.FEET), "umbrium_boots");
		
		materialScales = EnumHelper.addArmorMaterial("bookWyrmScales", "book_wyrm_scale", 10, new int[]{1, 3, 4, 2}, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
		materialScales.setRepairItem(new ItemStack(bookWyrmScale));
		scaleHelmet = registerItem(new ItemArmorGeneric("book_wyrm_scale", materialScales, EntityEquipmentSlot.HEAD), "scale_helmet");
		scaleChestplate = registerItem(new ItemArmorGeneric("book_wyrm_scale", materialScales, EntityEquipmentSlot.CHEST), "scale_chestplate");
		scaleLeggings = registerItem(new ItemArmorGeneric("book_wyrm_scale", materialScales, EntityEquipmentSlot.LEGS), "scale_leggings");
		scaleBoots = registerItem(new ItemArmorGeneric("book_wyrm_scale", materialScales, EntityEquipmentSlot.FEET), "scale_boots");
		
		materialScalesGolden = EnumHelper.addArmorMaterial("bookWyrmScalesGolden", "book_wyrm_scale_golden", 10, new int[]{1, 3, 4, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F);
		materialScalesGolden.setRepairItem(new ItemStack(bookWyrmScaleGolden));
		scaleGoldenHelmet = registerItem(new ItemArmorGeneric("book_wyrm_scale_golden", materialScalesGolden, EntityEquipmentSlot.HEAD), "scale_golden_helmet");
		scaleGoldenChestplate = registerItem(new ItemArmorGeneric("book_wyrm_scale_golden", materialScalesGolden, EntityEquipmentSlot.CHEST), "scale_golden_chestplate");
		scaleGoldenLeggings = registerItem(new ItemArmorGeneric("book_wyrm_scale_golden", materialScalesGolden, EntityEquipmentSlot.LEGS), "scale_golden_leggings");
		scaleGoldenBoots = registerItem(new ItemArmorGeneric("book_wyrm_scale_golden", materialScalesGolden, EntityEquipmentSlot.FEET), "scale_golden_boots");
		
		//Non fighting tools
		bookWyrmAnalyzer = registerItem(new ItemBookWyrmAnalyzer(), "book_wyrm_analyzer");
		
		//Boss stuff
		//The Destroyer
		callingStone = registerItem(new ItemBossSummoner() {
			protected Entity getBoss(World worldIn)
		    {
				EntityDestroyer boss = new EntityDestroyer(worldIn);
				boss.ignite();
				
				return boss;
		    }
		}, "calling_stone");
		essenceDestroyer = registerItem(new ItemSimpleFoiled(), "essence_destroyer");
		ravagingIngot = registerItem(new ItemSimpleFoiled(), "ravaging_ingot");
		
		materialRavaging = EnumHelper.addToolMaterial("ravaging", 0, 2107, 60.0F, 3.0F, 1).setRepairItem(new ItemStack(ravagingIngot));
		axeRavaging = registerItem(new ItemAxeFoiled(materialRavaging, 8.0F, -3.0F), "ravaging_axe");
		pickaxeRavaging = registerItem(new ItemPickaxeFoiled(materialRavaging), "ravaging_pickaxe");
		shovelRavaging = registerItem(new ItemShovelFoiled(materialRavaging), "ravaging_shovel");
		
		theRavager = registerItem(new ItemRavager(2107), "the_ravager");
		pelletUmbrium = registerItem(new ItemPellet(10), "pellet_umbrium");
		pelletSpiked = registerItem(new ItemPellet(10), "pellet_spiked");
		pelletRavaging = registerItem(new ItemPellet(20) {
		    @SideOnly(Side.CLIENT)
		    public boolean hasEffect(ItemStack stack)
		    {
		        return true;
		    }
		}, "pellet_ravaging");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		//Just making sure item blocks get registered before items
		for (Item i : itemBlockList) event.getRegistry().register(i);
		for (Item i : itemList) event.getRegistry().register(i);
		ModRecipes.initOreDict();
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent evt)
	{
		for (Item i : itemBlockList) initModel(i);
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
		item.setUnlocalizedName(LocUtils.prefix(name));
        
        if (tab != null) item.setCreativeTab(tab);
        
        itemList.add(item);
        
		return item;
	}

}
