package com.fuzs.animatedrecipebutton.gui;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import com.fuzs.animatedrecipebutton.handler.ConfigBuildHandler;
import com.fuzs.animatedrecipebutton.util.BookDesign;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;

public class BookButton extends ImageButton {

    private static final ResourceLocation BOOK_BUTTON = new ResourceLocation(AnimatedRecipeButton.MODID, "textures/gui/recipe_button.png");

    private final ImageButton parent;
    private final AbstractRecipeBookGui recipeBook;

    private float animationTicks;
    private boolean bookVisible;

    public BookButton(int posX, int posY, ImageButton button, AbstractRecipeBookGui recipeBookScreen) {

        super(posX, posY, 20, 18, 0, 0, 18, BOOK_BUTTON, it -> {});
        this.parent = button;
        this.recipeBook = recipeBookScreen;

    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void renderButton(int mouseX, int mouseY, float partialTicks) {

        if (this.visible) {

            BookDesign design = ConfigBuildHandler.GENERAL_CONFIG.bookDesign.get();
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(BOOK_BUTTON);
            GlStateManager.disableDepthTest();

            // all recipe books are saved separately
            boolean flag = mc.player.getRecipeBook().isGuiOpen() && this.recipeBook == null || this.recipeBook != null && this.recipeBook.isVisible();
            if (!this.bookVisible && flag) {
                this.animationTicks = 0.0F;
            }

            this.bookVisible = flag;
            this.x = this.parent.x;
            this.y = this.parent.y;

            if (this.isHovered()) {
                this.animationTicks = Math.min(design.getFrames() - 1.0F, this.animationTicks + partialTicks * design.getSpeed());
            } else {
                this.animationTicks = Math.max(0.0F, this.animationTicks - partialTicks * design.getSpeed());
            }

            int posX = Math.round(this.animationTicks) * this.width;
            int posY = design.getId() * 2 * this.height;

            if (this.bookVisible) {
                posY += this.height;
            }

            // vanilla position usually isn't centered in relation to surroundings, + 1 will fix that
            blit(this.x + 1, this.y, posX, posY, this.width, this.height);
            GlStateManager.enableDepthTest();

        }

    }

}