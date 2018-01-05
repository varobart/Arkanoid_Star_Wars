package ru.varazdat.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.varazdat.game.ArkanoidGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("user.name","seconduser");
		config.width = 800;
		config.height = 500;
		config.title = "Arkanoid";
		new LwjglApplication(new ArkanoidGame(), config);
	}
}
