package com.levesteszta.towerdefend;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.levesteszta.towerdefend.GameObjects.Enemies.Enemy;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import static com.levesteszta.towerdefend.helpers.Clock.*;

import java.util.ArrayList;

public abstract class myTower {
    protected float x, y;
    protected TileGrid grid;
    protected Tile standingTile;
    protected Sprite[] textures;
    protected int hp, defaultDmg, range, cost;
    protected ArrayList<Enemy> enemies;
    protected Enemy target;


    public myTower(TileGrid grid, ArrayList<Enemy> enemies){
        this.grid = grid;
        this.enemies = enemies;
    }
    // Tile -
    public void setStandingTile(Tile standingTile) {
        this.standingTile = standingTile;
        this.x = standingTile.getX();
        this.y = standingTile.getY();
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
    public void setTextures(Sprite[] textures) {
        this.textures = textures;
    }
    // Range
    public void setRange(int range) {
        this.range = range;
    }

    public abstract void attack(Enemy target);
    public void update(){};
    protected void draw(){
        DrawTex(this.textures[0], x, y, TILE_SIZE);
    };

    public void checkInRange(int range){

    }
}

class BaseTower extends myTower {
    private static final int BASE_DAMAGE = 2;
    private static final int RANGE = 10;
    private static final float COOLDOWN = 240f;

    private float timeSinceLastFire;

    public BaseTower(TileGrid grid, ArrayList<Enemy> enemies) {
        super(grid, enemies);
        this.setHp(100);
        this.setCost(50);
        this.setTextures(getTexturesFromArea("air.png",16));
        this.setRange(RANGE);
        this.defaultDmg = BASE_DAMAGE;
        this.timeSinceLastFire = BASE_DAMAGE;
    }

    @Override
    public void attack(Enemy target) {
      if (timeSinceLastFire >= COOLDOWN) {
        target.takeDamage(defaultDmg);
        timeSinceLastFire = 0;
      }
    }

    @Override
    public void update() {
        timeSinceLastFire += Delta();
        if(enemies.size() > 0) {
            if(getRange(new Vector2(this.x, this.y), new Vector2(enemies.get(0).x, enemies.get(0).y)) <= (TILE_SIZE * RANGE)){
                this.attack(enemies.get(0));
                System.out.println("Kapta a parasztja! ");
            }
        }
    }
}
