package lykrast.defiledlands.common.world.biome;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomeDesertDefiled extends BiomeDefiled {
	
	public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Defiled Desert");

    static {
        properties.setTemperature(Biomes.DESERT.getTemperature());
        properties.setRainfall(Biomes.DESERT.getRainfall());
        properties.setBaseHeight(Biomes.DESERT.getBaseHeight());
        properties.setHeightVariation(Biomes.DESERT.getHeightVariation());
    }
	
	public BiomeDesertDefiled()
	{
		super(properties);
        this.topBlock = ModBlocks.sandDefiled.getDefaultState();
        this.fillerBlock = ModBlocks.sandDefiled.getDefaultState();
        this.decorator.treesPerChunk = -999;
	}

}
