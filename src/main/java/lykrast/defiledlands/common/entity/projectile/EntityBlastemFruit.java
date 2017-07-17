package lykrast.defiledlands.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBlastemFruit extends EntityThrowable {

	public EntityBlastemFruit(World worldIn)
    {
        super(worldIn);
    }

    public EntityBlastemFruit(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityBlastemFruit(World worldIn, double x, double y, double z)
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
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 7.0F);
        }

        if (!this.world.isRemote)
        {
            this.world.newExplosion((Entity)null, this.posX, this.posY, this.posZ, 1.0F, false, true);
            this.setDead();
        }
    }

}
