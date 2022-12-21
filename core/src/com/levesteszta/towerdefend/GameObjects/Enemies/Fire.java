package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import com.levesteszta.towerdefend.TileGrid;

public class Fire extends Enemy {
    static final char TYPE = 'f';
    public Fire(TileGrid grid){
        super(grid,32,150,20, getTexturesFromArea("ellenfel2.png",16));
    }
    //public void update(){};
    //public void draw(){};
    //public void remove(){};
}