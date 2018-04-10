package lykrast.defiledlands.common.init;

import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.MOUNTAIN;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;
import static net.minecraftforge.common.BiomeDictionary.Type.SNOWY;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WASTELAND;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import lykrast.defiledlands.common.util.Config;
import lykrast.defiledlands.common.world.biome.BiomeDesertDefiled;
import lykrast.defiledlands.common.world.biome.BiomeForestTenebra;
import lykrast.defiledlands.common.world.biome.BiomeForestVilespine;
import lykrast.defiledlands.common.world.biome.BiomeHillsDefiled;
import lykrast.defiledlands.common.world.biome.BiomeIcePlainsDefiled;
import lykrast.defiledlands.common.world.biome.BiomePlainsDefiled;
import lykrast.defiledlands.common.world.biome.BiomeSwampDefiled;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModBiomes {
	
	public static Biome desertDefiled = new BiomeDesertDefiled(), 
			plainsDefiled = new BiomePlainsDefiled(),
			forestTenebra = new BiomeForestTenebra(),
			forestVilespine = new BiomeForestVilespine(),
			hillsDefiled = new BiomeHillsDefiled(),
			swampDefiled = new BiomeSwampDefiled(),
			icePlainsDefiled = new BiomeIcePlainsDefiled();

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event)
	{
    	register(event, desertDefiled, BiomeManager.BiomeType.DESERT, "desert_defiled", Config.weightDesertDefiled, SPOOKY, HOT, DRY, SANDY);
    	register(event, plainsDefiled, BiomeManager.BiomeType.WARM, "plains_defiled", Config.weightPlainsDefiled, SPOOKY, PLAINS);
    	register(event, forestTenebra, BiomeManager.BiomeType.WARM, "forest_tenebra", Config.weightForestTenebra, SPOOKY, FOREST);
    	register(event, forestVilespine, BiomeManager.BiomeType.WARM, "forest_vilespine", Config.weightForestVilespine, SPOOKY, FOREST);
    	register(event, hillsDefiled, BiomeManager.BiomeType.COOL, "hills_defiled", Config.weightHillsDefiled, SPOOKY, MOUNTAIN, HILLS);
    	register(event, swampDefiled, BiomeManager.BiomeType.WARM, "swamp_defiled", Config.weightSwampDefiled, SPOOKY, WET, SWAMP);
    	register(event, icePlainsDefiled, BiomeManager.BiomeType.ICY, "ice_plains_defiled", Config.weightIcePlainsDefiled, SPOOKY, COLD, SNOWY, WASTELAND);
	}
	
    public static void register(RegistryEvent.Register<Biome> event, Biome biome, BiomeManager.BiomeType type, String name, int weight, BiomeDictionary.Type... biomeDictTypes)
    {
		biome.setRegistryName(new ResourceLocation(DefiledLands.MODID, name));
		event.getRegistry().register(biome);
		for (BiomeDictionary.Type biomeDictType : biomeDictTypes) {
            BiomeDictionary.addTypes(biome, biomeDictType);
        }
		BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biome, weight));
	}

}
