package com.moltenwolfcub.boids.util;

public class Config {
    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 900;

    public static final int BOID_COUNT = 1000;
    public static final float TARGET_SPEED = 2.85f;
    public static final float RESOLVE_RATE = 0.2f;
    public static final int VIEW_RANGE = 75;
    public static final float SEPARATION_FORCE = 0.15f;
    public static final float COHESION_FORCE = 0.05f;
    public static final float ALIGNMENT_FORCE = 0.05f;
    public static final boolean AVOID_MOUSE = true;
    public static final float MOUSE_SEPARATION_SCALAR = 3f;
    public static final float MOUSE_EFFECT_RANGE_SCALAR = 2f;

    public static final float HUE_CHANGE_AMOUNT = 0.05f;
    public static final int HUE_STYLE = 2;
    /*
     * 0 = cycle through global
     * 1 = add on to current colour
     * 2 = cycle through local 
     */
    public static final float HUE_STYLE_2_CHANGE_MULTIPLIER = 200.0f;
}
