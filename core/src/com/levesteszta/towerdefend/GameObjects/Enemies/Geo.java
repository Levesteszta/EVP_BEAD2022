package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;

public class Geo extends Enemy {
    static final char TYPE = 'g';
    public Geo(TileGrid grid){
        super(grid,32,250,50,30, getTexturesFromArea("enemies/ellenfelElectro.png",16));
        setWeakElemental('i');
    }
    //public void update(){};
    //public void draw(){};
    //public void remove(){};
}
