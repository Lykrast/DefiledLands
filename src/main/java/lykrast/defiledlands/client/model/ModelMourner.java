package lykrast.defiledlands.client.model;

import lykrast.defiledlands.common.entity.boss.EntityMourner;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;

public class ModelMourner extends ModelPlayer {

	public ModelMourner(float modelSize) {
		super(modelSize, true);
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        EntityMourner mourner = (EntityMourner)entityIn;

        if (mourner.isFiring())
        {
        	switch (mourner.getCurrentAttack()) {
        	case EntityMourner.ATTACK_FIREBALLS:
                bipedRightArm.rotateAngleY = -0.1F + bipedHead.rotateAngleY;
                bipedRightArm.rotateAngleX = -(float)Math.PI / 2 + bipedHead.rotateAngleX;
        		break;
        	case EntityMourner.ATTACK_SHULKER:
                bipedLeftArm.rotateAngleY = 0.1F + bipedHead.rotateAngleY;
                bipedLeftArm.rotateAngleX = -(float)Math.PI / 2 + bipedHead.rotateAngleX;
	    		break;
        	case EntityMourner.ATTACK_GHAST:
        		bipedRightArm.rotateAngleX = (float)Math.PI;
        		bipedLeftArm.rotateAngleX = (float)Math.PI;
        		break;
        	}
        }
    }

}
