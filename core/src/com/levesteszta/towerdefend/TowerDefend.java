package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levesteszta.towerdefend.helpers.Clock;
import com.levesteszta.towerdefend.screens.StartScreen;
import com.badlogic.gdx.Game;

public class TowerDefend extends Game {
	public static SpriteBatch batch;
	public static TowerDefend tdGame;
	public static Game game;

	@Override
	public void create() {
		Clock.Stop();
		LoadAll();
		getAssetManager().finishLoading();
		setScreen(new StartScreen(this, getAssetManager()));
	}	
	
	@Override
	public void dispose() {
		if(batch != null)
			batch.dispose();	// Ne Errorral lépjen ki
	}
}
