package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;

public class Ice extends Enemy {
    static final char TYPE = 'i';
    public Ice(TileGrid grid){
        super(grid,32,90,10,8, getTexturesFromArea("enemies/ellenfelIce.png",16));
        setWeakElemental('f');
    }
    //public void update(){};
    //public void draw(){};
    //public void remove(){};
}
