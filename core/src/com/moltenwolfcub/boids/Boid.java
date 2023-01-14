package com.moltenwolfcub.boids;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.moltenwolfcub.boids.util.CachedSprites;
import com.moltenwolfcub.boids.util.Config;

public class Boid extends Actor implements Poolable {
    public boolean used;
    private Sprite sprite;

    private Integer id;

    public Boid() {
        this.used = false;
        this.sprite = null;
        this.id = -1;
    }

    public Boid init(Integer id, Random random) {
        this.used = true;
        this.sprite = CachedSprites.getSprite(BoidsGame.spriteTextureAtlas, "boid");
        this.id = id;

        sprite.setBounds(random.nextInt(0, Config.WINDOW_WIDTH-15), random.nextInt(0, Config.WINDOW_HEIGHT-25), 15, 25);

        return this;
    } 

    @Override
    public void reset() {
        this.used = false;
        this.id = -1;
        this.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
    
}
