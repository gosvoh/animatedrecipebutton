package com.fuzs.animatedrecipebutton;

import com.fuzs.animatedrecipebutton.handler.ConfigBuildHandler;
import com.fuzs.animatedrecipebutton.handler.GuiEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(AnimatedRecipeButton.MODID)
public class AnimatedRecipeButton {

    public static final String MODID = "animatedrecipebutton";
    public static final String NAME = "Animated Recipe Button";
    public static final Logger LOGGER = LogManager.getLogger(AnimatedRecipeButton.NAME);

    public AnimatedRecipeButton() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigBuildHandler.SPEC, MODID + ".toml");

    }

    private void clientSetup(final FMLClientSetupEvent evt) {
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
    }

}
