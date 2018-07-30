package lykrast.defiledlands.common.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICustomStateMapper {
	@SideOnly(Side.CLIENT)
	void setCustomStateMapper();
}
