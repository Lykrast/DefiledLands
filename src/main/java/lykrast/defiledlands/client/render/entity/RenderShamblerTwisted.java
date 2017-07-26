package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.client.model.ModelShambler;
import lykrast.defiledlands.client.render.entity.layers.LayerScuttlerEyes;
import lykrast.defiledlands.client.render.entity.layers.LayerShamblerTwistedEyes;
import lykrast.defiledlands.common.entity.monster.EntityShamblerTwisted;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderShamblerTwisted extends RenderLiving<EntityShamblerTwisted> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(DefiledLands.MODID, "textures/entity/shambler_twisted.png");
	
    public static final Factory FACTORY = new Factory();
	
	public RenderShamblerTwisted(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelShambler(0.0F), 0.5F);
        this.addLayer(new LayerShamblerTwistedEyes(this));
    }

    public ModelShambler getMainModel()
    {
        return (ModelShambler)super.getMainModel();
    }
    
    public void doRender(EntityShamblerTwisted entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	ModelShambler modelenderman = this.getMainModel();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	
    protected ResourceLocation getEntityTexture(EntityShamblerTwisted entity)
    {
        return TEXTURES;
    }

    protected void applyRotations(EntityShamblerTwisted entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	rotationYaw += (float)(Math.cos((double)entityLiving.ticksExisted * 3.25D) * Math.PI * 0.75D);
    	super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<EntityShamblerTwisted> {

        @Override
        public Render<? super EntityShamblerTwisted> createRenderFor(RenderManager manager) {
            return new RenderShamblerTwisted(manager);
        }

    }

}
