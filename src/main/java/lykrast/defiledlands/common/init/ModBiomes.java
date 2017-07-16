package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.common.world.biome.*;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class ModBiomes {
	
	public static List<Biome> biomeList = new ArrayList<Biome>();
	public static Biome desertDefiled = new BiomeDesertDefiled(), 
			plainsDefiled = new BiomePlainsDefiled(),
			forestTenebra = new BiomeForestTenebra(),
			forestVilespine = new BiomeForestVilespine(),
			hillsDefiled = new BiomeHillsDefiled(),
			swampDefiled = new BiomeSwampDefiled();
	
    public static void registerBiomes()
    {
    	register(desertDefiled, BiomeManager.BiomeType.DESERT, "desert_defiled", 6, SPOOKY, HOT, DRY, SANDY);
    	register(plainsDefiled, BiomeManager.BiomeType.WARM, "plains_defiled", 6, SPOOKY, PLAINS);
    	register(forestTenebra, BiomeManager.BiomeType.WARM, "forest_tenebra", 6, SPOOKY, FOREST);
    	register(forestVilespine, BiomeManager.BiomeType.WARM, "forest_vilespine", 10, SPOOKY, FOREST);
    	register(hillsDefiled, BiomeManager.BiomeType.COOL, "hills_defiled", 6, SPOOKY, MOUNTAIN, HILLS);
    	register(swampDefiled, BiomeManager.BiomeType.WARM, "swamp_defiled", 6, SPOOKY, WET, SWAMP);
    }
	
    public static void register(Biome biome, BiomeManager.BiomeType type, String name, int weight, BiomeDictionary.Type... biomeDictTypes)
    {
		biome.setRegistryName(new ResourceLocation(DefiledLands.MODID, name));
		ForgeRegistries.BIOMES.register(biome);
		for (BiomeDictionary.Type biomeDictType : biomeDictTypes) {
            BiomeDictionary.addTypes(biome, biomeDictType);
        }
		BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biome, weight));
		biomeList.add(biome);
	}

}
