package com.fuzs.animatedrecipebutton.helper;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;

public class ReflectionHelper {

    private static final String SCREEN_BUTTONS = "buttons";

    @SuppressWarnings("unchecked")
    public static <T extends GuiContainer> List<GuiButton> getButtonList(T instance) {

        try {

            return (List<GuiButton>) ObfuscationReflectionHelper.getPrivateValue(GuiScreen.class, instance, SCREEN_BUTTONS);

        } catch (Exception e) {

            AnimatedRecipeButton.LOGGER.error("getButtonList() failed", e);

        }

        return null;

    }

    public static <T extends GuiContainer> void setButtonList(T instance, List<GuiButton> list) {

        try {

            ObfuscationReflectionHelper.setPrivateValue(GuiScreen.class, instance, list, SCREEN_BUTTONS);

        } catch (Exception e) {

            AnimatedRecipeButton.LOGGER.error("setButtonList() failed", e);

        }

    }

}
