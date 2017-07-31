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

import java.util.ArrayList;
import java.util.List;

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
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBiomes {
	
	public static List<Biome> biomeList = new ArrayList<Biome>();
	public static Biome desertDefiled = new BiomeDesertDefiled(), 
			plainsDefiled = new BiomePlainsDefiled(),
			forestTenebra = new BiomeForestTenebra(),
			forestVilespine = new BiomeForestVilespine(),
			hillsDefiled = new BiomeHillsDefiled(),
			swampDefiled = new BiomeSwampDefiled(),
			icePlainsDefiled = new BiomeIcePlainsDefiled();
	
    public static void registerBiomes()
    {
    	register(desertDefiled, BiomeManager.BiomeType.DESERT, "desert_defiled", Config.weightDesertDefiled, SPOOKY, HOT, DRY, SANDY);
    	register(plainsDefiled, BiomeManager.BiomeType.WARM, "plains_defiled", Config.weightPlainsDefiled, SPOOKY, PLAINS);
    	register(forestTenebra, BiomeManager.BiomeType.WARM, "forest_tenebra", Config.weightForestTenebra, SPOOKY, FOREST);
    	register(forestVilespine, BiomeManager.BiomeType.WARM, "forest_vilespine", Config.weightForestVilespine, SPOOKY, FOREST);
    	register(hillsDefiled, BiomeManager.BiomeType.COOL, "hills_defiled", Config.weightHillsDefiled, SPOOKY, MOUNTAIN, HILLS);
    	register(swampDefiled, BiomeManager.BiomeType.WARM, "swamp_defiled", Config.weightSwampDefiled, SPOOKY, WET, SWAMP);
    	register(icePlainsDefiled, BiomeManager.BiomeType.ICY, "ice_plains_defiled", Config.weightIcePlainsDefiled, SPOOKY, COLD, SNOWY, WASTELAND);
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
