package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.client.render.entity.layers.LayerSlimeDefiledGel;
import lykrast.defiledlands.common.entity.monster.EntitySlimeDefiled;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSlimeDefiled extends RenderLiving<EntitySlimeDefiled> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(DefiledLands.MODID, "textures/entity/slime_defiled.png");
	
    public static final Factory FACTORY = new Factory();

    public RenderSlimeDefiled(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSlime(16), 0.25F);
        this.addLayer(new LayerSlimeDefiledGel(this));
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntitySlimeDefiled entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F * (float)entity.getSlimeSize();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntitySlimeDefiled entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.999F, 0.999F, 0.999F);
        float f1 = (float)entitylivingbaseIn.getSlimeSize();
        float f2 = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }
	
    protected ResourceLocation getEntityTexture(EntitySlimeDefiled entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntitySlimeDefiled> {

        @Override
        public Render<? super EntitySlimeDefiled> createRenderFor(RenderManager manager) {
            return new RenderSlimeDefiled(manager);
        }

    }

}
