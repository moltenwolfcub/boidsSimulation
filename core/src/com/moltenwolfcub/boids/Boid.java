package com.moltenwolfcub.boids;

import com.badlogic.gdx.utils.Pool.Poolable;

public class Boid implements Poolable {
    public boolean used;

    public Boid() {
        this.used = false;
    }

    public void init() {
        this.used = true;
    } 

    @Override
    public void reset() {
        this.used = false;
    }
    
}
