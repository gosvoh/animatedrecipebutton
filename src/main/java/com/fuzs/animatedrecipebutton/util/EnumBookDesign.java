package com.fuzs.animatedrecipebutton.util;

@SuppressWarnings("unused")
public enum EnumBookDesign {

    VANILLA(0),
    CONCEPT(1);

    private final int id;

    EnumBookDesign(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
