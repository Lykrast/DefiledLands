package lykrast.defiledlands.common.entity.boss;

import java.util.Random;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.entity.IEntityDefiled;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityMourner extends EntityMob implements IEntityDefiled {
    public static final ResourceLocation LOOT = new ResourceLocation(DefiledLands.MODID, "entities/the_mourner");
    private static final DataParameter<Integer> INVULNERABILITY_TIME = EntityDataManager.<Integer>createKey(EntityMourner.class, DataSerializers.VARINT);
    protected static final DataParameter<Byte> MOURNER_FLAGS = EntityDataManager.<Byte>createKey(EntityMourner.class, DataSerializers.BYTE);
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

	public EntityMourner(World worldIn) {
		super(worldIn);
        setSize(0.7F, 2.4F);
        experienceValue = 200;
        isImmuneToFire = true;
        moveHelper = new AIMoveControl(this);
	}

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new AIDoNothing());
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new AIChargeAttack(this));
        this.tasks.addTask(4, new AIRangedAttack(this));
        this.tasks.addTask(8, new AIMoveRandom(this));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    	this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, false));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(128.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(400.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        dataManager.register(INVULNERABILITY_TIME, 0);
        dataManager.register(MOURNER_FLAGS, (byte)0);
    }

    /**
     * Tries to move the entity towards the specified location.
     */
    public void move(MoverType type, double x, double y, double z) {
        super.move(type, x, y, z);
        this.doBlockCollisions();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        this.noClip = true;
        super.onUpdate();
        this.noClip = false;
        this.setNoGravity(true);
    }
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();

        if (getInvulTime() > 0)
        {
            motionY = 0.01;

            if (world.isRemote)
            {
	            for (int i1 = 0; i1 < 3; ++i1)
	            {
	                world.spawnParticle(EnumParticleTypes.SPELL_MOB, posX + rand.nextGaussian(), posY + (double)(rand.nextFloat() * 3.3F), posZ + rand.nextGaussian(), 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
	            }
            }
            else
            {
            	int i = getInvulTime();
            	if ((i > 60 && i % 40 == 0) || (i <= 60 && i % 10 == 0))
            	{
            		int y = (int)posY + rand.nextInt(11);
            		MutableBlockPos pos = new MutableBlockPos((int)posX + rand.nextInt(15) - 7, y, (int)posZ + rand.nextInt(15) - 7);
            		while (pos.getY() > 0 && y - pos.getY() < 16 && world.isAirBlock(pos)) pos.setY(pos.getY() - 1);
            		
                    world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY() + 1, pos.getZ(), true));
            	}
            }
        }

        if (this.world.isRemote)
        {
        	if (isFiring())
        	{
            	EnumParticleTypes particle = EnumParticleTypes.BARRIER;
            	switch (getCurrentAttack()) {
            	case ATTACK_FIREBALLS:
            		particle = EnumParticleTypes.FLAME;
            		break;
            	case ATTACK_SHULKER:
            		particle = EnumParticleTypes.END_ROD;
            		break;
            	case ATTACK_GHAST:
            		particle = EnumParticleTypes.SMOKE_LARGE;
            		break;
            	}
            	
	            for (int i = 0; i < 2; ++i)
	            {
	                world.spawnParticle(particle, posX + (rand.nextDouble() - 0.5D) * (double)width, posY + rand.nextDouble() * (double)height, posZ + (rand.nextDouble() - 0.5D) * (double)width, 0.0D, 0.0D, 0.0D);
	            }
        	}
        }
    }
    
    protected void updateAITasks()
    {
    	if (this.getInvulTime() > 0)
    	{
    		int j1 = this.getInvulTime() - 1;

    		if (j1 <= 0)
    		{
    			this.world.newExplosion(this, this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, 7.0F, false, this.world.getGameRules().getBoolean("mobGriefing"));
    			//this.world.playBroadcastSound(1023, new BlockPos(this), 0);
                this.playSound(SoundEvents.ENTITY_WITHER_SPAWN, 10.0F, 1.0F);
    		}

    		this.setInvulTime(j1);
    	}
    	else
    	{
    		super.updateAITasks();
    		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    	}
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
        	int force = 6;
    		entityIn.motionY += 0.4D;

            if (entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).knockBack(this, (float)force * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
            }
            else
            {
            	entityIn.addVelocity((double)(-MathHelper.sin(this.rotationYaw * 0.017453292F) * (float)force * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * 0.017453292F) * (float)force * 0.5F));
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Initializes this Wither's explosion sequence and makes it invulnerable. Called immediately after spawning.
     */
    public void ignite()
    {
        this.setInvulTime(200);
    }

    /**
     * Explosion resistance of a block relative to this entity
     */
    public float getExplosionResistance(Explosion explosionIn, World worldIn, BlockPos pos, IBlockState blockStateIn)
    {
        float f = super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
        Block block = blockStateIn.getBlock();

        if (net.minecraft.entity.boss.EntityWither.canDestroyBlock(block) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, pos, blockStateIn))
        {
            f = Math.min(2F, f);
        }

        return f;
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
        else if (source != DamageSource.DROWN && !(source.getTrueSource() instanceof EntityMourner))
        {
            if (this.getInvulTime() > 0 && source != DamageSource.OUT_OF_WORLD)
            {
                return false;
            }

            return super.attackEntityFrom(source, amount);
        }
        else
        {
        	return false;
        }
    }
    
    /**
     * handles entity death timer, experience orb and particle creation
     */
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime >= 180 && this.deathTime <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }

        boolean flag = this.world.getGameRules().getBoolean("doMobLoot");
        int i = this.experienceValue;

        if (!this.world.isRemote)
        {
            if (this.deathTime > 150 && this.deathTime % 5 == 0 && flag)
            {
                this.dropExperience(MathHelper.floor((float)i * 0.08F));
            }
            
            if (this.deathTime == 1)
            {
                //this.world.playBroadcastSound(1028, new BlockPos(this), 0);
                this.playSound(SoundEvents.ENTITY_ENDERDRAGON_DEATH, 10.0F, 1.0F);
            }
        }
        
        this.motionY = 0.01;
        
        if (this.deathTime >= 200)
        {
        	if (!this.world.isRemote)
        	{
        		if (flag)
        		{
        			this.dropExperience(MathHelper.floor((float)i * 0.2F));
        		}

        		this.setDead();
        	}

            for (int k = 0; k < 20; ++k)
            {
            	
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
    }

    private void dropExperience(int p_184668_1_)
    {
        while (p_184668_1_ > 0)
        {
            int i = EntityXPOrb.getXPSplit(p_184668_1_);
            p_184668_1_ -= i;
            this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, i));
        }
    }

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    /**
     * Returns false if this Entity is a boss, true otherwise.
     */
    public boolean isNonBoss()
    {
        return false;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Invul", this.getInvulTime());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setInvulTime(compound.getInteger("Invul"));

        if (this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    private boolean getMournerFlag(int mask) {
        int i = dataManager.get(MOURNER_FLAGS);
        return (i & mask) != 0;
    }

    private void setMournerFlag(int mask, boolean value) {
        int i = dataManager.get(MOURNER_FLAGS);

        if (value) i |= mask;
        else i &= ~mask;

        dataManager.set(MOURNER_FLAGS, (byte)(i & 255));
    }

    public boolean isCharging() {
        return getMournerFlag(1);
    }
    public void setIsCharging(boolean value) {
        setMournerFlag(1, value);
    }

    public boolean isFiring() {
        return getMournerFlag(2);
    }
    public void setIsFiring(boolean value) {
        setMournerFlag(2, value);
    }
    
	public static final int ATTACK_FIREBALLS = 0, ATTACK_SHULKER = 1, ATTACK_GHAST = 2;

    public int getCurrentAttack() {
        int i = dataManager.get(MOURNER_FLAGS);
        return i >>> 2;
    }
    
    public void setCurrentAttack(int value) {
        int i = dataManager.get(MOURNER_FLAGS);
        
        i &= 0b11;
        i |= value << 2;

        dataManager.set(MOURNER_FLAGS, (byte)(i & 255));
    }
    
    public int getRageFactor() {
    	float h = getHealth();
    	if (h < 100) return 3;
    	else if (h < 200) return 2;
    	else return 1;
    }

    /**
     * Sets the custom name tag for this entity
     */
    public void setCustomNameTag(String name)
    {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.ENTITY_WITHER_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_WITHER_DEATH;
    }

    public int getInvulTime()
    {
        return dataManager.get(INVULNERABILITY_TIME);
    }

    public void setInvulTime(int time)
    {
        dataManager.set(INVULNERABILITY_TIME, time);
    }

    class AIDoNothing extends EntityAIBase
    {
        public AIDoNothing()
        {
            this.setMutexBits(7);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return getInvulTime() > 0;
        }
    }

    //--------------------------------------------------------------------------------
    //IA and movement
    //--------------------------------------------------------------------------------

    //Charge at close players
    static class AIChargeAttack extends EntityAIBase
    {
    	private EntityMourner mourner;
        public AIChargeAttack(EntityMourner mourner)
        {
            this.setMutexBits(1);
            this.mourner = mourner;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return mourner.getAttackTarget() != null && mourner.getAttackTarget().isEntityAlive()
            		&& mourner.getDistanceSqToEntity(mourner.getAttackTarget()) <= 16.0D;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return mourner.getMoveHelper().isUpdating() && mourner.isCharging() && mourner.getAttackTarget() != null && mourner.getAttackTarget().isEntityAlive()
            		&& mourner.getDistanceSqToEntity(mourner.getAttackTarget()) <= 25.0D;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            EntityLivingBase entitylivingbase = mourner.getAttackTarget();
            Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
            mourner.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 0.5 * (mourner.getRageFactor() + 1));
            mourner.setIsCharging(true);
            mourner.setIsFiring(false);
            mourner.playSound(SoundEvents.ENTITY_VEX_CHARGE, 1.0F, 1.0F);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            mourner.setIsCharging(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = mourner.getAttackTarget();

            if (mourner.getEntityBoundingBox().grow(0.5).intersects(entitylivingbase.getEntityBoundingBox()))
            {
                mourner.attackEntityAsMob(entitylivingbase);
                mourner.swingArm(EnumHand.MAIN_HAND);
                mourner.setIsCharging(false);
            }
            else
            {
                Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
                mourner.moveHelper.setMoveTo(vec3d.x, vec3d.y - 1.0, vec3d.z, 0.5 * (mourner.getRageFactor() + 1));
            }
        }
    }

    //Fire projectiles
    static class AIRangedAttack extends EntityAIBase
    {
    	
    	private EntityMourner mourner;
        private int attackStep;
        private int attackTime;
        public AIRangedAttack(EntityMourner mourner) {
            this.setMutexBits(2);
            this.mourner = mourner;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return mourner.getAttackTarget() != null && !mourner.getMoveHelper().isUpdating() && !mourner.isCharging()
            		&& mourner.rand.nextInt(9 - mourner.getRageFactor() * 2) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return attackStep > 0 && mourner.isFiring() && mourner.getAttackTarget() != null && mourner.getAttackTarget().isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            attackStep = 2 + mourner.getRageFactor() * 3;
            attackTime = 60;
            mourner.setIsFiring(true);
            mourner.setCurrentAttack(mourner.rand.nextInt(3));
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            mourner.setIsFiring(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = mourner.getAttackTarget();
        	--attackTime;

            if (attackTime <= 0)
            {
                --attackStep;
                attackTime = 3;
            	switch (mourner.getCurrentAttack()) {
            	case ATTACK_FIREBALLS:
                    double d0 = mourner.getDistanceSqToEntity(entitylivingbase);
                	double d1 = entitylivingbase.posX - mourner.posX;
                    double d2 = entitylivingbase.getEntityBoundingBox().minY + (entitylivingbase.height / 2.0) - (mourner.posY + (mourner.height / 2.0));
                    double d3 = entitylivingbase.posZ - mourner.posZ;
                    
                    float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                    mourner.world.playEvent((EntityPlayer)null, 1018, new BlockPos((int)mourner.posX, (int)mourner.posY, (int)mourner.posZ), 0);

                    EntitySmallFireball entitysmallfireball = new EntitySmallFireball(mourner.world, mourner, d1 + mourner.getRNG().nextGaussian() * (double)f, d2, d3 + mourner.getRNG().nextGaussian() * (double)f);
                    entitysmallfireball.posY = mourner.posY + (mourner.height / 2.0) + 0.5D;
                    mourner.world.spawnEntity(entitysmallfireball);
                    break;
            	case ATTACK_SHULKER:
            		Axis axis = Axis.values()[mourner.rand.nextInt(3)];
                    EntityShulkerBullet entityshulkerbullet = new EntityShulkerBullet(mourner.world, mourner, entitylivingbase, axis);
	                entityshulkerbullet.posY += (mourner.height / 2.0) + 0.5D;
                    mourner.world.spawnEntity(entityshulkerbullet);
                    mourner.playSound(SoundEvents.ENTITY_SHULKER_SHOOT, 2.0F, (mourner.rand.nextFloat() - mourner.rand.nextFloat()) * 0.2F + 1.0F);
            		break;
            	case ATTACK_GHAST:
                    Vec3d vec3d = mourner.getLook(1.0F);
                    d2 = entitylivingbase.posX - (mourner.posX + vec3d.x * 4.0D);
                    d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + mourner.posY + (double)(mourner.height / 2.0F));
                    double d4 = entitylivingbase.posZ - (mourner.posZ + vec3d.z * 4.0D);
                    mourner.world.playEvent((EntityPlayer)null, 1016, new BlockPos(mourner), 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(mourner.world, mourner, d2, d3, d4);
                    entitylargefireball.explosionPower = mourner.getRageFactor();
                    entitylargefireball.posX = mourner.posX;
                    entitylargefireball.posY = mourner.posY + mourner.height + 0.5D;
                    entitylargefireball.posZ = mourner.posZ;
                    mourner.world.spawnEntity(entitylargefireball);
                    --attackStep;
                    attackTime = 10;
                    break;
            	}
            }

            mourner.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            super.updateTask();
        }
    }

    //MoveHelper
    static class AIMoveControl extends EntityMoveHelper
    {
        public AIMoveControl(EntityMourner mourner)
        {
            super(mourner);
        }

        public void onUpdateMoveHelper()
        {
            if (action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - entity.posX;
                double d1 = this.posY - entity.posY;
                double d2 = this.posZ - entity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = (double)MathHelper.sqrt(d3);

                if (d3 < entity.getEntityBoundingBox().getAverageEdgeLength())
                {
                    action = EntityMoveHelper.Action.WAIT;
                    entity.motionX *= 0.5D;
                    entity.motionY *= 0.5D;
                    entity.motionZ *= 0.5D;
                }
                else if (d3 >= 20 && ((EntityMourner) entity).attemptTeleportAir(posX, posY, posZ))
                {
                	entity.world.playSound((EntityPlayer)null, entity.prevPosX, entity.prevPosY, entity.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, entity.getSoundCategory(), 1.0F, 1.0F);
                    entity.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
                }
                else
                {
                    entity.motionX += d0 / d3 * 0.05D * speed;
                    entity.motionY += d1 / d3 * 0.05D * speed;
                    entity.motionZ += d2 / d3 * 0.05D * speed;

                    if (entity.getAttackTarget() == null)
                    {
                        entity.rotationYaw = -((float)MathHelper.atan2(entity.motionX, entity.motionZ)) * (180F / (float)Math.PI);
                        entity.renderYawOffset = entity.rotationYaw;
                    }
                    else
                    {
                        double d4 = entity.getAttackTarget().posX - entity.posX;
                        double d5 = entity.getAttackTarget().posZ - entity.posZ;
                        entity.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * (180F / (float)Math.PI);
                        entity.renderYawOffset = entity.rotationYaw;
                    }
                }
            }
        }
    }
    
    //Based on attemptTeleport, but allows the Mourner to teleport in the air
    protected boolean attemptTeleportAir(double x, double y, double z) {
        double d0 = this.posX;
        double d1 = this.posY;
        double d2 = this.posZ;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        boolean flag = false;
        BlockPos blockpos = new BlockPos(this);
        World world = this.world;
        Random random = this.getRNG();

        if (world.isBlockLoaded(blockpos))
        {
            this.setPositionAndUpdate(this.posX, this.posY, this.posZ);

            if (world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(this.getEntityBoundingBox())) flag = true;
        }

        if (!flag)
        {
            this.setPositionAndUpdate(d0, d1, d2);
            return false;
        }
        else
        {
            for (int j = 0; j < 128; ++j)
            {
                double d6 = (double)j / 127.0D;
                float f = (random.nextFloat() - 0.5F) * 0.2F;
                float f1 = (random.nextFloat() - 0.5F) * 0.2F;
                float f2 = (random.nextFloat() - 0.5F) * 0.2F;
                double d3 = d0 + (this.posX - d0) * d6 + (random.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double d4 = d1 + (this.posY - d1) * d6 + random.nextDouble() * (double)this.height;
                double d5 = d2 + (this.posZ - d2) * d6 + (random.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                world.spawnParticle(EnumParticleTypes.PORTAL, d3, d4, d5, (double)f, (double)f1, (double)f2);
            }

            if (this instanceof EntityCreature)
            {
                ((EntityCreature)this).getNavigator().clearPathEntity();
            }

            return true;
        }
    }

    //Move around
    static class AIMoveRandom extends EntityAIBase
    {
    	private EntityMourner mourner;
        public AIMoveRandom(EntityMourner mourner)
        {
            this.setMutexBits(1);
            this.mourner = mourner;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return !mourner.getMoveHelper().isUpdating() && mourner.rand.nextInt(7) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            BlockPos blockpos;
            if (mourner.getAttackTarget() != null && mourner.getAttackTarget().isEntityAlive()) blockpos = new BlockPos(mourner.getAttackTarget());
            else blockpos = new BlockPos(mourner);
            for (int i = 0; i < 3; ++i)
            {
                BlockPos blockpos1 = blockpos.add(mourner.rand.nextInt(15) - 7, mourner.rand.nextInt(11) - 5, mourner.rand.nextInt(15) - 7);

                if (mourner.world.isAirBlock(blockpos1))
                {
                    mourner.moveHelper.setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25 * mourner.getRageFactor());

                    if (mourner.getAttackTarget() == null)
                    {
                        mourner.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }

                    break;
                }
            }
        }
    }
    
}
