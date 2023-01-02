package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.levesteszta.towerdefend.helpers.Clock;
import com.levesteszta.towerdefend.screens.StartScreen;
import com.badlogic.gdx.Game;

public class TowerDefend extends Game {
	public static SpriteBatch batch;
	public static Stage stage;
	public static Game game;

	@Override
	public void create() {
		stage = new Stage(new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.clear();
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
