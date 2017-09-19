package lykrast.defiledlands.common.entity.monster;

import javax.annotation.Nullable;

import lykrast.defiledlands.common.entity.IEntityDefiled;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntitySlimeDefiled extends EntitySlime implements IEntityDefiled {
    public static final ResourceLocation LOOT = new ResourceLocation(DefiledLands.MODID, "entities/slime_defiled");

    public EntitySlimeDefiled(World worldIn)
    {
        super(worldIn);
    }
    
    public void setSlimeSize(int size, boolean resetHealth)
    {
    	super.setSlimeSize(size, resetHealth);
    }

    /**
     * Gets the amount of time the slime needs to wait between jumps.
     */
    protected int getJumpDelay()
    {
        return this.rand.nextInt(10) + 5;
    }

    /**
     * Indicates weather the slime is able to damage the player (based upon the slime's size)
     */
    protected boolean canDamagePlayer()
    {
        return true;
    }

    /**
     * Gets the amount of damage dealt to the player when "attacked" by the slime.
     */
    protected int getAttackStrength()
    {
        return this.getSlimeSize() * 2;
    }

    protected EntitySlimeDefiled createInstance()
    {
        return new EntitySlimeDefiled(this.world);
    }

    public void fall(float distance, float damageMultiplier)
    {
    }
    
    public int getMaxFallHeight()
    {
        return Integer.MAX_VALUE;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return this.getSlimeSize() == 1 ? LOOT : LootTableList.EMPTY;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump()
    {
        this.motionY = 0.6D;
        this.isAirBorne = true;
    }

}
