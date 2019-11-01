package com.fuzs.animatedrecipebutton.handler;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import com.fuzs.animatedrecipebutton.util.EnumBookDesign;
import net.minecraftforge.common.config.Config;

@Config(modid = AnimatedRecipeButton.MODID)
public class ConfigBuildHandler {

	@Config.Name("Book Design")
	@Config.Comment("Choose a book design to be used for the animation.")
	public static EnumBookDesign bookDesign = EnumBookDesign.VANILLA;
	
}
