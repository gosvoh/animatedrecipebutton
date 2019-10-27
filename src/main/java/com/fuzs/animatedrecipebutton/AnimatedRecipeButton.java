package com.fuzs.animatedrecipebutton;

import com.fuzs.animatedrecipebutton.handler.GuiEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(
        modid = AnimatedRecipeButton.MODID,
        name = AnimatedRecipeButton.NAME,
        version = AnimatedRecipeButton.VERSION,
        acceptedMinecraftVersions = AnimatedRecipeButton.RANGE,
        clientSideOnly = AnimatedRecipeButton.CLIENT,
        dependencies = AnimatedRecipeButton.DEPENDENCIES,
        certificateFingerprint = AnimatedRecipeButton.FINGERPRINT
)
public class AnimatedRecipeButton {

    public static final String MODID = "animatedrecipebutton";
    public static final String NAME = "Animated Recipe Button";
    public static final String VERSION = "@VERSION@";
    public static final String RANGE = "[1.12.2]";
    public static final boolean CLIENT = true;
    public static final String DEPENDENCIES = "required-after:forge@[14.23.5.2779,)";
    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final Logger LOGGER = LogManager.getLogger(AnimatedRecipeButton.NAME);

    @EventHandler
    public void init(FMLInitializationEvent evt) {
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
    }

    @EventHandler
    public void fingerprintViolation(FMLFingerprintViolationEvent evt) {
        LOGGER.warn("Invalid fingerprint detected! The file " + evt.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
    }
}
