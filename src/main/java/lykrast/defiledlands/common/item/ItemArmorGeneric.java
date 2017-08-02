package lykrast.defiledlands.common.item;

import javax.annotation.Nullable;

import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorGeneric extends ItemArmor {
	private static final String PATH = DefiledLands.MODID + ":textures/armor/";
	protected String texture;

	public ItemArmorGeneric(String name, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, 0, equipmentSlotIn);
		texture = PATH + name;
		if (equipmentSlotIn == EntityEquipmentSlot.LEGS) texture += "_2.png";
		else texture += "_1.png";
	}
	
    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return texture;
    }

}
