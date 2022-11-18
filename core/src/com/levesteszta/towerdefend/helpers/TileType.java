package com.levesteszta.towerdefend.helpers;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;

public enum TileType {
    DIRT0(Artist.getTexturesFromArea("terrain.png",16)[0], true,0),
    DIRT1(Artist.getTexturesFromArea("terrain.png",16)[1], true,0),
    DIRT2(Artist.getTexturesFromArea("terrain.png",16)[2], true, 0),
    DIRT3(Artist.getTexturesFromArea("terrain.png",16)[3], true, 0),
    DIRT4(Artist.getTexturesFromArea("terrain.png",16)[4], true, 0),
    DIRT5(Artist.getTexturesFromArea("terrain.png",16)[5], true, 0),
    DIRT6(Artist.getTexturesFromArea("terrain.png",16)[6], true, 0),
    DIRT7(Artist.getTexturesFromArea("terrain.png",16)[7], true, 2),
    DIRT8(Artist.getTexturesFromArea("terrain.png",16)[8], true, 2),
    DIRT9(Artist.getTexturesFromArea("terrain.png",16)[9], false, 2),
    DIRT10(Artist.getTexturesFromArea("terrain.png",16)[10], false,0),
    
    ROAD5(Artist.getTexturesFromArea("terrain.png",16)[11], false,1),
    ROAD0(Artist.getTexturesFromArea("terrain.png",16)[12],true,1),
    ROAD1(Artist.getTexturesFromArea("terrain.png",16)[13],true,1),
    ROAD2(Artist.getTexturesFromArea("terrain.png",16)[14],true,1),
    ROAD3(Artist.getTexturesFromArea("terrain.png",16)[15],true,1),
    ROAD4(Artist.getTexturesFromArea("terrain.png",16)[16],true,1);
    
    public Sprite sprite;
    public boolean isPlaceable;
    public int id;  //0-Fold, 1- UT 2- amire nem lehet rakni föld
    private static final Random RANDOM = new Random();
    private static final int SIZE = TileType.values().length;
    TileType(Sprite sprite, boolean isPlaceable,int id){
        this.sprite = sprite;
        this.isPlaceable = isPlaceable;
        this.id = id;
    }

    public static TileType getRandomDirt(){
        int pick = RANDOM.nextInt(10)+0;
        return TileType.values()[pick];
    }

    public static TileType getRandomRoad(){
        int pick = RANDOM.nextInt(SIZE-11)+11;
        return TileType.values()[pick];
    }

    public static TileType[] getTypes(){
        return TileType.values();
    }
}
