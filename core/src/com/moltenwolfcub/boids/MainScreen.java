package com.moltenwolfcub.boids;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.moltenwolfcub.boids.util.Config;

public class MainScreen implements Screen {
    private final BoidsGame game;
    private OrthographicCamera camera;
    private Viewport view;
    private Stage stage;

    private Pool<Boid> boidPool;
    private List<Boid> activeBoids;

    public MainScreen(BoidsGame game) {
		this.game = game;

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false);
		this.view = new FitViewport(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, this.camera);
        this.stage = new Stage(view, this.game.spriteBatch);
        Gdx.input.setInputProcessor(stage);

        this.boidPool = new Pool<Boid>() {
            @Override
            protected Boid newObject() {
                return new Boid();
            }
        };
        this.activeBoids = new ArrayList<Boid>();

        Boid tmpBoid = boidPool.obtain().init();
        this.activeBoids.add(tmpBoid);
        this.stage.addActor(tmpBoid);
    }

    @Override
    public void render(float delta) {
        tick();
        draw();
    }
    public void draw() {
        ScreenUtils.clear(0, 0, 0, 0);
        stage.draw();
    }
    public void tick() {
        this.freePooledObjects();
    }


    private void freePooledObjects() {
        List<Boid> dead = new ArrayList<>();
        for (Boid boid : this.activeBoids) {
            if (boid.used == false) {
                dead.add(boid);
                this.boidPool.free(boid);
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
