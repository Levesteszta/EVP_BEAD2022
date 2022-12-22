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
            int pick = RANDOM.nextInt(2);   //0 - 1 
            switch(pick){
                case 0:
                    enemies.add(new Basic(grid));break;
                case 1:
                    enemies.add(new Fire(grid));break;
                default:
                    break;
            }
            db++;
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public boolean isWaveCompleted(){
        return this.waveCompleted;
    }
    public Enemy getNextAlive(){
        for(Enemy e : enemies){
            if(!e.isDead())
                return e;
        }
        return null;
    }
}
