package lykrast.defiledlands.common.block;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.world.feature.WorldGenTenebra;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockSaplingTenebra extends BlockSapling {
	
	public BlockSaplingTenebra(float hardness)
	{
		setSoundType(SoundType.PLANT);
		setHardness(hardness);
		setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
	}
	
	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        int i = 0;
        int j = 0;
        
        WorldGenerator worldgenerator = new WorldGenTenebra(true);

        IBlockState iblockstate2 = Blocks.AIR.getDefaultState();
        worldIn.setBlockState(pos, iblockstate2, 4);
        
        if (!worldgenerator.generate(worldIn, rand, pos.add(i, 0, j)))
        {
        	worldIn.setBlockState(pos, state, 4);
        }
    }

    /**
     * Return true if the block can sustain a Bush
     */
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == ModBlocks.dirtDefiled || state.getBlock() == ModBlocks.grassDefiled;
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((Integer)state.getValue(STAGE)).intValue() << 3;
        return i;
    }

    // Type is here to prevent crashes despite not being used
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {TYPE, STAGE});
    }

}
