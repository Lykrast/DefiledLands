package lykrast.defiledlands.common.entity.monster;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.entity.IEntityDefiled;
import lykrast.defiledlands.common.entity.ai.EntityAIAttackMeleeStrafe;
import lykrast.defiledlands.common.util.Config;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityScuttler extends EntitySpider implements IEntityDefiled {
	
    public static final ResourceLocation LOOT = new ResourceLocation(DefiledLands.MODID, "entities/scuttler");

	public EntityScuttler(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void initEntityAI()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.5F));
		this.tasks.addTask(4, new EntityScuttler.AISpiderAttack(this));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityIronGolem>(this, EntityIronGolem.class, false));
	}

	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        //this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.34D);
    }

    public void fall(float distance, float damageMultiplier)
    {
    }
    
    public int getMaxFallHeight()
    {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
    	if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL) return false;
    	return this.isValidLightLevel();
    }
    
    @Override
    protected boolean isValidLightLevel()
    {
    	if (!Config.scuttlerSpawnInLight) return super.isValidLightLevel();
    	
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        int i = this.world.getLightFromNeighbors(blockpos);
        
    	if (i >= 8)
    	{
    		return this.rand.nextInt(16) == 0;
    	}
    	return i <= this.rand.nextInt(8);
    }
    
    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

	static class AISpiderAttack extends EntityAIAttackMeleeStrafe
	{
		public AISpiderAttack(EntitySpider spider)
		{
			super(spider, 1.0D, true, 0.6F);
		}

		protected double getAttackReachSqr(EntityLivingBase attackTarget)
		{
			return (double)(4.0F + attackTarget.width);
		}
	}

}
