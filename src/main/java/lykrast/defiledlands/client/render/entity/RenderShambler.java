package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.client.model.ModelShambler;
import lykrast.defiledlands.common.entity.monster.EntityShambler;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderShambler extends RenderLiving<EntityShambler> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(DefiledLands.MODID, "textures/entity/shambler.png");
	
    public static final Factory FACTORY = new Factory();
	
	public RenderShambler(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelShambler(0.0F), 0.5F);
    }

    public ModelShambler getMainModel()
    {
        return (ModelShambler)super.getMainModel();
    }
    
    public void doRender(EntityShambler entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	ModelShambler modelenderman = this.getMainModel();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	
    protected ResourceLocation getEntityTexture(EntityShambler entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityShambler> {

        @Override
        public Render<? super EntityShambler> createRenderFor(RenderManager manager) {
            return new RenderShambler(manager);
        }

    }

}
