package com.levesteszta.towerdefend.screens;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.levesteszta.towerdefend.TowerDefend;
import com.levesteszta.towerdefend.myGame;
import com.levesteszta.towerdefend.helpers.Clock;

public class GameStage extends ScreenAdapter{

	private static TileGrid room;
    private Skin skin; 
    private TowerDefend game;
    private AssetManager assetManager;
    private Table gameTable;
    private OrthographicCamera camera;
    private myGame myGame;

    public GameStage(TowerDefend game, AssetManager assetManager){
        this.game = game;
        this.assetManager = assetManager;
        this.skin = assetManager.get(SKIN);
        room = new TileGrid(TILE_SIZE*2, TILE_SIZE*6, WINDOW_WIDTH-(TILE_SIZE*2), WINDOW_HEIGHT-(TILE_SIZE*1));
        //Clock.ResetTimer();
        Clock.Play();
    }

    public void GameOver(){
        Gdx.input.setInputProcessor(null);
        Clock.Stop();
        myGame.dispose();
        GameStage.this.game.setScreen(new GameOverScreen(game, assetManager));
    }

    
    @Override
    public void show(){
        try{
            TowerDefend.batch = new SpriteBatch();
            camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            gameTable = new Table();
            gameTable.setFillParent(true);

            TowerDefend.stage.addActor(gameTable);
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
        
        TowerDefend.stage.act();
            Clock.update();
            camera.update();
            myGame.update();
        TowerDefend.stage.draw();
        if(myGame.isGameOver())
            GameOver();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}