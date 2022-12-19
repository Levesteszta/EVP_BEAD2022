package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import static com.levesteszta.towerdefend.helpers.Clock.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levesteszta.towerdefend.helpers.Clock;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class TowerDefend extends ApplicationAdapter {
	public static SpriteBatch batch;
	int spawnPoint;
	TileGrid room; 
	Enemy enemy;
	Wave wav;
	OrthographicCamera camera;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(WINDOW_WIDTH, WINDOW_HEIGHT);
		camera.rotate(90,0,0,1);
		room = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
		room.generate();
		spawnPoint = room.getStartIndex();	//y kordinátán val óelhelyezkedés
		enemy = new Basic(room);
		wav = new Wave(1, 350f, enemy);
	}	
	
	@Override
	public void dispose() {
		Dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.graphics.setContinuousRendering(true);

		Clock.update();

		camera.update();


		DrawDebugLines();
		room.draw();
		
		Sprite[] reg = getTexturesFromArea("terrain.png",16); 
		DrawTex(reg[0],0,0,TILE_SIZE);

		wav.update();
	}
}
