package lykrast.defiledlands.common.world.biome;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
