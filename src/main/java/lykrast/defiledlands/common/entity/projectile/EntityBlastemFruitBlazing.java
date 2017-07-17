package lykrast.defiledlands.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBlastemFruitBlazing extends EntityThrowable {

	public EntityBlastemFruitBlazing(World worldIn)
    {
        super(worldIn);
    }

    public EntityBlastemFruitBlazing(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityBlastemFruitBlazing(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 10.0F);
            result.entityHit.setFire(8);
        }

        if (!this.world.isRemote)
        {
            this.world.newExplosion((Entity)null, this.posX, this.posY, this.posZ, 1.5F, true, true);
            this.setDead();
        }
    }

}
