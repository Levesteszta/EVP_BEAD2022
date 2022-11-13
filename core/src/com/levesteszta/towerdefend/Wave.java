package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import java.util.ArrayList;

public class Wave {
    private static int db = 0;
    private int maxdb;
    private float spawnTime, lastSpawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemies;

    public Wave(int db, float spawnTime, Enemy enemyType){
        this.maxdb = db;
        this.spawnTime = spawnTime;
        this.enemyType = enemyType;
        lastSpawnTime = 0f;
        enemies = new ArrayList<Enemy>(maxdb);
    }

    public void update(float deltaTime){
        lastSpawnTime += deltaTime;
        if(lastSpawnTime > spawnTime){
            spawn();
            lastSpawnTime = 0f;
            resetTimer();
        }

        for(Enemy e : enemies){
            e.update(deltaTime);
            e.draw();
        }
    }

    public void spawn(){
        if(db < maxdb){
            enemies.add(new Basic(enemyType.getTileGrid()));
            db++;
        }
    }

}
