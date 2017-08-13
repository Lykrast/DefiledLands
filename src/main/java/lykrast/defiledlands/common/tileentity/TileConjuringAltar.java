package lykrast.defiledlands.common.tileentity;

import lykrast.defiledlands.common.world.biome.BiomeDefiled;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileConjuringAltar extends TileEntity implements ITickable {
	private boolean active;
	
	public TileConjuringAltar() {
		active = false;
	}

	@Override
	public void update() {
		recalculate();
	}
	
	public void recalculate()
	{
		//Should probably only be done on server side then sent to clients, but this was hard enough to make work
		boolean flag1 = world.isAirBlock(pos.up()) && world.isAirBlock(pos.up(2));
		boolean flag2 = world.getBiome(pos) instanceof BiomeDefiled;
		boolean result = flag1 && flag2;
		
		if (result != active) markDirty();
		active = result;
	}
	
	public boolean isActive() {
		return active;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		active = compound.getBoolean("active");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("active", active);
		return compound;
	}

}
