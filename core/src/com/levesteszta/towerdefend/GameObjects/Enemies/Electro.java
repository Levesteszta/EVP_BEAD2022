package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.MapGen.TileGrid;

public class Electro extends Enemy {
    static final char TYPE = 'e';
    public Electro(TileGrid grid){
        super(grid,32,110,25,15, getTexturesFromArea("enemies/ellenfelElectro.png",16));
        setWeakElemental('a');
    }
    //public void update(){};
    //public void draw(){};
    //public void remove(){};
}
