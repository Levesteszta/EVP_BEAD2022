package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import static com.levesteszta.towerdefend.helpers.Clock.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levesteszta.towerdefend.MapGen.TileGrid;
import com.levesteszta.towerdefend.helpers.Artist;
import com.levesteszta.towerdefend.helpers.Clock;
import com.levesteszta.towerdefend.screens.StartScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TowerDefend extends Game {
	public static SpriteBatch batch;
	public static Game game;
	public static TileGrid map;

	@Override
	public void create() {
		map = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
		Clock.Stop();
		LoadAll();
		getAssetManager().finishLoading();
		setScreen(new StartScreen(this, getAssetManager()));
	}	
	
	@Override
	public void dispose() {
		if(batch != null)
			this.batch.dispose();	// Ne Errorral lépjen ki
	}
}
