package lykrast.defiledlands.common.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomeIcePlainsDefiled extends BiomeDefiled {
	
	public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Defiled Ice Plains");

    static {
        properties.setTemperature(Biomes.ICE_PLAINS.getTemperature());
        properties.setRainfall(Biomes.ICE_PLAINS.getRainfall());
        properties.setBaseHeight(Biomes.ICE_PLAINS.getBaseHeight());
        properties.setHeightVariation(Biomes.ICE_PLAINS.getHeightVariation());
    }
	
	public BiomeIcePlainsDefiled()
	{
		super(properties);
	}

}
