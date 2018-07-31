package lykrast.defiledlands.common.item;

import lykrast.defiledlands.common.entity.projectile.EntityRavagerProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IPellet {
	EntityRavagerProjectile applyAttributes(EntityRavagerProjectile projectile, ItemStack stack);
	default void onHit(EntityLivingBase target, EntityLivingBase source, ItemStack pellet) {}
}
