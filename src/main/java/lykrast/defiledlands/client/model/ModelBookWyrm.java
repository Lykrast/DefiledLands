package lykrast.defiledlands.client.model;

import lykrast.defiledlands.common.entity.passive.EntityBookWyrm;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBookWyrm extends ModelQuadruped {
    
	public ModelBookWyrm()
	{
		this(0.0F);
	}
	
	public ModelBookWyrm(float scale)
	{
		super(6, scale);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 6, scale);
        this.head.setRotationPoint(0.0F, 17.0F, -6.0F);
        this.body = new ModelRenderer(this, 32, 10);
        this.body.addBox(-5.0F, -10.0F, -2.0F, 10, 16, 6, scale);
        this.body.setRotationPoint(0.0F, 20.0F, 3.0F);
        this.leg1 = new ModelRenderer(this, 0, 22);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
        this.leg1.setRotationPoint(-7.0F, 18.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 22);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
        this.leg2.setRotationPoint(7.0F, 18.0F, 7.0F);
        this.leg2.mirror = true;
        this.leg3 = new ModelRenderer(this, 0, 22);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
        this.leg3.setRotationPoint(-7.0F, 18.0F, -5.0F);
        this.leg4 = new ModelRenderer(this, 0, 22);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
        this.leg4.setRotationPoint(7.0F, 18.0F, -5.0F);
        this.leg4.mirror = true;
        this.head.setTextureOffset(0, 12).addBox(-2.0F, -1.0F, -12.0F, 4, 3, 6, scale);
        this.childYOffset = 4.0F;
	}

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	
    	int i = ((EntityBookWyrm)entityIn).digestTimer;
    	if (i > 0)
    		this.body.rotateAngleZ = (float)(Math.sin(i/20.0D)*(Math.PI / 32));
    }

}
