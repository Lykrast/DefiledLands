package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.common.entity.projectile.EntityRavagerProjectile;
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
public class RenderRavagerProjectile extends RenderSnowball<EntityRavagerProjectile> {
	
    public static final Factory FACTORY = new Factory();

	public RenderRavagerProjectile(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	@Override
	public ItemStack getStackToRender(EntityRavagerProjectile entityIn) {
		return new ItemStack(ModItems.pelletUmbrium);
	}

    public static class Factory implements IRenderFactory<EntityRavagerProjectile> {

        @Override
        public Render<? super EntityRavagerProjectile> createRenderFor(RenderManager manager) {
            return new RenderRavagerProjectile(manager, ModItems.pelletUmbrium, Minecraft.getMinecraft().getRenderItem());
        }

    }

}
