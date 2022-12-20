package com.levesteszta.towerdefend;

import java.util.ArrayList;
import java.util.Random;

import static com.levesteszta.towerdefend.helpers.Clock.*;

public class WaveManager {
    private static Random RANDOM = new Random();
    private static int db = 0;
    private int maxdb;
    TileGrid grid;
    public ArrayList<Wave> waves;

    public WaveManager(int db, TileGrid grid){
        this.maxdb = db;
        this.grid = grid;
        waves = new ArrayList<Wave>(db);
    }

    public void update(){
        spawn();
        for(Wave wave : waves){
            wave.update();
        }
    }

    public void spawn(){
        if(db < maxdb){
            int waveCount = 1 + RANDOM.nextInt(5 - 1 + 1); // minValue + rand.nextInt(maxValue - minValue + 1)
            waves.add(new Wave(waveCount, 5f, grid));
            db++;
        }
    }

    public ArrayList<Wave> getWaves() {
        return this.waves;
    }
}
