package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;

public class Basic extends Enemy {
    static final char TYPE = 'b';
    public Basic(TileGrid grid){
        super(grid,32,100,10, getTexturesFromArea("enemies/ellenfel.png",16));
    }
    //public void update(){};
    //public void draw(){};
    //public void remove(){};
}
