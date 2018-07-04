package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.common.entity.projectile.EntityRavagerProjectile;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRavagerProjectile extends RenderSnowball<EntityRavagerProjectile> {

	public RenderRavagerProjectile(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	@Override
	public ItemStack getStackToRender(EntityRavagerProjectile entityIn) {
		return entityIn.getItem();
	}

}
