package com.levesteszta.towerdefend.screens;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.levesteszta.towerdefend.TowerDefend;
import com.levesteszta.towerdefend.myGame;
import com.levesteszta.towerdefend.helpers.Clock;

public class GameStage extends ScreenAdapter{

	private static TileGrid room; 
    private TowerDefend game;
    private AssetManager assetManager;
    private OrthographicCamera camera;
    private myGame myGame;

    public GameStage(TowerDefend game, AssetManager assetManager){
        this.game = game;
        this.assetManager = assetManager;
        room = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
        //Clock.ResetTimer();
        Clock.Play();
    }
    
    @Override
    public void show(){
        try{
            TowerDefend.batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        myGame = new myGame(room);
        TowerDefend.batch.setProjectionMatrix(camera.combined);
        }catch(Throwable t){
            if (t instanceof RuntimeException)
                    throw (RuntimeException)t;
                else
                    throw new GdxRuntimeException(t);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setContinuousRendering(true);
		Clock.update();
		camera.update();
        myGame.update();
        
    }
    @Override
    public void dispose() {
        super.dispose();
    }
}