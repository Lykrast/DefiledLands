package lykrast.defiledlands.common.compat;

import net.minecraftforge.fml.common.event.FMLInterModComms;

public class CompatChisel extends ModCompat {

	@Override
	public void init()
	{
		FMLInterModComms.sendMessage("chisel", "variation:add", "stone_defiled|defiledlands:stone_defiled|0");
		FMLInterModComms.sendMessage("chisel", "variation:add", "stone_defiled|defiledlands:stone_defiled_decoration|0");
	}
}
