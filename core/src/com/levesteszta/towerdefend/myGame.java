package com.levesteszta.towerdefend;

import com.badlogic.gdx.ScreenAdapter;
import com.levesteszta.towerdefend.GameObjects.Player;
import com.levesteszta.towerdefend.GameObjects.Enemies.WaveManager;
import com.levesteszta.towerdefend.MapGen.TileGrid;
import com.levesteszta.towerdefend.helpers.Clock;

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
        if(!Clock.isPaused()){
            map.draw();
            player.update();
            waveManager.update();
        }
    }

    
    /** 
     * Game is Over, Good bye horses 
     * @return boolean
     */
    public boolean isGameOver(){
        return this.isGameOver;
    }

}
