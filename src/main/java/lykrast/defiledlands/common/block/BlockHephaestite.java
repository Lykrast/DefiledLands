package lykrast.defiledlands.common.block;

import lykrast.defiledlands.common.item.ItemBlockFuel;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHephaestite extends BlockCorrupted implements ICustomItemBlock {
    
	public BlockHephaestite(float hardness, float resistance) {
		super(Material.ROCK, SoundType.STONE, hardness, resistance, "pickaxe", 0);
		this.setLightLevel(1.0F);
	}

    /**
     * Called when the given entity walks on this Block
     */
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn))
        {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 6.0F);
            entityIn.setFire(9);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

	@Override
	public ItemBlock getItemBlock() {
		return new ItemBlockFuel(this, 16000);
	}

}
