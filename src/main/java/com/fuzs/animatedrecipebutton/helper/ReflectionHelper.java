package com.fuzs.animatedrecipebutton.helper;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ReflectionHelper {

    private static final String GUIINVENTORY_RECIPEBUTTON = "field_192048_z";

    public static void setRecipeButton(GuiContainer instance, GuiButtonImage button) {

        try {

            ObfuscationReflectionHelper.setPrivateValue((Class<? super GuiContainer>) instance.getClass(), instance, button, GUIINVENTORY_RECIPEBUTTON);

        } catch (Exception e) {

            AnimatedRecipeButton.LOGGER.error("setRecipeButton() failed", e);

        }

    }

}
