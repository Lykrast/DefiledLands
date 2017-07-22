package lykrast.defiledlands.common.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;

public class BlockDoorGeneric extends BlockDoor implements ICustomItemBlock {

	public BlockDoorGeneric(Material material, SoundType soundType, float hardness, float resistance) {
		super(material);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
	}
	
	public BlockDoorGeneric(Material material, SoundType soundType, float hardness, float resistance, String tool, int harvestLevel) {
		this(material, soundType, hardness, resistance);
		this.setHarvestLevel(tool, harvestLevel);
	}

	@Override
	public ItemBlock getItemBlock() {
		return null;
	}

}
