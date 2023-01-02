package com.levesteszta.towerdefend.screens;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.levesteszta.towerdefend.TowerDefend;
import com.levesteszta.towerdefend.helpers.Artist;

public class AboutScreen extends ScreenAdapter {
    private TowerDefend game;
    private AssetManager assetManager;
    private Stage stage;
    private Skin skin;
    private Table aboutTable;

    public AboutScreen(TowerDefend game, AssetManager assetManager){
        this.assetManager = assetManager;
        this.game = game;
        skin = assetManager.get(Artist.SKIN);
    }

    private TextButton BackButton(String text){
        TextButton button = new TextButton(text, skin);
        aboutTable.add(button).width(WINDOW_WIDTH/2).height(80).padBottom(0).padTop(WINDOW_HEIGHT-100);
        aboutTable.row();
        return button;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.clear();

        aboutTable = new Table();
        aboutTable.setFillParent(true);

        //mainTable.pack();
        stage.addActor(aboutTable);
        Gdx.input.setInputProcessor(stage);
        BackButton("Back").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back to the lobby...");
                aboutTable.clear();
                AboutScreen.this.game.setScreen(new StartScreen(game, assetManager));
            }
        });
        
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }
}
