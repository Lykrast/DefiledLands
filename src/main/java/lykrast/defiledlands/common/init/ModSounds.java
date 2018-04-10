package lykrast.defiledlands.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModSounds {
	public static SoundEvent bookWyrmIdle, bookWyrmHurt, bookWyrmDeath;
	private static List<SoundEvent> soundList = new ArrayList<>();
	
	
	static
	{
		bookWyrmIdle = registerSoundEvent("book_wyrm.idle");
		bookWyrmHurt = registerSoundEvent("book_wyrm.hurt");
		bookWyrmDeath = registerSoundEvent("book_wyrm.death");
	}

	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event)
	{
		for (SoundEvent s : soundList) event.getRegistry().register(s);
		//We no longer use it afterwards
		soundList = null;
	}
	
	public static SoundEvent registerSoundEvent(String name)
	{
		ResourceLocation location = new ResourceLocation(DefiledLands.MODID, name);
		SoundEvent event = new SoundEvent(location).setRegistryName(location);
		
		soundList.add(event);
		
		return event;
	}

}
