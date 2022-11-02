package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TowerDefend extends ApplicationAdapter {
	public static final float SPEED = 1f;
	int spawnPoint;

	ShapeRenderer render;
	TileGrid room; 
	Enemy enemy;

	@Override
	public void create() {
		render = new ShapeRenderer();
		room = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
		room.generate();
		spawnPoint = room.getStartIndex();	//y kordinátán val óelhelyezkedés
		enemy = new Basic(room.getTileDataesByInd(spawnPoint, 0));
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
		float dt = Math.min(Gdx.graphics.getDeltaTime(),1/60f);
		float df = Math.round(Gdx.graphics.getDeltaTime() * 100) / 100.0f;

		render.begin(ShapeType.Line);
		for(int i = 0; i < WINDOW_WIDTH/32;i++){
			for(int j = 0; j < WINDOW_HEIGHT/32; j++){
				render.line(i*32, 0,i*32, j*32);
				render.line(0, j*32,i*32, j*32);
			}
		}
		render.end();
		room.draw();
		//enemy.draw();
		Wave wav = new Wave(10, 1f, enemy);
		wav.update(df);
	}
}
