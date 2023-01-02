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
import com.levesteszta.towerdefend.TowerDefend;
import com.levesteszta.towerdefend.helpers.Artist;

public class StartScreen extends ScreenAdapter {
    private TowerDefend game;
    private AssetManager assetManager;
    private Skin skin;
    private Table mainTable;

    private TextButton addButton(String text){
        TextButton button = new TextButton(text, skin);
        mainTable.add(button).width(WINDOW_WIDTH/2).height(80).padBottom(10);
        mainTable.row();
        return button;
    }

    public StartScreen(TowerDefend game, AssetManager assetManager){
        this.assetManager = assetManager;
        this.game = game;
        skin = assetManager.get(Artist.SKIN);
    }

    @Override
    public void show() {    //ugyanolyan mint a create, csak ez 1x fut le
        mainTable = new Table();
        mainTable.setFillParent(true);

        //mainTable.pack();
        TowerDefend.stage.addActor(mainTable);


        Gdx.input.setInputProcessor(TowerDefend.stage);

        addButton("Play").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play...");
                mainTable.clear();
                StartScreen.this.game.setScreen(new GameStage(game, assetManager));
            }
        });
        addButton("About").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("About...");
                mainTable.clear();
                StartScreen.this.game.setScreen(new AboutScreen(game, assetManager));
            }
        });

        addButton("Exit").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit...");
                mainTable.clear();
                StartScreen.this.game.dispose();
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        TowerDefend.stage.act();

        TowerDefend.stage.getBatch().begin();
        TowerDefend.stage.getBatch().draw(GetTexture("ui/background.png"), 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        TowerDefend.stage.getBatch().draw(GetTexture("ui/backgroundTitle.png"), 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        TowerDefend.stage.getBatch().end();

        TowerDefend.stage.draw();
    }
}
