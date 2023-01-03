package com.levesteszta.towerdefend.GameObjects.Enemies;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import java.util.ArrayList;
import java.util.Random;

import com.levesteszta.towerdefend.MapGen.TileGrid;
public class Wave {
    private static int db;
    private static Random RANDOM = new Random();
    private int maxdb;
    private float spawnTime, lastSpawnTime;
    TileGrid grid;
    private ArrayList<Enemy> enemies;
    private boolean waveCompleted;

    public Wave(int db, float spawnTime, TileGrid grid){
        this.db = 0;
        this.maxdb = db;
        this.spawnTime = spawnTime;
        this.grid = grid; 
        this.lastSpawnTime = 0f;
        this.enemies = new ArrayList<Enemy>();
        this.waveCompleted = false;

        spawn();
    }

    public void update(){
        boolean allEnemiesDied = true;
        lastSpawnTime += Delta();
        if(enemies.size() < maxdb){
            if(lastSpawnTime > spawnTime){
                spawn();
                lastSpawnTime = 0f;
            }
        }
        for(Enemy e : enemies){
            if(!e.isDead()){
                allEnemiesDied = false;
                e.update();
                e.draw();
            }
        }
        if(allEnemiesDied)
            waveCompleted = true;
    }

    public void spawn(){
        if(db < maxdb){
            int pick = RANDOM.nextInt(6);   //0 - 5
            switch(pick){
                case 0:
                    enemies.add(new Air(grid));break;
                case 1:
                    enemies.add(new Fire(grid));break;
                case 2:
                    enemies.add(new Electro(grid));break;
                case 3:
                    enemies.add(new Ice(grid));break;
                case 4:
                    enemies.add(new Water(grid));break;
                case 5:
                    enemies.add(new Geo(grid));break;
                default:
                    break;
            }
            db++;
        }
    }

    
    /** 
     * Visszadja a legenerált ellenfél tömböt 
     * @return ArrayList<Enemy>
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    
    /** 
     * Visszadaj hogy mindenki halott-e a "hullámból"
     * @return boolean
     */
    public boolean isWaveCompleted(){
        return this.waveCompleted;
    }
    
    /** 
     * Visszaadja a sorrendbe a következő élőt
     * @return Enemy
     */
    public Enemy getNextAlive(){
        for(Enemy e : enemies){
            if(!e.isDead())
                return e;
        }
        return null;
    }
}
