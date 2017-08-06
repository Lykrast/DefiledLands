package lykrast.defiledlands.common.entity.projectile;

import lykrast.defiledlands.common.init.ModItems;
import lykrast.defiledlands.common.init.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRavagerProjectile extends EntitySmallFireball {
	private static final DataParameter<ItemStack> ITEM = EntityDataManager.<ItemStack>createKey(EntityPotion.class, DataSerializers.ITEM_STACK);
	private float damage, speed;

	public EntityRavagerProjectile(World worldIn) {
		super(worldIn);
		this.damage = 12.0F;
		this.speed = 1.0F;
	}

	public EntityRavagerProjectile(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, float speed) {
		super(worldIn, shooter, accelX, accelY, accelZ);
        double d0 = (double)MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.2D * (double)speed;
        this.accelerationY = accelY / d0 * 0.2D * (double)speed;
        this.accelerationZ = accelZ / d0 * 0.2D * (double)speed;
        this.damage = 12.0F;
        float f = 1.0F - (0.05F / speed);
		this.speed = f;
	}

	public EntityRavagerProjectile(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ, float speed) {
		super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.accelerationX *= (double)speed;
        this.accelerationY *= (double)speed;
        this.accelerationZ *= (double)speed;
		this.damage = 12.0F;
        float f = 1.0F - (0.05F / speed);
		this.speed = f;
	}

    protected void entityInit()
    {
    	super.entityInit();
        this.getDataManager().register(ITEM, ItemStack.EMPTY);
    }
	
	/**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
            	boolean flag = result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), getDamage());

            	if (flag)
            	{
            		this.applyEnchantments(this.shootingEntity, result.entityHit);
            		if (result.entityHit instanceof EntityLivingBase)
            		{
            			EntityLivingBase living = (EntityLivingBase)result.entityHit;
            			if (getItem().getItem() == ModItems.pelletSpiked)
            			{
            				int i = 0;
            				if (living.isPotionActive(ModPotions.bleeding))
            				{
            					i = living.getActivePotionEffect(ModPotions.bleeding).getAmplifier() + 1;
            					i = Math.min(i, 255);
            				}
            				living.addPotionEffect(new PotionEffect(ModPotions.bleeding, 200, i));
            			}
            			else if (getItem().getItem() == ModItems.pelletRavaging)
            				living.addPotionEffect(new PotionEffect(ModPotions.vulnerability, 200));
            		}
            	}
            }

            this.setDead();
        }
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	compound.setFloat("Damage", damage);
    	compound.setFloat("Speed", speed);

        ItemStack itemstack = this.getItem();

        if (!itemstack.isEmpty())
        {
            compound.setTag("Item", itemstack.writeToNBT(new NBTTagCompound()));
        }
    }
    
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
    	damage = compound.getFloat("Damage");
    	speed = compound.getFloat("Speed");

        ItemStack itemstack = new ItemStack(compound.getCompoundTag("Item"));

        if (itemstack.isEmpty())
        {
            this.setDead();
        }
        else
        {
            this.setItem(itemstack);
        }
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor()
    {
        return getSpeed();
    }

    public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public ItemStack getItem()
	{
		return (ItemStack)this.getDataManager().get(ITEM);
	}

    public void setItem(ItemStack stack)
    {
        this.getDataManager().set(ITEM, stack);
        this.getDataManager().setDirty(ITEM);
    }

	protected boolean isFireballFiery()
    {
        return false;
    }

}
