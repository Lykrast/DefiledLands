package lykrast.defiledlands.common.init;

import lykrast.defiledlands.client.render.entity.*;
import lykrast.defiledlands.common.entity.boss.*;
import lykrast.defiledlands.common.entity.monster.*;
import lykrast.defiledlands.common.entity.projectile.*;
import lykrast.defiledlands.common.util.DungeonDefiledList;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {
	
	private static int id = 1;
	
	public static void init()
	{
		//Monsters
		registerEntity(EntityShambler.class, "shambler", 0x171717, 0xebebeb);
        LootTableList.register(EntityShambler.LOOT);
		registerEntity(EntityShamblerTwisted.class, "shambler_twisted", 0x171717, 0xf62e2e);
        LootTableList.register(EntityShamblerTwisted.LOOT);
        
		registerEntity(EntityScuttler.class, "scuttler", 0x211823, 0xce0bff);
        LootTableList.register(EntityScuttler.LOOT);

		registerEntity(EntityHost.class, "host", 0x3a3a3a, 0xc873a0);
        LootTableList.register(EntityHost.LOOT);
		registerEntity(EntitySlimeDefiled.class, "slime_defiled", 0xbe6d91, 0xc873a0);
        LootTableList.register(EntitySlimeDefiled.LOOT);
        
        //Bosses
		registerEntity(EntityDestroyer.class, "the_destroyer", 0x643564, 0xf9f9f9);
        LootTableList.register(EntityDestroyer.LOOT);
        
        //Projectiles
        registerProjectile(EntityBlastemFruit.class, "blastem_fruit");
        registerProjectile(EntityBlastemFruitBlazing.class, "blastem_fruit_blazing");
        
        
        //Dungeon List
        DungeonDefiledList.addDungeonMob("scuttler", 200);
        DungeonDefiledList.addDungeonMob("host", 100);
        DungeonDefiledList.addDungeonMob("shambler_twisted", 100);
        //Registered here because that seemed the most fitting
        LootTableList.register(lykrast.defiledlands.common.world.feature.WorldGenDungeonsDefiled.LOOT);
	}
	
	public static void registerEntity(Class<? extends Entity> entityClass, String name, int colorBack, int colorFront)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(DefiledLands.MODID, name), entityClass, DefiledLands.MODID + "." + name, id++, DefiledLands.instance, 64, 3, true, colorBack, colorFront);
	}
	
	public static void registerProjectile(Class<? extends Entity> entityClass, String name)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(DefiledLands.MODID, name), entityClass, DefiledLands.MODID + "." + name, id++, DefiledLands.instance, 64, 10, true);
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityShambler.class, RenderShambler.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityShamblerTwisted.class, RenderShamblerTwisted.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityScuttler.class, RenderScuttler.FACTORY);
        
        RenderingRegistry.registerEntityRenderingHandler(EntityDestroyer.class, RenderDestroyer.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityHost.class, RenderHost.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySlimeDefiled.class, RenderSlimeDefiled.FACTORY);
        
        RenderingRegistry.registerEntityRenderingHandler(EntityBlastemFruit.class, RenderBlastemFruit.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBlastemFruitBlazing.class, RenderBlastemFruitBlazing.FACTORY);
    }

}
