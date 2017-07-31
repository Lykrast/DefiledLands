package lykrast.defiledlands.common.init;

import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModSounds {
	public static SoundEvent bookWyrmIdle, bookWyrmHurt, bookWyrmDeath;
	
	public static void init()
	{
		bookWyrmIdle = registerSoundEvent("book_wyrm.idle");
		bookWyrmHurt = registerSoundEvent("book_wyrm.hurt");
		bookWyrmDeath = registerSoundEvent("book_wyrm.death");
	}
	
	public static SoundEvent registerSoundEvent(String name)
	{
		ResourceLocation location = new ResourceLocation(DefiledLands.MODID, name);
		SoundEvent event = new SoundEvent(location).setRegistryName(location);
		
		ForgeRegistries.SOUND_EVENTS.register(event);
		
		return event;
	}

}
