package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import static com.levesteszta.towerdefend.helpers.Clock.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TowerDefend extends ApplicationAdapter {
	public static SpriteBatch batch;
	int spawnPoint;
	ShapeRenderer render;
	TileGrid room; 
	Enemy enemy;
	Wave wav;
	OrthographicCamera camera;

	@Override
	public void create() {
		batch = new SpriteBatch();
		render = new ShapeRenderer();
		camera = new OrthographicCamera(WINDOW_WIDTH, WINDOW_HEIGHT);
		camera.rotate(90,0,0,1);
		room = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
		room.generate();
		spawnPoint = room.getStartIndex();	//y kordinátán val óelhelyezkedés
		enemy = new Basic(room);
		wav = new Wave(3, 100f, enemy);
		resetTimer();
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
		setDelta();
		camera.update();
		render.begin(ShapeType.Line);
		for(int i = 0; i < WINDOW_WIDTH/32;i++){
			for(int j = 0; j < WINDOW_HEIGHT/32; j++){
				render.line(i*32, 0,i*32, j*32);
				render.line(0, j*32,i*32, j*32);
			}
		}
		render.end();
		room.draw();
		
		Sprite[] reg = getTexturesFromArea("terrain.png",16); 
		DrawTex(reg[0],0,0,TILE_SIZE);

		wav.update(getTimer());
	}
}
