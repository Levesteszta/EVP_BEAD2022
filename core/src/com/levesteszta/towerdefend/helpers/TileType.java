package com.levesteszta.towerdefend.helpers;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum TileType {
    DIRT0(Artist.getTexturesFromArea("terrain.png",16)[0], 0), // sima föld  
    DIRT1(Artist.getTexturesFromArea("terrain.png",16)[1], 0), // sima föld
    DIRT2(Artist.getTexturesFromArea("terrain.png",16)[2],0),  // sima föld
    DIRT3(Artist.getTexturesFromArea("terrain.png",16)[3],0),  // sima föld
    DIRT4(Artist.getTexturesFromArea("terrain.png",16)[4],0),  // sima föld
    DIRT5(Artist.getTexturesFromArea("terrain.png",16)[5],0),  // sima föld
    DIRT6(Artist.getTexturesFromArea("terrain.png",16)[6],0),  // sima föld
    DIRT7(Artist.getTexturesFromArea("terrain.png",16)[7],2),  // tó vagy fa
    DIRT8(Artist.getTexturesFromArea("terrain.png",16)[8],2),  // tó vagy fa
    DIRT9(Artist.getTexturesFromArea("terrain.png",16)[9], 2), // tó vagy fa
    DIRT10(Artist.getTexturesFromArea("terrain.png",16)[10],2),// tó vagy fa
    
    ROAD5(Artist.getTexturesFromArea("terrain.png",16)[11],1), // Út
    ROAD0(Artist.getTexturesFromArea("terrain.png",16)[12],1), // Út
    ROAD1(Artist.getTexturesFromArea("terrain.png",16)[13],1), // Út
    ROAD2(Artist.getTexturesFromArea("terrain.png",16)[14],1), // Út
    ROAD3(Artist.getTexturesFromArea("terrain.png",16)[15],1), // Út
    ROAD4(Artist.getTexturesFromArea("terrain.png",16)[16],1); // Út
    
    public Sprite sprite;
    public int id;  //0-Fold, 1-UT 2-amire nem lehet rakni föld
    private static final Random RANDOM = new Random();
    private static final int SIZE = TileType.values().length;
    TileType(Sprite sprite,int id){
        this.sprite = sprite;
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
