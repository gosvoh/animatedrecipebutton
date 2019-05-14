package com.fuzs.animatedrecipebutton;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = AnimatedRecipeButton.MODID,
        name = AnimatedRecipeButton.NAME,
        version = AnimatedRecipeButton.VERSION,
        acceptedMinecraftVersions = AnimatedRecipeButton.RANGE,
        clientSideOnly = AnimatedRecipeButton.CLIENT,
        certificateFingerprint = AnimatedRecipeButton.FINGERPRINT
)
public class AnimatedRecipeButton
{
    public static final String MODID = "animatedrecipebutton";
    public static final String NAME = "Animated Recipe Button";
    public static final String VERSION = "@VERSION@";
    public static final String RANGE = "[1.12, 1.12.2]";
    public static final boolean CLIENT = true;
    public static final String FINGERPRINT = "@FINGERPRINT@";

    private static final Logger LOGGER = LogManager.getLogger(AnimatedRecipeButton.NAME);

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
    }

    @EventHandler
    public void fingerprintViolation(FMLFingerprintViolationEvent event) {
        LOGGER.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
    }
}
