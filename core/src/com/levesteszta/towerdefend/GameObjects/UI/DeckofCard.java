package com.levesteszta.towerdefend.GameObjects.UI;

import java.util.ArrayList;

import com.levesteszta.towerdefend.GameObjects.Enemies.WaveManager;
import com.levesteszta.towerdefend.GameObjects.Towers.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;
public class DeckofCard {
    private TileGrid map;
    private WaveManager waves;
    private ArrayList<myCard> cards;
    
    public DeckofCard(TileGrid map, WaveManager waves) throws Throwable{
        this.map = map; this.waves = waves;
        makeCards();
    }

    private void makeCards() throws Throwable{
        this.cards = new ArrayList<myCard>(6);
        this.cards.add(new myCard((new myAirTower(map, waves).getStats())));
        this.cards.add(new myCard((new myElectroTower(map, waves).getStats())));
        this.cards.add(new myCard((new myFireTower(map, waves).getStats())));
        this.cards.add(new myCard((new myIceTower(map, waves).getStats())));
        this.cards.add(new myCard((new myWaterTower(map, waves).getStats())));
        this.cards.add(new myCard((new myGeoTower(map, waves).getStats())));
    }

    public void draw(){
        for(myCard card : this.cards)
            card.draw((int)(this.cards.indexOf(card)*card.getWidth()),0);
    }

    public myCard getInCoord(int x, int y){
        for(myCard card : cards){
            if(card.thisClicked(x, y))
                return card;
        }
        return null;
    }

    public ArrayList<myCard> getCards() {
        return cards;
    }
}
