package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.client.model.ModelDestroyer;
import lykrast.defiledlands.common.entity.boss.EntityMourner;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMourner extends RenderLiving<EntityMourner> {
	
	private static final ResourceLocation[] TEXTURES = {
			new ResourceLocation(DefiledLands.MODID, "textures/entity/the_mourner.png"),
			new ResourceLocation(DefiledLands.MODID, "textures/entity/the_mourner_rage1.png"),
			new ResourceLocation(DefiledLands.MODID, "textures/entity/the_mourner_rage2.png")};
	private static final ResourceLocation TEXTURES_EXPLOSION = new ResourceLocation(DefiledLands.MODID, "textures/entity/the_mourner_explosion.png");

    public RenderMourner(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDestroyer(0.0F), 0.5F);
    }
	
    protected ResourceLocation getEntityTexture(EntityMourner entity)
    {
        return TEXTURES[entity.getRageFactor()-1];
    }

    protected float getDeathMaxRotation(EntityMourner entityLivingBaseIn)
    {
        return 0.0F;
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityMourner entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
    	if (entitylivingbaseIn.deathTime > 0 || entitylivingbaseIn.getInvulTime() > 0)
        {
    		float f;
    		if (entitylivingbaseIn.deathTime > 0)
    		{
                f = (float)entitylivingbaseIn.deathTime / 200.0F;
    		}
    		else
    		{
    			f = (float)entitylivingbaseIn.getInvulTime() / 200.0F;
    		}
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
        }
        
        GlStateManager.depthFunc(515);
    }

    protected void applyRotations(EntityMourner entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	if (entityLiving.deathTime > 0)
    	{
    		double d = (double)entityLiving.deathTime / 200.0D;
        	rotationYaw += (float)(Math.cos((double)entityLiving.ticksExisted * 3.25D) * Math.PI * d * 5.0D);
    	}
    	else
    	{
    		int r = entityLiving.getRageFactor();
    		if (r > 1) rotationYaw += (float)(Math.cos((double)entityLiving.ticksExisted * 3.25D) * Math.PI * (r-1));
    	}
    	super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityMourner entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
    }

}
