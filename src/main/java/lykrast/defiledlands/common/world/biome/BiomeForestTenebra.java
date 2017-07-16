package lykrast.defiledlands.common.world.biome;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeForestTenebra extends BiomeDefiled {
	
	public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Tenebra Forest");

    static {
        properties.setTemperature(Biomes.FOREST.getTemperature());
        properties.setRainfall(Biomes.FOREST.getRainfall());
        properties.setBaseHeight(Biomes.FOREST.getBaseHeight());
        properties.setHeightVariation(Biomes.FOREST.getHeightVariation());
    }
	
	public BiomeForestTenebra()
	{
		super(properties);
        this.decorator.treesPerChunk = 10;
	}

}
