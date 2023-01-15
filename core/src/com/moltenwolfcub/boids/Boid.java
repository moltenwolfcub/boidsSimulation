package com.moltenwolfcub.boids;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.moltenwolfcub.boids.util.CachedSprites;
import com.moltenwolfcub.boids.util.Config;

public class Boid extends Actor implements Poolable {
    public boolean used;
    private Sprite sprite;
    private MainScreen screen;

    private Integer id;
    private Vector2 deltaPos;

    public Boid() {
        this.used = false;
        this.sprite = null;
        this.screen = null;
        this.id = -1;
        this.deltaPos = new Vector2(0, 0);
    }

    public Boid init(MainScreen screen, Integer id, Random random) {
        this.used = true;
        this.sprite = CachedSprites.getSprite(BoidsGame.spriteTextureAtlas, "boid");
        this.screen = screen;
        this.id = id;
        this.deltaPos.set(random.nextInt(-5, 5), random.nextInt(-5, 5));

        this.setOrigin(this.getWidth()/2, this.getHeight()/2);
        this.setBounds(random.nextFloat(0, Config.WINDOW_WIDTH-15), random.nextFloat(0, Config.WINDOW_HEIGHT-25), 15, 25);

        // if (this.deltaPos.x == 0) {
        //     this.setColor(Color.RED);
        // }
        // if (this.deltaPos.y == 0) {
        //     this.setColor(Color.GREEN);
        // }

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
        batch.setColor(this.getColor());

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

        this.cohesion();
        this.alignment();
        this.seperation();
        if (Config.AVOID_MOUSE) {
            this.mouseSeparation();
        }

        this.resolveSpeed();


        this.addAction(Actions.moveBy(this.deltaPos.x, this.deltaPos.y, 0f));
        this.handleScreenWrapping();

        this.setRotation(calculateRotation());
    }
    
    private void handleScreenWrapping() {
        if (this.getX()+this.getWidth() < 0) {
            this.setX(Config.WINDOW_WIDTH);
        } else if (this.getX()-this.getWidth() > Config.WINDOW_WIDTH) {
            this.setX(-this.getWidth());
        }
        if (this.getY()+this.getHeight() < 0) {
            this.setY(Config.WINDOW_HEIGHT);
        } else if (this.getY()-this.getHeight() > Config.WINDOW_HEIGHT) {
            this.setY(-this.getHeight());
        }
    }

    private float calculateRotation() {
        float x = deltaPos.x;
        float y = deltaPos.y;
        double radians = Math.atan(y/x);
        float degrees = (float)Math.toDegrees(radians);
        degrees += x>=0 ? -90 : 90;
        return degrees;
    }

    private void resolveSpeed() {
        float moveDistance = (float)Math.sqrt(this.deltaPos.x*this.deltaPos.x + this.deltaPos.y*this.deltaPos.y);
        float targetX = this.deltaPos.x/moveDistance*Config.TARGET_SPEED;
        float targetY = this.deltaPos.y/moveDistance*Config.TARGET_SPEED;
        this.deltaPos.add(Config.RESOLVE_RATE*(targetX-this.deltaPos.x), Config.RESOLVE_RATE*(targetY-this.deltaPos.y));
    }

    
    private void seperation() {
        for (Boid otherBoid : this.screen.getBoids()) {
            if (otherBoid.id == this.id) {
                continue;
            }
            float distanceX = otherBoid.getX() - this.getX();
            float distanceY = otherBoid.getY() - this.getY();
            float distance = (float)Math.sqrt(distanceX*distanceX + distanceY*distanceY);

            if (distance > Config.VIEW_RANGE) {
                continue;
            }
            this.deltaPos.add(-Config.SEPARATION_FORCE*(distanceX/distance), -Config.SEPARATION_FORCE*(distanceY/distance));
        }
    }

    private void cohesion() {
        int inRangeCount = 0;
        int sumX = 0;
        int sumY = 0;

        for (Boid otherBoid : this.screen.getBoids()) {
            if (otherBoid.id == this.id) {
                continue;
            }
            float distanceX = otherBoid.getX() - this.getX();
            float distanceY = otherBoid.getY() - this.getY();
            float distance = (float)Math.sqrt(distanceX*distanceX + distanceY*distanceY);

            if (distance > Config.VIEW_RANGE) {
                continue;
            }
            inRangeCount++;
            sumX+=distanceX;
            sumY+=distanceY;
        }
        try {
            this.deltaPos.add(Config.COHESION_FORCE*(sumX/inRangeCount),0);
        } catch (ArithmeticException e) {}
        try {
            this.deltaPos.add(0, Config.COHESION_FORCE*(sumY/inRangeCount));
        } catch (ArithmeticException e) {}
    }

    private void alignment() {
        int inRangeCount = 0;
        int sumDeltaX = 0;
        int sumDeltaY = 0;

        for (Boid otherBoid : this.screen.getBoids()) {
            if (otherBoid.id == this.id) {
                continue;
            }
            float distanceX = otherBoid.getX() - this.getX();
            float distanceY = otherBoid.getY() - this.getY();
            float distance = (float)Math.sqrt(distanceX*distanceX + distanceY*distanceY);

            if (distance > Config.VIEW_RANGE) {
                continue;
            }
            inRangeCount++;
            sumDeltaX+=otherBoid.deltaPos.x-this.deltaPos.x;
            sumDeltaY+=otherBoid.deltaPos.y-this.deltaPos.x;
        }
        try {
            this.deltaPos.add(Config.ALIGNMENT_FORCE*(sumDeltaX/inRangeCount),0);
        } catch (ArithmeticException e) {}
        try {
            this.deltaPos.add(0, Config.ALIGNMENT_FORCE*(sumDeltaY/inRangeCount));
        } catch (ArithmeticException e) {}
    }

    private void mouseSeparation() {
        Vector3 mousePos = screen.view.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        float distanceX = mousePos.x - this.getX();
        float distanceY = mousePos.y - this.getY();
        float distance = (float)Math.sqrt(distanceX*distanceX + distanceY*distanceY);

        if (distance > Config.VIEW_RANGE*Config.MOUSE_EFFECT_RANGE_SCALAR) {
            return;
        }
        try {
            this.deltaPos.add(-Config.SEPARATION_FORCE*Config.MOUSE_SEPARATION_SCALAR*(distanceX/distance),0);
        } catch (ArithmeticException e) {}
        try {
            this.deltaPos.add(0, -Config.SEPARATION_FORCE*Config.MOUSE_SEPARATION_SCALAR*(distanceY/distance));
        } catch (ArithmeticException e) {}
    }
}
