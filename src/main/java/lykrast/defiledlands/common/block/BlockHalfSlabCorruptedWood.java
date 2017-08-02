package lykrast.defiledlands.common.block;

import lykrast.defiledlands.common.init.ModBlocks;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHalfSlabCorruptedWood extends BlockSlabCorruptedWood implements ICustomModel {

    public boolean isDouble()
    {
        return false;
    }
    
	public ItemBlock getItemBlock()
	{
		return new ItemSlab(this, this, (BlockSlab) ModBlocks.slabDoubleTenebra);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		for (Variant v : Variant.values()) 
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), getMetaFromState(blockState.getBaseState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM).withProperty(VARIANT, v)), 
					new ModelResourceLocation(getRegistryName() + "_" + v, "inventory"));
	}

}
