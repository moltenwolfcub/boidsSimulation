package com.moltenwolfcub.boids;

import java.util.Arrays;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.moltenwolfcub.boids.util.Config;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] args) {
		if(Arrays.stream(args).anyMatch("pack"::equals)) {
			packTextures();
		}

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
		config.setTitle("Boids");
		new Lwjgl3Application(new BoidsGame(), config);
	}

	private static void packTextures() {
		TexturePacker.process("main/textures", "main/atlases", "spriteTextureMap.atlas");
	}
}
