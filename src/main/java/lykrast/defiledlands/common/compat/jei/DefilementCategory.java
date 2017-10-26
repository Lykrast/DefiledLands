package lykrast.defiledlands.common.compat.jei;

import lykrast.defiledlands.core.DefiledLands;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.config.Constants;
import net.minecraft.util.text.translation.I18n;

public class DefilementCategory implements IRecipeCategory<DefilementWrapper> {
	private final IDrawable background;
	private final String localizedName;
	public static final String UID = "dldefilement";

	public DefilementCategory(IGuiHelper guiHelper)
	{
		background = guiHelper.createDrawable(DefiledLandsJEI.GUI, 0, 0, 76, 18);
		localizedName = I18n.translateToLocal("ui.defiledlands.jei.defilement");
	}

	@Override
	public String getUid()
	{
		return UID;
	}

	@Override
	public String getTitle()
	{
		return localizedName;
	}

	@Override
	public String getModName()
	{
		return DefiledLands.MODID;
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, DefilementWrapper recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 0, 0);
		guiItemStacks.init(1, false, 58, 0);

		guiItemStacks.set(ingredients);
	}

}
