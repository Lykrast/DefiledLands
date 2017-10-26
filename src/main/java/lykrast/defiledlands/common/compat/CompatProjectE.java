package lykrast.defiledlands.common.compat;

import lykrast.defiledlands.common.block.BlockGrassCorrupted;
import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.init.ModItems;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IEMCProxy;
import moze_intel.projecte.api.proxy.ITransmutationProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class CompatProjectE extends ModCompat {
	// https://github.com/sinkillerj/ProjectE/tree/master/src/main/java/moze_intel/projecte/api
	
	@Override
	public void init()
	{
		// EMC values
		IEMCProxy emc = ProjectEAPI.getEMCProxy();
		
		// Blocks
		emc.registerCustomEMC("stoneDefiled", 1);
		emc.registerCustomEMC("dirtDefiled", 1);
		emc.registerCustomEMC(new ItemStack(ModBlocks.grassDefiled), 2);
		emc.registerCustomEMC("sandDefiled", 1);
		emc.registerCustomEMC("gravelDefiled", 4);
		
		// Plants
		emc.registerCustomEMC(new ItemStack(ModBlocks.vilespine), 32);
		emc.registerCustomEMC(new ItemStack(ModBlocks.blastem), 32);
		emc.registerCustomEMC(new ItemStack(ModItems.blastemFruit), 64);
		emc.registerCustomEMC(new ItemStack(ModBlocks.scuronotte), 32);
		
		// Ores
		emc.registerCustomEMC("gemHephaestite", 128);
		emc.registerCustomEMC("ingotUmbrium", 256);
		emc.registerCustomEMC("gemScarlite", 8192);
		
		// Mob drops
		emc.registerCustomEMC(new ItemStack(ModItems.scuttlerEye), 128);
		emc.registerCustomEMC(new ItemStack(ModItems.blackHeart), 128);
		emc.registerCustomEMC(new ItemStack(ModItems.foulSlime), 32);
		emc.registerCustomEMC(new ItemStack(ModItems.bookWyrmRaw), 64);
		emc.registerCustomEMC(new ItemStack(ModItems.bookWyrmScale), 64);
		emc.registerCustomEMC(new ItemStack(ModItems.bookWyrmScaleGolden), 1024);
		emc.registerCustomEMC("essenceDestroyer", 16384);
	}
	
	@Override
	public void postInit()
	{
		// World Transmutation
		ITransmutationProxy transmute = ProjectEAPI.getTransmutationProxy();
		
		IBlockState dirt = ModBlocks.dirtDefiled.getDefaultState(),
				sand = ModBlocks.sandDefiled.getDefaultState(), 
				stone = ModBlocks.stoneDefiled.getDefaultState();
		transmute.registerWorldTransmutation(dirt, sand, stone);
		transmute.registerWorldTransmutation(sand, stone, dirt);
		transmute.registerWorldTransmutation(stone, dirt, sand);
		
		transmute.registerWorldTransmutation(ModBlocks.grassDefiled.getDefaultState().withProperty(BlockGrassCorrupted.SNOWY, Boolean.valueOf(false)), sand, stone);
		transmute.registerWorldTransmutation(ModBlocks.grassDefiled.getDefaultState().withProperty(BlockGrassCorrupted.SNOWY, Boolean.valueOf(true)), sand, stone);
		

		IBlockState sandstone = ModBlocks.sandstoneDefiled.getDefaultState(),
				gravel = ModBlocks.gravelDefiled.getDefaultState();
		transmute.registerWorldTransmutation(sandstone, gravel, null);
		transmute.registerWorldTransmutation(gravel, sandstone, null);
	}
}
