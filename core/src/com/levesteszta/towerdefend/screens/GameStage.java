package com.levesteszta.towerdefend.screens;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.GameObjects.Towers.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levesteszta.towerdefend.TowerDefend;
import com.levesteszta.towerdefend.myGame;
import com.levesteszta.towerdefend.helpers.Clock;

public class GameStage extends ScreenAdapter {
    TowerDefend game;
    myGame myGame;
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
        Clock.ResetTimer();
        Clock.Play();
    }
    
    @Override
    public void show() {
        TowerDefend.batch = new SpriteBatch();
        myGame = new myGame(room);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setContinuousRendering(true);
        
		Clock.update();
		myGame.update();
        
    }
    @Override
    public void dispose() {
        super.dispose();
    }
}