package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.levesteszta.towerdefend.GameObjects.Player;
import com.levesteszta.towerdefend.GameObjects.Enemies.WaveManager;
import com.levesteszta.towerdefend.MapGen.TileGrid;

public class myGame extends ScreenAdapter {
    private static TileGrid map;
    private static Player player;
    private static WaveManager waveManager;
    private boolean isGameOver = false;

    public myGame(TileGrid room) throws Throwable{
        map = room;
        waveManager = new WaveManager(map);
        player = new Player(map,waveManager);
    }

    public void update(){
        if(player.isGameOver())
            isGameOver = true;
        else{
            map.draw();
            player.update();
            waveManager.update();
        }
    }

    public boolean isGameOver(){
        return this.isGameOver;
    }

}
