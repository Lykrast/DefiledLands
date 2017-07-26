package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.client.render.entity.RenderShambler.Factory;
import lykrast.defiledlands.client.render.entity.layers.LayerScuttlerEyes;
import lykrast.defiledlands.common.entity.monster.EntityScuttler;
import lykrast.defiledlands.common.entity.monster.EntityShambler;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScuttler extends RenderLiving<EntityScuttler> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(DefiledLands.MODID, "textures/entity/scuttler.png");
	
    public static final Factory FACTORY = new Factory();

    public RenderScuttler(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSpider(), 1.0F);
        this.addLayer(new LayerScuttlerEyes(this));
    }

    protected float getDeathMaxRotation(EntityScuttler entityLivingBaseIn)
    {
        return 180.0F;
    }
	
    protected ResourceLocation getEntityTexture(EntityScuttler entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityScuttler> {

        @Override
        public Render<? super EntityScuttler> createRenderFor(RenderManager manager) {
            return new RenderScuttler(manager);
        }

    }

}
