package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;

public class Air extends Enemy {
    static final char TYPE = 'b';
    public Air(TileGrid grid){
        super(grid,32,100,10,5, getTexturesFromArea("enemies/ellenfelAir.png",16));
        setWeakElemental('-');
    }
    //public void update(){};
    //public void draw(){};
    //public void remove(){};
}
