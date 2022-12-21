package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import static com.levesteszta.towerdefend.helpers.Clock.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levesteszta.towerdefend.helpers.Artist;
import com.levesteszta.towerdefend.helpers.Clock;
import com.levesteszta.towerdefend.screens.StartScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TowerDefend extends Game {
	public static SpriteBatch batch;
	public static Game game;

	@Override
	public void create() {
		Clock.Stop();
		LoadAll();
		getAssetManager().finishLoading();
		setScreen(new StartScreen(this, getAssetManager()));
		
		/* 
		batch = new SpriteBatch()
		room = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
		room.generate();
		spawnPoint = room.getStartIndex();	//y kordinátán való elhelyezkedés
		enemy = new Basic(room);
		enemy = new Fire(room);
		//wav = new Wave(3, 5f, room);
		
		//tower = new BaseTower(room, wav.getEnemies());
		//tower.setStandingTile(room.getTileDataesByInd(4, 3)); 

		waveManager = new WaveManager(2, room);
		//System.out.println("PAIN: "+1+"");

		*/
	}	
	
	@Override
	public void dispose() {
		if(batch != null)
			this.batch.dispose();	// Ne Errorral lépjen ki
	}
	/*
	@Override
	public void render() {
		
		Gdx.graphics.setContinuousRendering(true);

		Clock.update();

		DrawDebugLines();
		room.draw();
		
		//tower.draw();
		//tower.update();

		waveManager.update();
	}*/
}
