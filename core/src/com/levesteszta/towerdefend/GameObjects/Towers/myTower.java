package com.levesteszta.towerdefend.GameObjects.Towers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.MapGen.*;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import java.util.ArrayList;

public abstract class myTower {
    protected float x, y;
    protected TileGrid grid;
    protected Tile standingTile;
    protected Sprite texture;
    protected int hp, defaultDmg, range, cost;
    protected WaveManager enemies;
    protected Enemy target;
    protected ArrayList<Bullet> bullets;


    public myTower(TileGrid grid, WaveManager enemies){
        this.grid = grid;
        this.enemies = enemies;
    }
    // Tile -
    public void setStandingTile(Tile standingTile) {
        this.standingTile = standingTile;
        if(this.standingTile.getTile().id == 0){
            this.standingTile = standingTile;
            this.x = standingTile.getX();
            this.y = standingTile.getY();
        }
        else {
            this.standingTile = null;
            this.x = -1; this.y = -1;
            System.out.println("Nem jó a hely");
        }
    }
    public Tile getStandingTile() {
        return standingTile;
    }
    // Cost -
    public void setCost(int cost) {
        if(cost > 0)
            this.cost = cost;
    }
    public int getCost() {
        return cost;
    }
    // Hp -
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        if(hp > 0)
            this.hp = hp;
    }
    // X -
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    // Y -
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    // Texture
    public void setTextures(Sprite texture) {
        this.texture = texture;
    }
    // Range
    public void setRange(int range) {
        this.range = range;
    }

    public abstract void attack(Enemy target);
    public void update(){};
    public void draw(){
        DrawTex(this.texture, x, y, TILE_SIZE);
    };

    //public void checkInRange(int range){}
}
