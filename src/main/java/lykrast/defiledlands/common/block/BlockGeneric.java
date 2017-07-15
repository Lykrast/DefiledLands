package lykrast.defiledlands.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockGeneric extends Block {
	
	public BlockGeneric() {
        this(Material.ROCK, SoundType.STONE);
    }
	
	public BlockGeneric(Material material, SoundType soundType) {
		this(Material.ROCK, SoundType.STONE, 1.0F, 5.0F);
	}
	
	public BlockGeneric(Material material, SoundType soundType, float hardness, float resistance) {
		super(material);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
	}
	
	public BlockGeneric(Material material, SoundType soundType, float hardness, float resistance, String tool, int harvestLevel) {
		this(material, soundType, hardness, resistance);
		this.setHarvestLevel(tool, harvestLevel);
	}

}
