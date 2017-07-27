package lykrast.defiledlands.common.world.biome;

import java.util.Random;

import lykrast.defiledlands.common.entity.monster.*;
import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.world.feature.*;
import net.minecraft.block.BlockBush;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BiomeDefiled extends Biome {

    public static WorldGenerator vilespineGen = new WorldGenVilespine(), 
    		blastemGen = new WorldGenBlastem(),
    		tenebraGen = new WorldGenTenebra(false),
    		scuronotteGen = new WorldGenScuronotte();
	protected int vilespinePerChunk, scuronottePerChunk;

	public BiomeDefiled(BiomeProperties properties)
	{
		super(properties);
        this.topBlock = ModBlocks.grassDefiled.getDefaultState();
        this.fillerBlock = ModBlocks.dirtDefiled.getDefaultState();
		this.vilespinePerChunk = 50;
		this.scuronottePerChunk = 2;
		
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityShambler.class, 80, 1, 2));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityShamblerTwisted.class, 80, 1, 2));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityScuttler.class, 100, 1, 3));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHost.class, 100, 2, 3));
	}

    /**
     * returns the chance a creature has to spawn.
     */
    public float getSpawningChance()
    {
        return 0.13F;
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return (WorldGenAbstractTree)(rand.nextInt(5) > 0 ? tenebraGen : TREE_FEATURE);
    }
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
//        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, worldIn, rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON))
//        {
            for (int j2 = 0; j2 < 80; ++j2)
            {
                int i3 = rand.nextInt(16) + 8;
                int l3 = rand.nextInt(256);
                int l1 = rand.nextInt(16) + 8;
                (new WorldGenDungeonsDefiled()).generate(worldIn, rand, pos.add(i3, l3, l1));
            }
//        }
        
		super.decorate(worldIn, rand, pos);
		
		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS))
			for (int j5 = 0; j5 < this.vilespinePerChunk; ++j5)
			{
				int l9 = rand.nextInt(16) + 8;
				int k13 = rand.nextInt(16) + 8;
				int l16 = worldIn.getHeight(pos.add(l9, 0, k13)).getY() * 2;

				if (l16 > 0)
				{
					int j19 = rand.nextInt(l16);
					this.vilespineGen.generate(worldIn, rand, pos.add(l9, j19, k13));
				}
			}
		
		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN))
		{
			int i = rand.nextInt(16) + 8;
	        int j = rand.nextInt(16) + 8;
	        int height = worldIn.getHeight(pos.add(i, 0, j)).getY() * 2;
	        if (height < 1) height = 1;
	        int k = rand.nextInt(height);
	        
	        blastemGen.generate(worldIn, rand, pos.add(i, k, j));
		}
		
		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM))
		{
			for (int l3 = 0; l3 < this.scuronottePerChunk; ++l3)
			{
				int i8 = rand.nextInt(16) + 8;
                int l11 = rand.nextInt(16) + 8;
                BlockPos blockpos2 = worldIn.getHeight(pos.add(i8, 0, l11));
                this.scuronotteGen.generate(worldIn, rand, blockpos2);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public int getGrassColorAtPos(BlockPos blockPos) {
        return RGBToInt(68, 47, 68);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getFoliageColorAtPos(BlockPos blockPos) {
        return RGBToInt(107, 91, 104);
    }

    @Override
    public int getSkyColorByTemp(float temp) {
        return RGBToInt(34, 0, 51);
    }

    @Override
    public int getWaterColorMultiplier()
    {
        return RGBToInt(107, 91, 104);
    }

    
    /**
     * Convert r, g and b colors to an integer representation.
     * <br>
     * From Cyclops Core.
     * @param r red
     * @param g green
     * @param b blue
     * @return integer representation of the color.
     */
    private static int RGBToInt(int r, int g, int b) {
        return (int)r << 16 | (int)g << 8 | (int)b;
    }

}
