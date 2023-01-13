package com.moltenwolfcub.boids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BoidsGame extends Game {
	public ShapeRenderer shapeBatch;
	
	@Override
	public void create () {
		shapeBatch = new ShapeRenderer();

		setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		shapeBatch.dispose();
	}
}
