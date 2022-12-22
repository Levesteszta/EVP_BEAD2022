package com.levesteszta.towerdefend;

import com.badlogic.gdx.assets.AssetManager;
import com.levesteszta.towerdefend.GameObjects.Enemies.WaveManager;
import com.levesteszta.towerdefend.GameObjects.Towers.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;

public class myGame {
    private AssetManager assetManager;
    private TowerDefend game;
    private TileGrid map;
    private WaveManager waveManager;

    private myTower tower;

    public myGame(TileGrid map){
        this.map = map;
        map.generate();
        waveManager = new WaveManager(2, map);

        tower = new myBaseTower(map, waveManager);
		tower.setStandingTile(map.getTileDataesByInd(4, 3)); 
    }

    public void update(){
        map.draw();
        tower.update();
        waveManager.update();
    }

}
