package com.levesteszta.towerdefend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TowerDefend extends ApplicationAdapter {
	float WINDOW_WIDTH = 997f;
	float WINDOW_HEIGHT = 706f;
	int TILE_SIZE = 32;

	SpriteBatch batch;
	ShapeRenderer render;
	TileGrid room; 
	Enemy enemies;

	@Override
	public void create () {
		batch = new SpriteBatch();
		render = new ShapeRenderer();
		room = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
		room.generate();

		enemies = new Basic(TILE_SIZE*2, TILE_SIZE*(6+(room.getStartIndex())));
	}	
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public void render () {
		//render

		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		render.begin(ShapeType.Line);
		for(int i = 0; i < WINDOW_WIDTH/32;i++){
			for(int j = 0; j < WINDOW_HEIGHT/32; j++){
				render.line(i*32, 0,i*32, j*32);
				render.line(0, j*32,i*32, j*32);
			}
		}
		render.end();
		batch.begin();
			room.draw(batch);
			enemies.draw(batch);
		batch.end();
	}
}
