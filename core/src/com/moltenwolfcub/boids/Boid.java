package com.moltenwolfcub.boids;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.moltenwolfcub.boids.util.CachedSprites;

public class Boid extends Actor implements Poolable {
    public boolean used;
    private Sprite sprite;

    public Boid() {
        this.used = false;
        this.sprite = null;
    }

    public Boid init() {
        this.used = true;
        this.sprite = CachedSprites.getSprite(BoidsGame.spriteTextureAtlas, "boid");

        return this;
    } 

    @Override
    public void reset() {
        this.used = false;
        this.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setBounds(50, 50, 15, 25);
        sprite.draw(batch);
    }
    
}
