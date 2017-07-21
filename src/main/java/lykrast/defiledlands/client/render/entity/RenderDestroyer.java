package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.client.model.ModelDestroyer;
import lykrast.defiledlands.client.render.entity.RenderScuttler.Factory;
import lykrast.defiledlands.client.render.entity.layers.LayerScuttlerEyes;
import lykrast.defiledlands.common.entity.boss.EntityDestroyer;
import lykrast.defiledlands.common.entity.monster.EntityScuttler;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDestroyer extends RenderLiving<EntityDestroyer> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(DefiledLands.MODID, "textures/entity/the_destroyer.png"),
			TEXTURES_EXPLOSION = new ResourceLocation(DefiledLands.MODID, "textures/entity/the_destroyer_explosion.png");
	
    public static final Factory FACTORY = new Factory();

    public RenderDestroyer(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDestroyer(0.0F), 0.5F);
    }
	
    protected ResourceLocation getEntityTexture(EntityDestroyer entity)
    {
        return TEXTURES;
    }

    protected float getDeathMaxRotation(EntityDestroyer entityLivingBaseIn)
    {
        return 0.0F;
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityDestroyer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
    	if (entitylivingbaseIn.deathTime > 0)
        {
            float f = (float)entitylivingbaseIn.deathTime / 200.0F;
            GlStateManager.depthFunc(515);
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(516, f);
            this.bindTexture(TEXTURES_EXPLOSION);
            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.depthFunc(514);
        }

        this.bindEntityTexture(entitylivingbaseIn);
        this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        if (entitylivingbaseIn.hurtTime > 0)
        {
            GlStateManager.depthFunc(514);
            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.depthFunc(515);
        }
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
