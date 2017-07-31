package lykrast.defiledlands.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBlastemFruit extends EntityThrowable {
	protected float damage, explosion;
	protected boolean destructive;

	public EntityBlastemFruit(World worldIn)
    {
        super(worldIn);
        damage = 7.0F;
        explosion = 1.0F;
        destructive = true;
    }

    public EntityBlastemFruit(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
        damage = 7.0F;
        explosion = 1.0F;
        destructive = true;
    }

    public EntityBlastemFruit(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        setPosition(x, y, z);
    }
    
    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
        }

        if (!this.world.isRemote)
        {
            this.world.newExplosion((Entity)null, this.posX, this.posY, this.posZ, explosion, false, destructive);
            this.setDead();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	compound.setFloat("damage", damage);
    	compound.setFloat("explosion", explosion);
    	compound.setBoolean("destructive", destructive);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);

        if (compound.hasKey("damage", 99))
        {
            this.damage = compound.getFloat("damage");
        }
        if (compound.hasKey("explosion", 99))
        {
            this.explosion = compound.getFloat("explosion");
        }
        if (compound.hasKey("destructive", 99))
        {
            this.destructive = compound.getBoolean("destructive");
        }
    }

    public void setDamage(float damage)
    {
        this.damage = damage;
    }

    public float getDamage()
    {
        return damage;
    }

    public void setExplosionStrength(float explosion)
    {
        this.explosion = explosion;
    }

    public float getExplosionStrength()
    {
        return explosion;
    }

    public void setDestructive(boolean destructive)
    {
        this.destructive = destructive;
    }

    public boolean isDestructive()
    {
        return destructive;
    }

}
