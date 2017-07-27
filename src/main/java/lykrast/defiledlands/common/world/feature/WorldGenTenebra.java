package lykrast.defiledlands.common.world.feature;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTenebra extends WorldGenAbstractTree {

	public WorldGenTenebra(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
		int i = rand.nextInt(6) + rand.nextInt(6) + 3;
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + i + 1 <= 256)
		{

            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j)
            {
                int k = 1;

                if (j == position.getY())
                {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2)
                {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l)
                {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < 256)
                        {
                            if (!this.isReplaceable(worldIn,blockpos$mutableblockpos.setPos(l, j, i1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
            	Block soil = worldIn.getBlockState(position.down()).getBlock();
            	if ((soil == ModBlocks.grassDefiled || soil == ModBlocks.dirtDefiled) && position.getY() < worldIn.getHeight() - i - 1)
            	{
            		setDirtAt(worldIn,position.down());
            		//Causes phantom blocks for some reason, so we use a more hacky method
            		//BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(position);
            		BlockPos mutable = new BlockPos(position);
            		
            		placeLogAt(worldIn, mutable);
            		for (int j=1; j<=i;j++)
            		{
            			mutable = mutable.up();
            			if (rand.nextInt(3) == 0)
            			{
            				mutable = mutable.offset(EnumFacing.Plane.HORIZONTAL.random(rand));
            			}
            			if (!canGrowInto(worldIn.getBlockState(mutable).getBlock())) break;
            			placeLogAt(worldIn, mutable);
            			
            			for (EnumFacing e : EnumFacing.HORIZONTALS)
            			{
            				if (rand.nextInt(6) == 0) placeLeafAt(worldIn, mutable.offset(e));
            			}
            		}
            		if (rand.nextInt(6) == 0) placeLeafAt(worldIn, mutable.up());
            	}
            	else
            	{
            		return false;
            	}
            }
		}
		else
		{
			return false;
		}

        return true;
    }

    private void placeLogAt(World worldIn, BlockPos pos)
    {
        this.setBlockAndNotifyAdequately(worldIn, pos, ModBlocks.tenebraLog.getDefaultState());
    }

    private void placeLeafAt(World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);

        if (state.getBlock().isAir(state, worldIn, pos) || state.getBlock().isLeaves(state, worldIn, pos))
        {
            this.setBlockAndNotifyAdequately(worldIn, pos, ModBlocks.tenebraLeaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)));
        }
    }
	
	@Override
    /**
     * returns whether or not a tree can grow into a block
     * For example, a tree will not grow into stone
     */
    protected boolean canGrowInto(Block blockType)
    {
        Material material = blockType.getDefaultState().getMaterial();
        return super.canGrowInto(blockType) || blockType == ModBlocks.dirtDefiled || blockType == ModBlocks.grassDefiled || blockType == ModBlocks.tenebraLog;
    }
	
	@Override
    /**
     * sets dirt at a specific location if it isn't already dirt
     */
    protected void setDirtAt(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getBlock() == ModBlocks.grassDefiled)
        {
            this.setBlockAndNotifyAdequately(worldIn, pos, ModBlocks.dirtDefiled.getDefaultState());
        }
    }
}
