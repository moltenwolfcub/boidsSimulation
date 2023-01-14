package com.moltenwolfcub.boids;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.moltenwolfcub.boids.util.CachedSprites;
import com.moltenwolfcub.boids.util.Config;

public class Boid extends Actor implements Poolable {
    public boolean used;
    private Sprite sprite;

    private Integer id;
    private Vector2 deltaPos;

    public Boid() {
        this.used = false;
        this.sprite = null;
        this.id = -1;
        this.deltaPos = new Vector2(0, 0);
    }

    public Boid init(Integer id, Random random) {
        this.used = true;
        this.sprite = CachedSprites.getSprite(BoidsGame.spriteTextureAtlas, "boid");
        this.id = id;
        this.deltaPos.set(random.nextInt(-5, 5), random.nextInt(-5, 5));

        this.setOrigin(this.getWidth()/2, this.getHeight()/2);
        this.setBounds(random.nextInt(0, Config.WINDOW_WIDTH-15), random.nextInt(0, Config.WINDOW_HEIGHT-25), 15, 25);

        return this;
    } 

    @Override
    public void reset() {
        this.used = false;
        this.sprite = null;
        this.id = -1;
        this.deltaPos.set(0, 0);
        this.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(this.getColor());

        batch.draw(
            this.sprite,
            this.getX(), this.getY(),
            this.getOriginX(), this.getOriginY(),
            this.getWidth(), this.getHeight(),
            this.getScaleX(), this.getScaleY(),
            this.getRotation()
        );
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        this.addAction(Actions.moveBy(this.deltaPos.x, this.deltaPos.y, 0f));
        this.handleScreenWrapping();
    }
    
    private void handleScreenWrapping() {
        if (this.getX()+this.getWidth() < 0) {
            this.setX(Config.WINDOW_WIDTH);
        } else if (this.getX()-this.getWidth() > Config.WINDOW_WIDTH) {
            this.setX(0);
        }
        if (this.getY()+this.getHeight() < 0) {
            this.setY(Config.WINDOW_HEIGHT);
        } else if (this.getY()-this.getHeight() > Config.WINDOW_HEIGHT) {
            this.setY(0);
        }
    }
}
