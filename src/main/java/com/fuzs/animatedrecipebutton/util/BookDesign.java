package com.fuzs.animatedrecipebutton.util;

@SuppressWarnings("unused")
public enum BookDesign {

    VANILLA(0, 9, 1.0F),
    CONCEPT(1, 5, 0.5F);

    private final int id;
    private final int frames;
    private final float speed;

    BookDesign(int id, int frames, float speed) {
        this.id = id;
        this.frames = frames;
        this.speed = speed;
    }

    public int getId() {
        return this.id;
    }

    public int getFrames() {
        return this.frames;
    }

    public float getSpeed() {
        return this.speed;
    }

}
