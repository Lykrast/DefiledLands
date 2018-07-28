package lykrast.defiledlands.common.entity.monster;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.entity.IEntityDefiled;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHost extends EntityMob implements IEntityDefiled {
    public static final ResourceLocation LOOT = new ResourceLocation(DefiledLands.MODID, "entities/host");
    
	protected int slimesRemaining, slimesQueued;

    public EntityHost(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.7F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        slimesRemaining = (int)getMaxHealth();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
    	spawnSlimes();
    	super.onLivingUpdate();
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
    	queueSlimes(slimesRemaining);
    	spawnSlimes();
    	super.setDead();
    }
    
    protected void queueSlimes(int nb)
    {
    	if (slimesRemaining > 0)
    	{
        	int i = Math.min(nb, slimesRemaining);
        	slimesRemaining -= i;
        	slimesQueued += i;
    	}
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            boolean flag = super.attackEntityFrom(source, amount);
            
            if (flag)
            {
            	queueSlimes((int)amount);
            }
            
            return flag;
        }
    }
    
    protected void spawnSlimes()
    {    	
    	while (slimesQueued >= 2)
    	{
    		int i = 1;
    		if (slimesQueued > 8) i = 2;
    		
    		if (!this.world.isRemote)
    		{
    			int k = slimesQueued / 2;
    			float f = ((float)(k % 2) - 0.5F) * 0.5F;
    			float f1 = ((float)(k / 2) - 0.5F) * 0.5F;
    			EntitySlimeDefiled slime = new EntitySlimeDefiled(world);

    			if (isNoDespawnRequired()) slime.enablePersistence();

    			slime.setSlimeSize(i, true);
    			slime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
    			this.world.spawnEntity(slime);
    		}
    		
    		if (i == 1) slimesQueued -= 2;
    		else slimesQueued -= i*4;
    	}
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setShort("SlimesRemaining", (short)slimesRemaining);
        compound.setShort("SlimesQueued", (short)slimesQueued);
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("SlimesRemaining", 99))
        {
        	slimesRemaining = compound.getShort("SlimesRemaining");
        }

        if (compound.hasKey("SlimesQueued", 99))
        {
        	slimesQueued = compound.getShort("SlimesQueued");
        }
    }
    
    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

}
