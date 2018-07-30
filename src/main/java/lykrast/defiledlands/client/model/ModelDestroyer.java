package lykrast.defiledlands.client.model;

import lykrast.defiledlands.common.entity.boss.EntityDestroyer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;

public class ModelDestroyer extends ModelPlayer {

	public ModelDestroyer(float modelSize) {
		super(modelSize, true);
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        EntityDestroyer destroyer = (EntityDestroyer)entityIn;
        
        if (destroyer.isLeaping()) {
            bipedRightLeg.rotateAngleX += ((float)Math.PI / 5F);
            bipedLeftLeg.rotateAngleX += ((float)Math.PI / 5F);
            bipedRightArm.rotateAngleX = 3.7699115F;
            bipedLeftArm.rotateAngleX = 3.7699115F;
        }
    }

}
