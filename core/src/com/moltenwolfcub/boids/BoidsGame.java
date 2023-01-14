package com.moltenwolfcub.boids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class BoidsGame extends Game {
	public SpriteBatch spriteBatch;
    public static TextureAtlas spriteTextureAtlas;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		spriteTextureAtlas = new TextureAtlas("main/atlases/spriteTextureMap.atlas");

		setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
	}
}
