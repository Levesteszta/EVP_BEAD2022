package com.levesteszta.towerdefend.screens;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levesteszta.towerdefend.TileGrid;
import com.levesteszta.towerdefend.TowerDefend;
import com.levesteszta.towerdefend.Wave;
import com.levesteszta.towerdefend.WaveManager;
import com.levesteszta.towerdefend.myTower;
import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.helpers.Clock;

public class GameStage extends ScreenAdapter {
    TowerDefend game;

    int spawnPoint;
    AssetManager assetManager;
	TileGrid room; 
	Enemy enemy;
	Wave wav;
	WaveManager waveManager;
	myTower tower;

    public GameStage(TowerDefend game, AssetManager assetManager){
        this.game = game;
        this.assetManager = assetManager;
        room = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
    }
    
    @Override
    public void show() {
        TowerDefend.batch = new SpriteBatch();
        Clock.Play();
        room.generate();
		spawnPoint = room.getStartIndex();	//y kordinátán való elhelyezkedés
		enemy = new Basic(room);
		enemy = new Fire(room);
		//wav = new Wave(3, 5f, room);
		
		//tower = new BaseTower(room, wav.getEnemies());
		//tower.setStandingTile(room.getTileDataesByInd(4, 3)); 

		waveManager = new WaveManager(2, room);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setContinuousRendering(true);
        
		Clock.update();

		DrawDebugLines();
		room.draw();
		
		//tower.draw();
		//tower.update();

		waveManager.update();
    }
    @Override
    public void dispose() {
        super.dispose();
    }
}