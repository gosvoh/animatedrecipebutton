package com.fuzs.animatedrecipebutton.util;

@SuppressWarnings("unused")
public enum BookDesign {

    VANILLA(0),
    CONCEPT(1);

    private final int id;

    BookDesign(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
