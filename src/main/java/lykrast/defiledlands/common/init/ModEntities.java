package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.client.render.entity.RenderBlastemFruit;
import lykrast.defiledlands.client.render.entity.RenderBlastemFruitBlazing;
import lykrast.defiledlands.client.render.entity.RenderBookWyrm;
import lykrast.defiledlands.client.render.entity.RenderDestroyer;
import lykrast.defiledlands.client.render.entity.RenderHost;
import lykrast.defiledlands.client.render.entity.RenderRavagerProjectile;
import lykrast.defiledlands.client.render.entity.RenderScuttler;
import lykrast.defiledlands.client.render.entity.RenderShambler;
import lykrast.defiledlands.client.render.entity.RenderShamblerTwisted;
import lykrast.defiledlands.client.render.entity.RenderSlimeDefiled;
import lykrast.defiledlands.common.entity.boss.EntityDestroyer;
import lykrast.defiledlands.common.entity.monster.EntityHost;
import lykrast.defiledlands.common.entity.monster.EntityScuttler;
import lykrast.defiledlands.common.entity.monster.EntityShambler;
import lykrast.defiledlands.common.entity.monster.EntityShamblerTwisted;
import lykrast.defiledlands.common.entity.monster.EntitySlimeDefiled;
import lykrast.defiledlands.common.entity.passive.EntityBookWyrm;
import lykrast.defiledlands.common.entity.projectile.EntityBlastemFruit;
import lykrast.defiledlands.common.entity.projectile.EntityBlastemFruitBlazing;
import lykrast.defiledlands.common.entity.projectile.EntityRavagerProjectile;
import lykrast.defiledlands.common.util.DungeonDefiledList;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ModEntities {
	
	private static int id = 1;
	private static List<EntityEntry> entityList = new ArrayList<>();
	
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
        
        //Animals
		registerEntity(EntityBookWyrm.class, "book_wyrm", 0x412c41, 0xfef9e4);
        LootTableList.register(EntityBookWyrm.LOOT);
        
        //Bosses
		registerEntity(EntityDestroyer.class, "the_destroyer", 0x643564, 0xf9f9f9);
        LootTableList.register(EntityDestroyer.LOOT);
        
        //Projectiles
        registerProjectile(EntityBlastemFruit.class, "blastem_fruit");
        registerProjectile(EntityBlastemFruitBlazing.class, "blastem_fruit_blazing");
        registerProjectile(EntityRavagerProjectile.class, "ravager_projectile", 3);
        
        
        //Dungeon List
        DungeonDefiledList.addDungeonMob("scuttler", 160);
        DungeonDefiledList.addDungeonMob("host", 120);
        DungeonDefiledList.addDungeonMob("shambler_twisted", 20);
        //Registered here because that seemed the most fitting
        LootTableList.register(lykrast.defiledlands.common.world.feature.WorldGenDungeonsDefiled.LOOT);
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
	{
		for (EntityEntry e : entityList) event.getRegistry().register(e);
	}
	
	public static void registerEntity(Class<? extends Entity> entityClass, String name, int colorBack, int colorFront)
	{
		EntityEntryBuilder<Entity> builder = EntityEntryBuilder.create()
				.entity(entityClass)
				.name(DefiledLands.MODID + "." + name)
				.id(new ResourceLocation(DefiledLands.MODID, name), id++)
				.tracker(64, 3, true)
				.egg(colorBack, colorFront);
		entityList.add(builder.build());
	}
	
	public static void registerProjectile(Class<? extends Entity> entityClass, String name)
	{
		registerProjectile(entityClass, name, 10);
	}
	
	public static void registerProjectile(Class<? extends Entity> entityClass, String name, int updateRate)
	{
		EntityEntryBuilder<Entity> builder = EntityEntryBuilder.create()
				.entity(entityClass)
				.name(DefiledLands.MODID + "." + name)
				.id(new ResourceLocation(DefiledLands.MODID, name), id++)
				.tracker(64, updateRate, true);
		entityList.add(builder.build());
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityShambler.class, RenderShambler.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityShamblerTwisted.class, RenderShamblerTwisted.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityScuttler.class, RenderScuttler.FACTORY);
        
        RenderingRegistry.registerEntityRenderingHandler(EntityDestroyer.class, RenderDestroyer.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityHost.class, RenderHost.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySlimeDefiled.class, RenderSlimeDefiled.FACTORY);
        
        RenderingRegistry.registerEntityRenderingHandler(EntityBookWyrm.class, RenderBookWyrm.FACTORY);
        
        RenderingRegistry.registerEntityRenderingHandler(EntityBlastemFruit.class, RenderBlastemFruit.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBlastemFruitBlazing.class, RenderBlastemFruitBlazing.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRavagerProjectile.class, RenderRavagerProjectile.FACTORY);
    }

}
