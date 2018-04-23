package com.bigbass.spacefilling.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bigbass.spacefilling.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1200;
		config.height = 900;
		
		config.resizable = false;
		
		config.vSyncEnabled = false;
		
		config.title = "Space Filling Geometry";
		
		new LwjglApplication(new Main(), config);
	}
}
