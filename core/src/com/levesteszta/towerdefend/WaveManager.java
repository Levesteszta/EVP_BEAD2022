package com.levesteszta.towerdefend;

import java.util.Random;
import static com.levesteszta.towerdefend.helpers.Clock.*;

public class WaveManager {
    private static Random RANDOM = new Random();
    private static int db = 0;
    private float lastSpawnTime;
    private int maxdb;

    private Wave currentWave;
    TileGrid grid;

    public WaveManager(int db, TileGrid grid){
        this.maxdb = db;
        this.grid = grid;
        this.lastSpawnTime = 0f;

        currentWave = null;
        newWave();
    }

    public void update(){
        if(!currentWave.isWaveCompleted()){
            System.out.println("Max: "+maxdb);
            System.out.println("Ez a "+db+". wave ");
            System.out.println("Wave adatok: "+currentWave.getEnemies().size()+" db enemy van...");
            currentWave.update();
        } 
        else{
            lastSpawnTime += Delta();
            if(db <= maxdb){
                if(lastSpawnTime > 10f){
                    lastSpawnTime = 0f;
                    newWave();
                }
                else System.out.println("Várni kell még");
            }
            else System.out.println("Vége...");
        }
    }

    private void newWave(){
        int waveEnemiesCount = 1 + RANDOM.nextInt(5); // minValue + rand.nextInt(maxValue(-1)) 1 - 5
        this.currentWave = new Wave(waveEnemiesCount, 5f, grid);
        db++;
    }

    public Wave getCurrentWave(){
        return this.currentWave;
    }
}
