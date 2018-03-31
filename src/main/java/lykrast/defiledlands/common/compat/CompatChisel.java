package lykrast.defiledlands.common.compat;

import lykrast.defiledlands.common.block.BlockStoneDefiledDecoration;
import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class CompatChisel extends ModCompat {

	@Override
	public void init()
	{
		addVariation("stone_defiled", ModBlocks.stoneDefiled, 0);
		int var = BlockStoneDefiledDecoration.Variants.values().length;
		for (int i=0;i<var;i++) addVariation("stone_defiled", ModBlocks.stoneDefiledDecoration, i);
	}
	
	//Got that from Immersive Engineering
	//https://github.com/BluSunrize/ImmersiveEngineering/blob/master/src/main/java/blusunrize/immersiveengineering/common/util/compat/ChiselHelper.java
	private void addVariation(String group, Block block, int meta)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("group", group);
		tag.setString("block", block.getRegistryName().toString());
		tag.setInteger("meta", meta);
		FMLInterModComms.sendMessage("chisel", "add_variation", tag);
	}
}
