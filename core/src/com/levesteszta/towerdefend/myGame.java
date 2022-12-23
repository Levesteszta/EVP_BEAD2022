package com.levesteszta.towerdefend;

import com.levesteszta.towerdefend.GameObjects.Player;
import com.levesteszta.towerdefend.GameObjects.Enemies.WaveManager;
import com.levesteszta.towerdefend.GameObjects.Towers.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;

public class myGame {
    private static TileGrid map;
    private WaveManager waveManager;
    private Player player;
    private myTower tower;

    public myGame(TileGrid room){
        map = room;
        this.waveManager = new WaveManager(2, map);
        this.player = new Player(map);
        
        //this.tower = new myBaseTower(map, waveManager);
		//this.tower.setStandingTile(map.getTileDataesByInd(4, 3)); 
    }
    
    public void update(){
        map.draw();
        this.player.update();
        this.waveManager.update();
        //this.tower.update();
    }

}
