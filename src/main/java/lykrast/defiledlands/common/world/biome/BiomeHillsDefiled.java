package lykrast.defiledlands.common.world.biome;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeHillsDefiled extends BiomeDefiled {
	
	public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Defiled Hills");

    static {
        properties.setTemperature(Biomes.EXTREME_HILLS.getTemperature());
        properties.setRainfall(Biomes.EXTREME_HILLS.getRainfall());
        properties.setBaseHeight(Biomes.EXTREME_HILLS.getBaseHeight());
        properties.setHeightVariation(Biomes.EXTREME_HILLS.getHeightVariation());
    }
	
	public BiomeHillsDefiled()
	{
		super(properties);
	}

}
