package com.levesteszta.towerdefend.GameObjects.UI;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import java.lang.reflect.ParameterizedType;

import com.levesteszta.towerdefend.GameObjects.Towers.myTower;

public class myCard {
    private float x, y, width, height;
    private int cost;
    private Class<myTower> tower;

    // HEIGHT = 6*TILE_SIZE ; 
    // WIDTH = 164 - 165 körül papíron a az ideális szélesség 

    // TODO: make this Müködöképes, beolvasás buzi gneric
    public myCard(Class<? extends myTower> tower){
        this.tower = (Class<myTower>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
        this.height = 6*TILE_SIZE; this.width = 164;
    }

    public void draw(int x, int y){
        this.x = x; this.y = y; 
        System.out.println("x: "+this.tower);
        
        //Card Background
        DrawTex(GetTexture("proba.jpg"), this.x+2, this.y+2, this.width-2, this.height-2);
        //Tower Name
        //DrawTextCenterAllign(this.tower, (this.x), (this.height-25), (this.width),1);
        //Tower Stat
    }
    
    public Class<?> getTower(){
        return this.tower;
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
