package com.moltenwolfcub.boids;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class BoidsGame extends Game {
    public static TextureAtlas spriteTextureAtlas;
	public SpriteBatch spriteBatch;
	public Random random;
	
	@Override
	public void create () {
		spriteTextureAtlas = new TextureAtlas("main/atlases/spriteTextureMap.atlas");
		this.random = new Random();
		this.spriteBatch = new SpriteBatch();

		setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		this.spriteBatch.dispose();
	}
}
