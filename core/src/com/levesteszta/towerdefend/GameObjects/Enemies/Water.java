package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;

public class Water extends Enemy {
    static final char TYPE = 'w';
    public Water(TileGrid grid){
        super(grid,32,120,15,15, getTexturesFromArea("enemies/ellenfelWater.png",16));
        setWeakElemental('e');
    }
    //public void update(){};
    //public void draw(){};
    //public void remove(){};
}
