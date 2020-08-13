package com.fuzs.animatedrecipebutton.handler;

import com.fuzs.animatedrecipebutton.gui.BookButton;
import com.fuzs.animatedrecipebutton.helper.ReflectionHelper;
import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"rawtypes", "unused"})
public class GuiEventHandler {

    @SubscribeEvent
    public void guiInit(GuiScreenEvent.InitGuiEvent.Post evt) {

        Screen screen = evt.getGui();

        if (screen instanceof ContainerScreen) {

            ContainerScreen containerScreen = (ContainerScreen) screen;
            List<Widget> buttonList = ReflectionHelper.getButtonList(containerScreen);

            if (buttonList != null) {

                Iterator<Widget> iterator = buttonList.iterator();
                ImageButton button = null;

                while (iterator.hasNext()) {

                    Widget widget = iterator.next();

                    if (widget instanceof ImageButton) {
                        button = (ImageButton) widget;
                        iterator.remove();
                        break;
                    }

                }

                if (button != null) {

                    // get recipe book for the button to check when it's opened, null is handeled later
                    AbstractRecipeBookGui recipeBookScreen = null;
                    if (containerScreen instanceof AbstractFurnaceScreen) {
                        recipeBookScreen = ((AbstractFurnaceScreen) containerScreen).recipeGui;
                    }

                    // replace vanilla recipe button in rendering list, isn't replaced in the list handling button presses
                    BookButton animatedBook = new BookButton(button.field_230690_l_, button.field_230691_m_, button, recipeBookScreen);
                    buttonList.add(animatedBook);
                    ReflectionHelper.setButtonList(containerScreen, buttonList);

                }

            }

        }

    }

}
