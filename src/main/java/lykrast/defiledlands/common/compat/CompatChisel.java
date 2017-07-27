package lykrast.defiledlands.common.compat;

import lykrast.defiledlands.common.block.BlockStoneDefiledDecoration;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class CompatChisel extends ModCompat {

	@Override
	public void init()
	{
		FMLInterModComms.sendMessage("chisel", "variation:add", "stone_defiled|defiledlands:stone_defiled|0");
		int var = BlockStoneDefiledDecoration.Variants.values().length;
		for (int i=0;i<var;i++)
		FMLInterModComms.sendMessage("chisel", "variation:add", "stone_defiled|defiledlands:stone_defiled_decoration|" + i);
	}
}
