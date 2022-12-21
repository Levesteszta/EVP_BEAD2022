package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import java.util.ArrayList;
import java.util.Random;

import com.levesteszta.towerdefend.GameObjects.Enemies.Basic;
import com.levesteszta.towerdefend.GameObjects.Enemies.Fire;
import com.levesteszta.towerdefend.GameObjects.Enemies.Enemy;
public class Wave {
    private static int db = 0;
    private static Random RANDOM = new Random();
    private int maxdb;
    private float spawnTime, lastSpawnTime;
    TileGrid grid;
    private ArrayList<Enemy> enemies;

    public Wave(int leastDb, float spawnTime, TileGrid grid){
        this.maxdb = leastDb;
        this.spawnTime = spawnTime;
        lastSpawnTime = 0f;
        enemies = new ArrayList<Enemy>(maxdb);
        this.grid = grid; 
    }

    public void update(){
        lastSpawnTime += Delta();
        System.out.println("lastSpawnTime: "+lastSpawnTime);
        if(lastSpawnTime > spawnTime){
            spawn();
            lastSpawnTime = 0f;
        }

        for(Enemy e : enemies){
            e.update();
            e.draw();
        }
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
}
