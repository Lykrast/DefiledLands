package lykrast.defiledlands.common.world.biome;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
