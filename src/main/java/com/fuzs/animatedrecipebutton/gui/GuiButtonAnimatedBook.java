package com.fuzs.animatedrecipebutton.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class GuiButtonAnimatedBook extends GuiButtonImage {

    private final ResourceLocation resourceLocation;
    private final int xTexStart;
    private final int yTexStart;
    private final int yOffset;

    private float animationTicks;
    private boolean bookVisible;

    public GuiButtonAnimatedBook(int buttonId, int posX, int posY, int widthIn, int heightIn, int textureX, int textureY, int p_i47392_8_, ResourceLocation texture) {

        super(buttonId, posX, posY, widthIn, heightIn, textureX, textureY, p_i47392_8_, texture);
        this.xTexStart = textureX;
        this.yTexStart = textureY;
        this.yOffset = p_i47392_8_;
        this.resourceLocation = texture;

    }

    public void setPosition(int posX, int posY) {

        this.x = posX + 1;
        this.y = posY;

        if (!this.bookVisible) {
            this.animationTicks = 0.0F;
        }

    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {

        if (this.visible) {

            this.bookVisible = mc.player.getRecipeBook().isGuiOpen();

            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(this.resourceLocation);
            GlStateManager.disableDepth();

            int i = this.xTexStart;
            int j = this.yTexStart;

            if (this.hovered) {
                this.animationTicks = Math.min(3.0F, this.animationTicks + partialTicks / 2.0F);
            } else {
                this.animationTicks = Math.max(0.0F, this.animationTicks - partialTicks / 2.0F);
            }

            if (this.bookVisible) {
                j += 20;
            }

            i += Math.round(this.animationTicks) * 20;
            this.drawTexturedModalRect(this.x, this.y - this.yOffset, i, j, this.width, this.height + this.yOffset);
            GlStateManager.enableDepth();

        }

    }

}