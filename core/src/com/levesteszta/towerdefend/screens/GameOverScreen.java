package com.levesteszta.towerdefend.screens;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.levesteszta.towerdefend.TowerDefend;
import com.levesteszta.towerdefend.helpers.Artist;

public class GameOverScreen extends ScreenAdapter {
    private TowerDefend game;
    private AssetManager assetManager;
    private Skin skin;
    private Table gameoverTable;

    public GameOverScreen(TowerDefend game, AssetManager assetManager){
        this.assetManager = assetManager;
        this.game = game;
        skin = assetManager.get(Artist.SKIN);
    }

    
    /** 
     * @param text
     * @return TextButton
     */
    private TextButton BackButton(String text){
        TextButton button = new TextButton(text, skin);
        gameoverTable.add(button).width(WINDOW_WIDTH/2).height(80).padBottom(0).padTop(WINDOW_HEIGHT-140);
        gameoverTable.row();
        return button;
    }

    @Override
    public void show() {
        gameoverTable = new Table();
        gameoverTable.setFillParent(true);

        //mainTable.pack();
        TowerDefend.stage.addActor(gameoverTable);
        Gdx.input.setInputProcessor(TowerDefend.stage);
        BackButton("Back to Menu").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameoverTable.clear();
                GameOverScreen.this.game.setScreen(new StartScreen(game, assetManager));
            }
        });
        
    }

    
    /** 
     * @param delta
     */
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        TowerDefend.stage.act();
        TowerDefend.stage.getBatch().begin();
        TowerDefend.stage.getBatch().draw(GetTexture("ui/GAMEOVERbackground.png"), 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        TowerDefend.stage.getBatch().end();
        TowerDefend.stage.draw();
    }
}
