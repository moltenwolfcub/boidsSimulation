package com.moltenwolfcub.boids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Boid extends Actor implements Poolable {
    public boolean used;
    private static Texture texture;

    static {
        texture = new Texture(Gdx.files.internal("textures/boid.png"));
    }

    public Boid() {
        this.used = false;
    }

    public Boid init() {
        this.used = true;

        return this;
    } 

    @Override
    public void reset() {
        this.used = false;
        this.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 50, 50, 15, 25);
        // batch.end();
        // if(!projectionMatrixSet) {
        //     this.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // }
        // this.shapeRenderer.begin(ShapeType.Filled);
        // this.shapeRenderer.setColor(Color.RED);

        // int origin = 50;
        // int sideLength = 100;

        // this.shapeRenderer.triangle(
        //     origin, origin,
        //     origin+sideLength, origin,
        //     origin+sideLength/2, (float)(origin+Math.sqrt(Math.pow(sideLength, 2)-Math.pow(sideLength/2, 2)))
        // );
        // this.shapeRenderer.end();
        // batch.begin();
    }
    
}
