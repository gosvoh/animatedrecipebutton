package com.fuzs.animatedrecipebutton.handler;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import com.fuzs.animatedrecipebutton.gui.GuiButtonAnimatedBook;
import com.fuzs.animatedrecipebutton.helper.ReflectionHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;
import java.util.List;

public class GuiEventHandler {

    private static final ResourceLocation ANIMATED_RECIPE_BUTTON = new ResourceLocation(AnimatedRecipeButton.MODID, "textures/gui/recipe_button.png");

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void guiInit(GuiScreenEvent.InitGuiEvent.Post evt) {

        GuiScreen gui = evt.getGui();
        GuiButtonAnimatedBook animatedBook = null;

        if (gui instanceof GuiCrafting) {
            animatedBook = this.replaceButton((GuiCrafting) gui, 5, 49);
        } else if (gui instanceof GuiInventory) {
            animatedBook = this.replaceButton((GuiInventory) gui, 104, 22);
        }

        if (animatedBook == null) {
            return;
        }

        // from https://stackoverflow.com/questions/1196586/calling-remove-in-foreach-loop-in-java
        List<GuiButton> buttonList = evt.getButtonList();
        Iterator<GuiButton> iterator = buttonList.iterator();
        boolean flag = false;
        while (iterator.hasNext()) {
            GuiButton button = iterator.next();
            if (button.id == 10 && button.visible) {
                iterator.remove();
                flag = true;
            }
        }

        if (flag) {
            buttonList.add(animatedBook);
        }

    }

    private GuiButtonAnimatedBook replaceButton(GuiContainer gui, int x, int y) {

        GuiButtonAnimatedBook animatedBook = new GuiButtonAnimatedBook(10, gui.getGuiLeft() + x, gui.height / 2 - y,
                20, 18, 0, 0, 2, ANIMATED_RECIPE_BUTTON);
        ReflectionHelper.setRecipeButton(gui, animatedBook);
        return animatedBook;

    }

}
