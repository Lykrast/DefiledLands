package lykrast.defiledlands.common.world.biome;

import java.util.Random;

import lykrast.defiledlands.common.entity.monster.EntityHost;
import lykrast.defiledlands.common.entity.monster.EntityScuttler;
import lykrast.defiledlands.common.entity.monster.EntityShambler;
import lykrast.defiledlands.common.entity.monster.EntityShamblerTwisted;
import lykrast.defiledlands.common.entity.passive.EntityBookWyrm;
import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.util.Config;
import lykrast.defiledlands.common.world.feature.WorldGenBlastem;
import lykrast.defiledlands.common.world.feature.WorldGenConjuringAltar;
import lykrast.defiledlands.common.world.feature.WorldGenCorruptionPost;
import lykrast.defiledlands.common.world.feature.WorldGenDungeonsDefiled;
import lykrast.defiledlands.common.world.feature.WorldGenScuronotte;
import lykrast.defiledlands.common.world.feature.WorldGenTenebra;
import lykrast.defiledlands.common.world.feature.WorldGenVilespine;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BiomeDefiled extends Biome {

    public static WorldGenerator vilespineGen = new WorldGenVilespine(), 
    		blastemGen = new WorldGenBlastem(),
    		tenebraGen = new WorldGenTenebra(false),
    		scuronotteGen = new WorldGenScuronotte(),
    		corruptionPosGen = new WorldGenCorruptionPost();
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
		
		if (Config.bookWyrmSpawn)
			this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityBookWyrm.class, 4, 2, 4));
		
		if (Config.weightShambler > 0)
			this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityShambler.class, Config.weightShambler, 1, 2));
		if (Config.weightTwistedShambler > 0)
			this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityShamblerTwisted.class, Config.weightTwistedShambler, 1, 2));
		if (Config.weightScuttler > 0)
			this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityScuttler.class, Config.weightScuttler, 1, 3));
		if (Config.weightHost > 0)
			this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHost.class, Config.weightHost, 2, 3));
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
	
	@SuppressWarnings("deprecation")
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

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DESERT_WELL))
        if (worldIn.getChunkFromBlockCoords(pos).getRandomWithSeed(119432789L).nextInt(50) == 0)
        {
            int i = rand.nextInt(16) + 8;
            int j = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
            (new WorldGenConjuringAltar()).generate(worldIn, rand, blockpos);
        }
		
		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS))
			for (int j5 = 0; j5 < this.vilespinePerChunk; ++j5)
			{
				int l9 = rand.nextInt(16) + 8;
				int k13 = rand.nextInt(16) + 8;
				int l16 = worldIn.getHeight(pos.add(l9, 0, k13)).getY() * 2;

				if (l16 > 0)
				{
					int j19 = rand.nextInt(l16);
					vilespineGen.generate(worldIn, rand, pos.add(l9, j19, k13));
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
                scuronotteGen.generate(worldIn, rand, blockpos2);
			}
		}
		
		for (int x=8;x<24;x++)
		{
			for (int z=8;z<24;z++)
			{
                BlockPos blockpos2 = worldIn.getHeight(pos.add(x,0,z));
                if (worldIn.getBiome(blockpos2) instanceof BiomeDefiled)
                {
                	corruptionPosGen.generate(worldIn, rand, blockpos2);
                }
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public int getGrassColorAtPos(BlockPos blockPos) {
        return 0x442f44;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getFoliageColorAtPos(BlockPos blockPos) {
        return 0x6b5b68;
    }

    @Override
    public int getSkyColorByTemp(float temp) {
        return 0x220033;
    }

    @Override
    public int getWaterColorMultiplier() {
        return 0x6b5b68;
    }
}
