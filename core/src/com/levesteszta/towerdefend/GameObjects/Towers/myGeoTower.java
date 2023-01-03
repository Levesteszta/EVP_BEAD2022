package com.levesteszta.towerdefend.GameObjects.Towers;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.badlogic.gdx.math.Vector2;
import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;
import java.util.ArrayList;

public class myGeoTower extends myTower {
    private static final int BASE_DAMAGE = 0;
    private static final int RANGE = 0;
    private static final float COOLDOWN = 0f;
    private static final int COST = 20;
    private static final String textureName = "earth.png";

    private float timeSinceLastFire;

    public myGeoTower(TileGrid grid, WaveManager enemies) {
        super(grid, enemies);
        this.setName("Earth");
        this.setHp(100);
        this.setCost(COST);
        this.setTowerRange(RANGE);
        this.setDefaultDmg(BASE_DAMAGE);
        this.setTextures(getTexturesFromArea("towers/"+textureName,16)[0]);
        this.timeSinceLastFire = BASE_DAMAGE;
        this.bullets = new ArrayList<Bullet>();

        //draw();
    }

    @Override
    public void attack() {

    }

    @Override
    public void update() {
        draw();
    }

    @Override
    public ArrayList<String> getStats(){
        return new ArrayList<String>(){
            {
                add("Earth");
                add(textureName);
                add(String.valueOf(BASE_DAMAGE));
                add(String.valueOf(RANGE));
                add(String.valueOf(COOLDOWN));
                add(String.valueOf(COST));
            }
        };
    }
}