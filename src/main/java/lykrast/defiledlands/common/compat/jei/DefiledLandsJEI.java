package lykrast.defiledlands.common.compat.jei;

import lykrast.defiledlands.common.init.ModItems;
import lykrast.defiledlands.core.DefiledLands;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JEIPlugin
public class DefiledLandsJEI implements IModPlugin {
	public static final ResourceLocation GUI = new ResourceLocation(DefiledLands.MODID, "textures/gui/jei.png");
	
	@Override
	public void register(IModRegistry registry)
	{
		DefilementMaker.registerRecipes(registry);
		
		registry.addRecipeCatalyst(new ItemStack(ModItems.defilementPowder), DefilementCategory.UID);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories(new DefilementCategory(guiHelper));
	}
}
