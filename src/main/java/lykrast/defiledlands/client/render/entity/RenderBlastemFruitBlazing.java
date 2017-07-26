package lykrast.defiledlands.client.render.entity;

import javax.annotation.Nonnull;

import lykrast.defiledlands.client.render.entity.RenderScuttler.Factory;
import lykrast.defiledlands.common.entity.projectile.EntityBlastemFruitBlazing;
import lykrast.defiledlands.common.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlastemFruitBlazing extends RenderSnowball<EntityBlastemFruitBlazing> {
	
    public static final Factory FACTORY = new Factory();

	public RenderBlastemFruitBlazing(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	@Override
	public ItemStack getStackToRender(EntityBlastemFruitBlazing entityIn) {
		return new ItemStack(ModItems.blastemFruitBlazing);
	}

    public static class Factory implements IRenderFactory<EntityBlastemFruitBlazing> {

        @Override
        public Render<? super EntityBlastemFruitBlazing> createRenderFor(RenderManager manager) {
            return new RenderBlastemFruitBlazing(manager, ModItems.blastemFruitBlazing, Minecraft.getMinecraft().getRenderItem());
        }

    }

}
