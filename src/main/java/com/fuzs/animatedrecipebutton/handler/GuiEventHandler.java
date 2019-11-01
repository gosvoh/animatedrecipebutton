package com.fuzs.animatedrecipebutton.handler;

import com.fuzs.animatedrecipebutton.gui.BookButton;
import com.fuzs.animatedrecipebutton.helper.ReflectionHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Iterator;
import java.util.List;

public class GuiEventHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void guiInit(GuiScreenEvent.InitGuiEvent.Post evt) {

        GuiScreen screen = evt.getGui();

        if (screen instanceof GuiContainer) {

            GuiContainer containerScreen = (GuiContainer) screen;
            List<GuiButton> buttonList = ReflectionHelper.getButtonList(containerScreen);

            if (buttonList != null) {

                Iterator<GuiButton> iterator = buttonList.iterator();
                GuiButtonImage button = null;

                while (iterator.hasNext()) {

                    GuiButton widget = iterator.next();

                    if (widget instanceof GuiButtonImage) {
                        button = (GuiButtonImage) widget;
                        iterator.remove();
                        break;
                    }

                }

                if (button != null) {

                    // replace vanilla recipe button in rendering list, isn't replaced in the list handling button presses
                    BookButton animatedBook = new BookButton(button.id, button.x, button.y, button, containerScreen instanceof GuiFurnace);
                    buttonList.add(animatedBook);
                    ReflectionHelper.setButtonList(containerScreen, buttonList);

                }

            }

        }

    }

}
