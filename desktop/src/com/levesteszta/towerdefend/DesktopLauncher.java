package com.levesteszta.towerdefend;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//import com.levesteszta.towerdefend.TowerDefend;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name", "Public");
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Tower Defend");

		// x: 32*31 , y = 22*32
		config.setWindowSizeLimits(992, 704, 992, 704);
		config.setWindowedMode(992, 704);
		
		new Lwjgl3Application(new TowerDefend(), config);
	}
}
