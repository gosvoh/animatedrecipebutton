package com.fuzs.animatedrecipebutton.helper;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ReflectionHelper {

    private static final String SCREEN_BUTTONS = "buttons";

    public static <T extends ContainerScreen> List<Widget> getButtonList(T instance) {

        try {

            return ObfuscationReflectionHelper.getPrivateValue(Screen.class, instance, SCREEN_BUTTONS);

        } catch (Exception e) {

            AnimatedRecipeButton.LOGGER.error("getButtonList() failed", e);

        }

        return null;

    }

    public static <T extends ContainerScreen> void setButtonList(T instance, List<Widget> list) {

        try {

            ObfuscationReflectionHelper.setPrivateValue(Screen.class, instance, list, SCREEN_BUTTONS);

        } catch (Exception e) {

            AnimatedRecipeButton.LOGGER.error("setButtonList() failed", e);

        }

    }

}
