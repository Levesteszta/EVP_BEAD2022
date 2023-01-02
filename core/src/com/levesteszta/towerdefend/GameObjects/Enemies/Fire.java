package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;

public class Fire extends Enemy {
    static final char TYPE = 'f';
    public Fire(TileGrid grid){
        super(grid,32,150,20,20, getTexturesFromArea("enemies/ellenfelFire.png",16));
        setWeakElemental('w');
    }
    //public void update(){};
    //public void draw(){};
    //public void remove(){};
}