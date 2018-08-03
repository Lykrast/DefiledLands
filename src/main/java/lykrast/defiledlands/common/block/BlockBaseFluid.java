package lykrast.defiledlands.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBaseFluid extends BlockFluidClassic implements ICustomStateMapper {

	public BlockBaseFluid(Fluid fluid, Material material) {
		super(fluid, material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setCustomStateMapper() {
		ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(LEVEL).build());
	}

}
