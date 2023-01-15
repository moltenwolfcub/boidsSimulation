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
        setup();

        for (int i = 0; i < 100; i++) {

            activeBoids.add(boidPool.obtain().init(this, i, this.game.random));
        }
        for (Boid boid : activeBoids) {
            this.stage.addActor(boid);
        }
    }
    private void setup() {

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

    }

    @Override
    public void render(float delta) {
        tick();
        draw();
    }
    private void draw() {
        ScreenUtils.clear(0, 0, 0, 0);
        this.stage.draw();
    }
    private void tick() {
        this.freePooledObjects();
        this.stage.act(Gdx.graphics.getDeltaTime());
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

    public List<Boid> getBoids() {
        return activeBoids;
    }

    @Override
    public void resize(int width, int height) {
        this.view.update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
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
