package com.moltenwolfcub.boids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoidsGame extends Game {
	// public ShapeRenderer shapeBatch;
	public SpriteBatch spriteBatch;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		// shapeBatch = new ShapeRenderer();

		setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		// shapeBatch.dispose();
		spriteBatch.dispose();
	}
}
