package com.fuzs.animatedrecipebutton.gui;

import com.fuzs.animatedrecipebutton.AnimatedRecipeButton;
import com.fuzs.animatedrecipebutton.handler.ConfigBuildHandler;
import com.fuzs.animatedrecipebutton.util.BookDesign;
import com.mojang.blaze3d.matrix.MatrixStack;
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

        super(posX, posY, 20, 18, 0, 0, 18, BOOK_BUTTON, it -> {
        });
        this.parent = button;
        this.recipeBook = recipeBookScreen;

    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void func_230431_b_(@SuppressWarnings("NullableProblems") MatrixStack stack, int mouseX, int mouseY, float partialTicks) {

        if (this.field_230694_p_) { //this.visible

            BookDesign design = ConfigBuildHandler.GENERAL_CONFIG.bookDesign.get();
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(BOOK_BUTTON);
            GlStateManager.disableDepthTest();

            // all recipe books are saved separately
            //noinspection ConstantConditions
            boolean flag = mc.player.getRecipeBook().isGuiOpen() && this.recipeBook == null || this.recipeBook != null && this.recipeBook.isVisible();
            if (!this.bookVisible && flag) {
                this.animationTicks = 0.0F;
            }

            this.bookVisible = flag;
            this.field_230690_l_ = this.parent.field_230690_l_; //this.x
            this.field_230691_m_ = this.parent.field_230691_m_; //this.y

            if (this.func_230449_g_()) { //this.isHovered()
                this.animationTicks = Math.min(design.getFrames() - 1.0F, this.animationTicks + partialTicks * design.getSpeed());
            } else {
                this.animationTicks = Math.max(0.0F, this.animationTicks - partialTicks * design.getSpeed());
            }

            int posX = Math.round(this.animationTicks) * this.field_230688_j_; //... * this.width
            int posY = design.getId() * 2 * this.field_230689_k_; //... * this.height

            if (this.bookVisible) {
                posY += this.field_230689_k_; //posY += this.height
            }

            // vanilla position usually isn't centered in relation to surroundings, + 1 will fix that
            func_238474_b_(stack, this.field_230690_l_ + 1, this.field_230691_m_, posX, posY, this.field_230688_j_, this.field_230689_k_);
            GlStateManager.enableDepthTest();

        }

    }

}