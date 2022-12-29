package com.levesteszta.towerdefend.GameObjects.UI;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import java.util.ArrayList;
public class myCard {
    private float x, y, width, height;
    private int cost;
    private ArrayList<String> towerStat;

    // HEIGHT = 6*TILE_SIZE ; 
    // WIDTH = 164 - 165 körül papíron a az ideális szélesség 

    //TODO: make KÁRTYÁK MÜKÖDÖKÉPESRE :SOB:
    public myCard(ArrayList<String> towerStat){
        this.towerStat = towerStat;
        this.height = 6*TILE_SIZE; this.width = 164;
    }

    public void draw(int x, int y){
        this.x = x; this.y = y;
        //Card Background
        DrawTex(GetTexture("proba.jpg"), this.x+2, this.y+2, this.width-2, this.height-2);
        //Tower Name
        DrawTextCenterAllign(towerStat.get(0), (this.x), (this.height-25), (this.width),1);
        //Tower Stat
        int paddingDown = 40;
        for(int i = 2; i < towerStat.size(); i++){
            DrawText(towerStat.get(i), (this.x)+20, (this.height-25)-paddingDown,1);
            paddingDown+= 20;
        }
    }
    
    public int getCost() {
        return this.cost;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
   
}
