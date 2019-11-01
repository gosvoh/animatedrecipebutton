package com.fuzs.animatedrecipebutton.gui;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import com.fuzs.animatedrecipebutton.handler.ConfigBuildHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class BookButton extends GuiButtonImage {

    private static final ResourceLocation BOOK_BUTTON = new ResourceLocation(AnimatedRecipeButton.MODID, "textures/gui/recipe_button.png");

    private final GuiButtonImage parent;
    private final boolean furnace;

    private float animationTicks;
    private boolean bookVisible;

    public BookButton(int id, int posX, int posY, GuiButtonImage button, boolean flag) {

        super(id, posX, posY, 20, 18, 0, 0, 18, BOOK_BUTTON);
        this.parent = button;
        this.furnace = flag;

    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        if (this.visible) {

            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(BOOK_BUTTON);
            GlStateManager.disableDepthTest();

            // all recipe books are saved separately
            boolean flag = mc.player.getRecipeBook().isGuiOpen() && !this.furnace || mc.player.getRecipeBook().func_202883_c() && this.furnace;
            if (!this.bookVisible && flag) {
                this.animationTicks = 0.0F;
            }

            this.bookVisible = flag;
            this.x = this.parent.x;
            this.y = this.parent.y;

            if (this.hovered) {
                this.animationTicks = Math.min(4.0F, this.animationTicks + partialTicks / 2.0F);
            } else {
                this.animationTicks = Math.max(0.0F, this.animationTicks - partialTicks / 2.0F);
            }

            int posX = Math.round(this.animationTicks) * this.width;
            int posY = ConfigBuildHandler.GENERAL_CONFIG.bookDesign.get().getId() * 2 * this.height;

            if (this.bookVisible) {
                posY += this.height;
            }

            // vanilla position usually isn't centered in relation to surroundings, + 1 will fix that
            this.drawTexturedModalRect(this.x + 1, this.y, posX, posY, this.width, this.height);
            GlStateManager.enableDepthTest();

        }

    }

}