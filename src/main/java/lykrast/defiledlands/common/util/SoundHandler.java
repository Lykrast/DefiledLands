package lykrast.defiledlands.common.util;

import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundHandler {
	public static SoundEvent bookWyrmIdle, bookWyrmHurt, bookWyrmDeath;
	
	public static void init()
	{
		bookWyrmIdle = registerSoundEvent("bookwyrm.idle");
		bookWyrmHurt = registerSoundEvent("bookwyrm.hurt");
		bookWyrmDeath = registerSoundEvent("bookwyrm.death");
	}
	
	public static SoundEvent registerSoundEvent(String name)
	{
		ResourceLocation location = new ResourceLocation(DefiledLands.MODID, name);
		SoundEvent event = new SoundEvent(location).setRegistryName(location);
		
		ForgeRegistries.SOUND_EVENTS.register(event);
		
		return event;
	}

}
