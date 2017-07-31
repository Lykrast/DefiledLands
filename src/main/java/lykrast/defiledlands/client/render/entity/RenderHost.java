package lykrast.defiledlands.client.render.entity;

import lykrast.defiledlands.common.entity.monster.EntityHost;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHost extends RenderLiving<EntityHost> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(DefiledLands.MODID, "textures/entity/host.png");
    public static final Factory FACTORY = new Factory();
	
	public RenderHost(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelZombie(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
	}
	
    protected ResourceLocation getEntityTexture(EntityHost entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityHost> {

        @Override
        public Render<? super EntityHost> createRenderFor(RenderManager manager) {
            return new RenderHost(manager);
        }

    }

}
