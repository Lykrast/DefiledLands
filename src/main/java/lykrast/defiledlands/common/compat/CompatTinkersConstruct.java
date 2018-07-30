package lykrast.defiledlands.common.compat;

import lykrast.defiledlands.common.block.BlockBaseFluid;
import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.init.ModItems;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.utils.HarvestLevels;

public class CompatTinkersConstruct extends ModCompat {
	// Mostly inspired from how IE does it
	// https://github.com/BluSunrize/ImmersiveEngineering/blob/master/src/main/java/blusunrize/immersiveengineering/common/util/compat/TConstructHelper.java
	
	public static final Material UMBRIUM = new Material("umbrium", 0xa51acc);
	public static final Fluid MOLTEN_UMBRIUM = sendFluidForMelting("umbrium", registerFluid(new FluidColouredMetal("umbrium", 0xa51acc, 800)));
	
	@Override
	public void preInit() {
		ModBlocks.registerBlock(new BlockBaseFluid(MOLTEN_UMBRIUM, net.minecraft.block.material.Material.LAVA), "molten_umbrium", null);
		
		TinkerRegistry.addMaterialStats(UMBRIUM, 
				new HeadMaterialStats(246, 5.0F, 4.0F, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.0F, 72),
                new ExtraMaterialStats(60));
		TinkerRegistry.integrate(UMBRIUM, MOLTEN_UMBRIUM, "Umbrium").toolforge().preInit();
	}
	
	@Override
	public void init() {
		UMBRIUM.setCastable(true);
		UMBRIUM.addCommonItems("Umbrium");
		UMBRIUM.setRepresentativeItem(ModItems.umbriumIngot);
	}
	
	public static Fluid registerFluid(Fluid fluid) {
		FluidRegistry.addBucketForFluid(fluid);
		FluidRegistry.registerFluid(fluid);
		return fluid;
	}
	
	public static Fluid sendFluidForMelting(String ore, Fluid fluid) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("fluid", fluid.getName());
		tag.setString("ore", ore);
		tag.setBoolean("toolforge", true);
		FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);
		return fluid;
	}
	
	public static class FluidColouredMetal extends Fluid {
		public static ResourceLocation ICON_MetalStill = new ResourceLocation("tconstruct:blocks/fluids/molten_metal");
		public static ResourceLocation ICON_MetalFlowing = new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow");

		int colour;

		public FluidColouredMetal(String name, int colour, int temp) {
			super(name, ICON_MetalStill, ICON_MetalFlowing);
			this.colour = colour;
			this.setTemperature(temp);
			this.setDensity(2000);
			this.setViscosity(10000);
		}

		@Override
		public int getColor() {
			return colour|0xff000000;
		}
	}
}
