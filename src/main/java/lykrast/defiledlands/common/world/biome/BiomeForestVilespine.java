package lykrast.defiledlands.common.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomeForestVilespine extends BiomeDefiled {
	
	public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Vilespine Forest");

    static {
        properties.setTemperature(Biomes.FOREST.getTemperature());
        properties.setRainfall(Biomes.FOREST.getRainfall());
        properties.setBaseHeight(Biomes.FOREST.getBaseHeight());
        properties.setHeightVariation(Biomes.JUNGLE.getHeightVariation());
    }
	
	public BiomeForestVilespine()
	{
		super(properties);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.05F;
		this.vilespinePerChunk = 500;
	}

}
