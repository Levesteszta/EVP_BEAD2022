package com.levesteszta.towerdefend.GameObjects.Towers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.MapGen.*;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import java.util.ArrayList;

public abstract class myTower {
    //Tower Stat
    private int hp, defaultDmg, range, cost;
    private String name;
    private float cd;

    //Artist Data
    protected float x, y;
    protected TileGrid grid;
    protected Tile standingTile;
    protected Sprite texture;

    //Attacking Data
    protected WaveManager waves;
    protected ArrayList<Enemy> enemies;
    protected Enemy target;
    protected ArrayList<Bullet> bullets;
    protected boolean targeted;

    public myTower(TileGrid grid, WaveManager waves){
        this.grid = grid;
        this.waves = waves;
        this.enemies = waves.getCurrentWave().getEnemies();

        this.targeted = false;
        this.target = getClosestEnemy();
    }

    public void updateEnemyList(ArrayList<Enemy> enemies){
        this.enemies = enemies;
    }

    // Tile -
    public void setStandingTile(Tile standingTile) {
        this.standingTile = standingTile;
        if(this.name != "Earth"){
            if(this.standingTile.getTile().id == 0){
                this.standingTile = standingTile;
                this.x = standingTile.getX();
                this.y = standingTile.getY();
            }
            else {
                this.standingTile = null;
                this.x = -1; this.y = -1;
            }
        }
        if(this.name == "Earth"){
            if(this.standingTile.getTile().id == 1){
                this.standingTile = standingTile;
                this.x = standingTile.getX();
                this.y = standingTile.getY();
                standingTile.setTileType(true);
            }
            else {
                this.standingTile = null;
                this.x = -1; this.y = -1;
            }
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

    public abstract void attack();
    public abstract ArrayList<String> getStats();
    public void update(){};
    public void draw(){
        DrawTex(this.texture, x, y, TILE_SIZE);
    };

    protected Enemy getClosestEnemy(){
        Enemy closestEnemy = null;
        float closestDistance = 100000;
        for(Enemy e : enemies){
            if(checkInRange(e) && getActualDistance(e) < closestDistance){
                closestDistance = getActualDistance(e);
                closestEnemy = e;
            }
        }
        if(closestEnemy != null){
            targeted = true;
            System.out.println("Closest enemy: "+closestEnemy.getID());
        }
        return closestEnemy;
    }

    protected float getActualDistance(Enemy e){
        float xDist = Math.abs(e.getX() - x);
        float yDist = Math.abs(e.getY() - y);
        return xDist + yDist;
    }

    public void clearBullets(){
        this.bullets.clear();
    }
    public boolean checkInRange(Enemy e){
        float xDist = Math.abs(e.getX() - x);
        float yDist = Math.abs(e.getY() - y);
        if(xDist <= range*TILE_SIZE && yDist <= range*TILE_SIZE)
            return true;
        return false;
    }

}
