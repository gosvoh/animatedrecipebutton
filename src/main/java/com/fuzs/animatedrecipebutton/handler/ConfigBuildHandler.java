package com.fuzs.animatedrecipebutton.handler;

import com.fuzs.animatedrecipebutton.util.BookDesign;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigBuildHandler {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final GeneralConfig GENERAL_CONFIG = new GeneralConfig("general");

    public static class GeneralConfig {

        public final ForgeConfigSpec.EnumValue<BookDesign> bookDesign;

        private GeneralConfig(String name) {

            BUILDER.push(name);
            this.bookDesign = ConfigBuildHandler.BUILDER.comment("Choose a book design to be used for the animation.").defineEnum("Book Design", BookDesign.VANILLA);
            BUILDER.pop();

        }

    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
