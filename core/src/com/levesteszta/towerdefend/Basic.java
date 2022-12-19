package com.levesteszta.towerdefend;
import static com.levesteszta.towerdefend.helpers.Artist.*;

public class Basic extends Enemy {
    public Basic(TileGrid grid){
        super(grid,32,100,10, getTexturesFromArea("ellenfel.png",16));
    }
}