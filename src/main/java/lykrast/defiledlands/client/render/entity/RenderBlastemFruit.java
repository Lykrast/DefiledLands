package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.common.entity.projectile.EntityBlastemFruit;
import lykrast.defiledlands.common.init.ModItems;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlastemFruit extends RenderSnowball<EntityBlastemFruit> {
	
	public RenderBlastemFruit(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	@Override
	public ItemStack getStackToRender(EntityBlastemFruit entityIn) {
		return new ItemStack(ModItems.blastemFruit);
	}

}
