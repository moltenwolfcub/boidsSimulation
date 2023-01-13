package com.moltenwolfcub.boids;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen implements Screen {
    private final BoidsGame game;
    private OrthographicCamera camera;
    private Viewport view;
    private Pool<Boid> boidPool;
    private List<Boid> activeBoids;

    public MainScreen(BoidsGame game) {
		this.game = game;

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false);
		this.view = new FitViewport(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, this.camera);

        this.boidPool = new Pool<Boid>() {
            @Override
            protected Boid newObject() {
                return new Boid();
            }
        };

        this.activeBoids = new ArrayList<Boid>();
        
    }

    @Override
    public void render(float delta) {
        this.update();
        this.draw();
    }
    public void draw() {
        ScreenUtils.clear(0, 0, 0, 0);

        this.camera.update();
        this.game.shapeBatch.begin(ShapeType.Filled);
        this.game.shapeBatch.setColor(Color.RED);
        this.game.shapeBatch.rect(10, 10, 50, 50);
        this.game.shapeBatch.end();
        // this.game.batch.setProjectionMatrix(this.camera.combined);

        // this.game.batch.begin();
        // for (Particle particle : this.activeParticles) {
        //     particle.paint(this.game.batch);
        // }
        // this.game.batch.end();

    }
    public void update() {
        // this.handleInput();
        this.freePooledObjects();

        // for (Particle particle : this.activeParticles) {
        //     particle.tick();
        // }
    }


    private void freePooledObjects() {
        List<Boid> dead = new ArrayList<>();
        for (Boid particle : this.activeBoids) {
            if (particle.used == false) {
                dead.add(particle);
                this.boidPool.free(particle);
                continue;
            }
        }
        this.activeBoids.removeAll(dead);
    }



    @Override
    public void resize(int width, int height) {
        this.view.update(width, height);
    }

    @Override
    public void dispose() {
        
    }


    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void show() {
        
    }
    
}
