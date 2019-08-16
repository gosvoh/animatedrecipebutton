package com.fuzs.animatedrecipebutton.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonAnimatedBook extends GuiButtonImage {

    private final ResourceLocation resourceLocation;
    private final int xTexStart;
    private final int yTexStart;
    private final int yOffset;
    private float animationTicks;
    public boolean bookVisible = false;
    private boolean prevBookVisible = false;

    public GuiButtonAnimatedBook(int buttonId, int posX, int posY, int widthIn, int heightIn, int textureX, int textureY, int p_i47392_8_, ResourceLocation texture) {
        super(buttonId, posX, posY, widthIn, heightIn, textureX, textureY, p_i47392_8_, texture);
        this.xTexStart = textureX;
        this.yTexStart = textureY;
        this.yOffset = p_i47392_8_;
        this.resourceLocation = texture;
    }

    public void setPosition(int posX, int posY) {
        this.x = posX;
        this.y = posY;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(this.resourceLocation);
            GlStateManager.disableDepth();
            int i = this.xTexStart;
            int j = this.yTexStart;

            if (this.hovered && this.animationTicks < 3.0F) {
                if (this.animationTicks < 0.0F) {
                    this.animationTicks = 0.0F;
                }
                this.animationTicks += partialTicks / 2.0F;
            } else if (!this.hovered && this.animationTicks > 0.0F) {
                this.animationTicks -= partialTicks / 2.0F;
            }

            // prevent flipping pages animation when the button was just pressed
            if (!this.prevBookVisible && this.bookVisible) {
                this.animationTicks = 0.0F;
            }

            if (this.bookVisible) {
                j += 20;
            }

            i += Math.round(this.animationTicks) * 20;

            this.drawTexturedModalRect(this.x, this.y - this.yOffset, i, j, this.width, this.height + this.yOffset);
            GlStateManager.enableDepth();
            this.prevBookVisible = this.bookVisible;
        }
    }
}