package com.levesteszta.towerdefend;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.levesteszta.towerdefend.GameObjects.Player;
import com.levesteszta.towerdefend.GameObjects.Enemies.WaveManager;
import com.levesteszta.towerdefend.GameObjects.Towers.*;
import com.levesteszta.towerdefend.GameObjects.UI.myCard;
import com.levesteszta.towerdefend.MapGen.TileGrid;
import static com.levesteszta.towerdefend.helpers.Artist.*;

public class myGame {
    private static TileGrid map;
    private WaveManager waveManager;
    private Player player;
    private ArrayList<myCard> cards;
    //private myTower tower;

    private void makeCards() throws Throwable{
        this.cards = new ArrayList<myCard>(6);
        System.out.println("itt van a gond? nem");
        this.cards.add(new myCard((new myAirTower(map, waveManager).getStats())));
        this.cards.add(new myCard((new myElectroTower(map, waveManager).getStats())));
        this.cards.add(new myCard((new myFireTower(map, waveManager).getStats())));
        this.cards.add(new myCard((new myIceTower(map, waveManager).getStats())));
        this.cards.add(new myCard((new myWaterTower(map, waveManager).getStats())));
        this.cards.add(new myCard((new myAirTower(map, waveManager).getStats())));
    }

    public myGame(TileGrid room) throws Throwable{
        map = room;
        this.waveManager = new WaveManager(2, map);
        this.player = new Player(map,this.waveManager);
        makeCards();

        //this.tower = new myBaseTower(map, waveManager);
		//this.tower.setStandingTile(map.getTileDataesByInd(4, 3)); 
    }

    public void update(){
        map.draw();
        this.player.update();
        this.waveManager.update();
        
        for(myCard card : this.cards)
            card.draw((int)(this.cards.indexOf(card)*card.getWidth()),0);
        //this.tower.update();
    }

}
