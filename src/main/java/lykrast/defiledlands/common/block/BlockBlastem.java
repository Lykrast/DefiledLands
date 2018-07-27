package lykrast.defiledlands.common.block;

import java.util.Random;

import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.init.ModItems;
import lykrast.defiledlands.common.util.PlantUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBlastem extends BlockBush implements IGrowable {
	
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
	
	public BlockBlastem()
	{
		super(Material.PLANTS);
		this.setHardness(0.1F);
        this.setResistance(0.5F);
        this.setSoundType(SoundType.PLANT);
        setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
        setTickRandomly(true);
	}

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
    }
	
	/**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	
    	int age = state.getValue(AGE);
    	
    	if (age == 15 && PlantUtils.vulnerableToBlastem(entityIn))
    	{
    		worldIn.setBlockState(pos, state.withProperty(AGE, 0), 2);
    		
    		if (!worldIn.isRemote)
    		{
    			boolean flag = worldIn.getGameRules().getBoolean("mobGriefing");
    			worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1.0F, flag);
    		}
    	}
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion explosionIn)
    {
        return false;
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	int j = state.getValue(AGE);

    	if (j < 15)
    	{
    		if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true))
    		{
    			worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 2);
    			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
    		}
    	}
    	
    	super.updateTick(worldIn, pos, state, rand);
    }
    
    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        super.getDrops(drops, world, pos, state, fortune);
        int age = state.getValue(AGE);
        
        if (age == 15)
        {
        	drops.add(new ItemStack(ModItems.blastemFruit, 1, 0));
        }
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	int age = state.getValue(AGE);
    	
    	if (age < 15)
    	{
    		return false;
    	}
    	else
    	{
    		worldIn.setBlockState(pos, state.withProperty(AGE, 10), 2);
    		
    		if (!worldIn.isRemote)
    		{
    			EntityItem fruit = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.blastemFruit));
				worldIn.spawnEntity(fruit);
    		}
    		
    		return true;
    	}
    }
    
    @Override
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }
    
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos.down());
        Block block = state.getBlock();
        //if (block.canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, this)) return true;

        if (block != ModBlocks.dirtDefiled && block != ModBlocks.grassDefiled)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean canBlockStay(World worldIn, BlockPos pos)
    {
        return this.canPlaceBlockAt(worldIn, pos);
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, meta);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE);
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return state.getValue(AGE) < 15;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		int age = Math.min(15, state.getValue(AGE) + MathHelper.getInt(rand, 2, 5));
		worldIn.setBlockState(pos, state.withProperty(AGE, age), 2);
	}

}
