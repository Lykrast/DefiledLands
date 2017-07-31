package lykrast.defiledlands.common.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomePlainsDefiled extends BiomeDefiled {
	
	public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Defiled Plains");

    static {
        properties.setTemperature(Biomes.PLAINS.getTemperature());
        properties.setRainfall(Biomes.PLAINS.getRainfall());
        properties.setBaseHeight(Biomes.PLAINS.getBaseHeight());
        properties.setHeightVariation(Biomes.PLAINS.getHeightVariation());
    }
	
	public BiomePlainsDefiled()
	{
		super(properties);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.05F;
	}

}
