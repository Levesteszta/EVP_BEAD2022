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

    
    /** 
     * Az ellenfél listát frissití hogy kit kellene lövöldözni
     * @param enemies
     */
    public void updateEnemyList(ArrayList<Enemy> enemies){
        this.enemies = enemies;
    }

    
    /** 
     * Beállítjuk pontosan csempéhez igazítva hova álíltsuk a figurát + megnézzük itt jó helyre e nyomja a játékos
     * @param standingTile
     */
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
    
    /** 
     * Visszadja a Csempét amin van a torony
     * @return Tile
     */
    public Tile getStandingTile() {
        return standingTile;
    }
    
    /** 
     * Beállítja a nevet
     * @param name
     */
    //Name
    public void setName(String name) {
        this.name = name;
    }
    
    /** 
     * Visszaadja a nevét a toronynak ( típusát )
     * @return String
     */
    public String getTowerName() {
        return name;
    }
    
    /** 
     * Visszaadja a lővés 'újratöltés' idejét
     * @return float
     */
    //Cooldown
    public float getCooldown() {
        return cd;
    }
    
    /** 
     * Beállítja a lővés 'újratöltés' idejét
     * @param cd
     */
    public void getCooldown(float cd) {
        this.cd = cd;
    }
    
    /** 
     * A sebzést állítja be
     * @param defaultDmg
     */
    //DMG
    public void setDefaultDmg(int defaultDmg) {
        this.defaultDmg = defaultDmg;
    }
    
    /** 
     * Visszadaja a sebzést
     * @return int
     */
    public int getDefaultDmg() {
        return defaultDmg;
    }
    
    /** 
     * Az árat állítja be, azért benn vna ha véletlen 0 alatti lenen hogy akkor hagyjuk inkább
     * @param cost
     */
    // Cost -
    public void setCost(int cost) {
        if(cost > 0)
            this.cost = cost;
    }
    
    /** 
     * Visszaadja az árat
     * @return int
     */
    public int getCost() {
        return cost;
    }
    
    /** 
     * Visszaadja az Életerőt
     * @return int
     */
    // Hp -
    public int getHp() {
        return hp;
    }
    
    /** 
     * Beállítja az életerőt
     * @param hp
     */
    public void setHp(int hp) {
        if(hp > 0)
            this.hp = hp;
    }
    
    /** 
     * X koordinátája
     * @return float
     */
    // X -
    public float getX() {
        return x;
    }
    
    /** 
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }
    
    /** 
     * Y koordinátája
     * @return float
     */
    // Y -
    public float getY() {
        return y;
    }
    
    /** 
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }
    
    /** 
     * Textura, kínézet beállítása
     * @param texture
     */
    // Texture
    public void setTextures(Sprite texture) {
        this.texture = texture;
    }
    
    /** 
     * Hatótáv beállítása
     * @param range
     */
    // Range
    public void setTowerRange(int range) {
        this.range = range;
    }
    
    /** 
     * Hatótáb lekérdezése
     * @return int
     */
    public int getTowerRange() {
        return range;
    }

    public abstract void attack();
    public abstract ArrayList<String> getStats();
    public void update(){};
    public void draw(){
        DrawTex(this.texture, x, y, TILE_SIZE);
    };

    
    /** Visszadja a legközelebbi Ellenfelet
     * @return Enemy
     */
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

    
    /** 
     * Visszaadja az Ellenfél és a Torony közti távolságot
     * @param e
     * @return float
     */
    protected float getActualDistance(Enemy e){
        float xDist = Math.abs(e.getX() - x);
        float yDist = Math.abs(e.getY() - y);
        return xDist + yDist;
    }

    public void clearBullets(){
        this.bullets.clear();
    }
    
    /** 
     * Visszadja hogy a Torony és az Ellenfél a Hatótávon belül van e 
     * @param e
     * @return boolean
     */
    public boolean checkInRange(Enemy e){
        float xDist = Math.abs(e.getX() - x);
        float yDist = Math.abs(e.getY() - y);
        if(xDist <= range*TILE_SIZE && yDist <= range*TILE_SIZE)
            return true;
        return false;
    }

}
