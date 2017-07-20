package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.client.model.ModelDestroyer;
import lykrast.defiledlands.client.render.entity.RenderScuttler.Factory;
import lykrast.defiledlands.client.render.entity.layers.LayerScuttlerEyes;
import lykrast.defiledlands.common.entity.boss.EntityDestroyer;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDestroyer extends RenderLiving<EntityDestroyer> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(DefiledLands.MODID, "textures/entity/the_destroyer.png");
	
    public static final Factory FACTORY = new Factory();

    public RenderDestroyer(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDestroyer(0.0F), 0.5F);
    }
	
    protected ResourceLocation getEntityTexture(EntityDestroyer entity)
    {
        return TEXTURES;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityDestroyer entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
    }

    public static class Factory implements IRenderFactory<EntityDestroyer> {

        @Override
        public Render<? super EntityDestroyer> createRenderFor(RenderManager manager) {
            return new RenderDestroyer(manager);
        }

    }

}
