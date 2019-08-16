package com.fuzs.animatedrecipebutton.handler;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import com.fuzs.animatedrecipebutton.gui.GuiButtonAnimatedBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;
import java.util.List;

public class ModEventHandler {

    private final Minecraft mc = Minecraft.getMinecraft();
    private static final ResourceLocation ANIMATED_RECIPE_BUTTON = new ResourceLocation(AnimatedRecipeButton.MODID,"textures/gui/recipe_button.png");
    private GuiButtonAnimatedBook recipeButton = new GuiButtonAnimatedBook(10, 0, 0, 20, 18, 0, 0, 2, ANIMATED_RECIPE_BUTTON);

    @SubscribeEvent(priority = EventPriority.LOW)
    public void guiInit(GuiScreenEvent.InitGuiEvent.Post evt) {

        GuiScreen gui = evt.getGui();
        if (evt.getGui() instanceof GuiCrafting || evt.getGui() instanceof GuiInventory) {

            this.updateButtonPosition((GuiContainer) gui, this.recipeButton);
            this.recipeButton.bookVisible = mc.player.getRecipeBook().isGuiOpen();

            // solution from https://stackoverflow.com/questions/1196586/calling-remove-in-foreach-loop-in-java
            List<GuiButton> buttonList = evt.getButtonList();
            Iterator<GuiButton> i = buttonList.iterator();
            boolean flag = false;
            while (i.hasNext()) {
                GuiButton button = i.next(); // must be called before you can call i.remove()
                if (button.id == 10 && button.visible) {
                    i.remove();
                    flag = true;
                }
            }

            if (flag) { // compatibility with mods like No Recipe Book
                buttonList.add(recipeButton);
            }

        }
    }

    // needed for GuiRecipeBook#isOffsetNextToMainGUI()
    @SubscribeEvent
    public void guiDrawScreen(GuiScreenEvent.DrawScreenEvent evt) {

        GuiScreen gui = evt.getGui();
        if (gui instanceof GuiCrafting || gui instanceof GuiInventory) {
            this.recipeButton.bookVisible = mc.player.getRecipeBook().isGuiOpen();
        }

    }

    @SubscribeEvent
    public void guiActionTriggered(GuiScreenEvent.ActionPerformedEvent.Post evt) {

        GuiScreen gui = evt.getGui();
        if (gui instanceof GuiCrafting || gui instanceof GuiInventory) {

            if (evt.getButton().id == 10 && evt.getButton() instanceof GuiButtonAnimatedBook)
            {
                this.updateButtonPosition((GuiContainer) gui, this.recipeButton);
                this.recipeButton.bookVisible = mc.player.getRecipeBook().isGuiOpen();
            }
        }

    }

    private void updateButtonPosition(GuiContainer gui, GuiButtonAnimatedBook button) {

        if (gui instanceof GuiCrafting) {
            button.setPosition(gui.getGuiLeft() + 6, gui.height / 2 - 49);
        } else if (gui instanceof GuiInventory) {
            button.setPosition(gui.getGuiLeft() + 105, gui.height / 2 - 22);
        }

    }

}
