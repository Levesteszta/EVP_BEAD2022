package com.levesteszta.towerdefend.GameObjects.Enemies;

import java.util.Random;

import com.levesteszta.towerdefend.GameObjects.Player;
import com.levesteszta.towerdefend.GameObjects.Towers.myTower;
import com.levesteszta.towerdefend.MapGen.TileGrid;

import static com.levesteszta.towerdefend.helpers.Clock.*;

public class WaveManager {
    private static Random RANDOM = new Random();
    private static int db = 0;
    private float lastSpawnTime;
    private int maxdb, RANDOM_MAX_TICK;

    private Wave currentWave;
    TileGrid grid;

    public WaveManager(int db, TileGrid grid){
        this.maxdb = db;
        this.grid = grid;
        this.lastSpawnTime = 0f;
        this.RANDOM_MAX_TICK = 5;
        currentWave = null;
        newWave();
    }

    public void update(){
        if(!currentWave.isWaveCompleted()){
            currentWave.update();
        } 
        else{
            lastSpawnTime += Delta();
            if(db <= maxdb){
                if(lastSpawnTime > 10f){
                    lastSpawnTime = 0f;
                    newWave();
                }
                else {
                    for(myTower x : Player.getTower()){
                        x.clearBullets();
                    }
                    System.out.println("Várni kell még");
                }
            }
            // TODO: WinScreen?
            else System.out.println("Vége...");
        }
    }

    private void newWave(){
        if(db%2==0 && db >= RANDOM_MAX_TICK+2)
            this.RANDOM_MAX_TICK += 2;
        int waveEnemiesCount = 1 + RANDOM.nextInt(RANDOM_MAX_TICK); // minValue + rand.nextInt(maxValue(-1)) 1 - MAX
        this.currentWave = new Wave(waveEnemiesCount, 5f, grid);
        db++;
    }

    public Wave getCurrentWave(){
        return this.currentWave;
    }
}
