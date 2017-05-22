package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Controllers.Dash;
import com.mygdx.game.Utils.CONSTANTS;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = CONSTANTS.WIDTH;
		config.height = CONSTANTS.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new Dash(), config);
	}
}
