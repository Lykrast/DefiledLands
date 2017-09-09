package lykrast.defiledlands.common.block;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGravelCorrupted extends BlockFallingCorrupted {
	
	public BlockGravelCorrupted(Material material, SoundType soundType, float hardness, float resistance) {
		super(material, soundType, hardness, resistance, "shovel", 0);
	}
	
    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (fortune > 3)
        {
            fortune = 3;
        }

        return rand.nextInt(10 - fortune * 3) == 0 ? Items.FLINT : super.getItemDropped(state, rand, fortune);
    }

}
