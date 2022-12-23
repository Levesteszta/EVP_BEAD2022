package com.levesteszta.towerdefend;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//import com.levesteszta.towerdefend.TowerDefend;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name", "Public");
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		// Az update framerate végett ki kell kötni mennyi is az annyi
		config.setForegroundFPS(60);
		config.setIdleFPS(60);
		// Window szépségek
		config.setTitle("Tower Defend");
		config.setWindowIcon("money.png");
		// x: 32*31 , y = 22*32
		// ne lehessen átméretezni, mert . !
		config.setWindowSizeLimits(992, 704, 992, 704);
		config.setWindowedMode(992, 704);
		
		new Lwjgl3Application(new TowerDefend(), config);
	}
}
