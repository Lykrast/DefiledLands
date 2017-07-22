package lykrast.defiledlands.common.world.feature;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
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
            		
            		int x = 0, y = 0, z = 0;
            		placeLogAt(worldIn, position.add(x,y,z));
            		for (y=1; y<=i;y++)
            		{
            			if (rand.nextInt(2) == 0)
            			{
            				if (rand.nextInt(2) == 0) x += rand.nextInt(3) - 1;
            				else z += rand.nextInt(3) - 1;
            			}
            			if (!canGrowInto(worldIn.getBlockState(position.add(x,y,z)).getBlock())) break;
            			placeLogAt(worldIn, position.add(x,y,z));
            		}
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
