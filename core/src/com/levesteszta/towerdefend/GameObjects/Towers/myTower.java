package com.levesteszta.towerdefend.GameObjects.Towers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.MapGen.*;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import java.util.ArrayList;

public abstract class myTower {
    private int hp, defaultDmg, range, cost;
    private String name;
    private float cd;
    protected float x, y;
    protected TileGrid grid;
    protected Tile standingTile;
    protected Sprite texture;
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
    //Name
    public void setName(String name) {
        this.name = name;
    }
    public String getTowerName() {
        return name;
    }
    //Cooldown
    public float getCooldown() {
        return cd;
    }
    public void getCooldown(float cd) {
        this.cd = cd;
    }
    //DMG
    public void setDefaultDmg(int defaultDmg) {
        this.defaultDmg = defaultDmg;
    }
    public int getDefaultDmg() {
        return defaultDmg;
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
    public void setTowerRange(int range) {
        this.range = range;
    }
    public int setTowerRange() {
        return range;
    }
    public ArrayList<String> getStats(){
        return new ArrayList<String>(){
            {
                add(name);
                add(String.valueOf(defaultDmg));
                add(String.valueOf(range));
                add(String.valueOf(cd));
                add(String.valueOf(cost));
            }
        };
    }
    public abstract void attack(Enemy target);
    public void update(){};
    public void draw(){
        DrawTex(this.texture, x, y, TILE_SIZE);
    };

    //public void checkInRange(int range){}
}
