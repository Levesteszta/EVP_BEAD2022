package com.levesteszta.towerdefend;

import java.util.ArrayList;

public class Wave {
    private int db;
    private float spawnTime, lastSpawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemies;

    public Wave(int db, float spawnTime, Enemy enemyType){
        this.db = db;
        this.spawnTime = spawnTime;
        this.enemyType = enemyType;
        lastSpawnTime = 0f;
        enemies = new ArrayList<Enemy>();
    }

    public void update(float deltaTime){
        lastSpawnTime += deltaTime;
        if(lastSpawnTime > spawnTime){
            spawn();
            lastSpawnTime = 0f;
        }

        for(Enemy e : enemies){
            e.update(deltaTime);
            e.draw();
        }
        System.out.print("]]]] Last: "+lastSpawnTime+" :: "+deltaTime+"\r\n");
    }

    public void spawn(){
        enemies.add(new Basic(enemyType.getStartTile()));
    }

}
