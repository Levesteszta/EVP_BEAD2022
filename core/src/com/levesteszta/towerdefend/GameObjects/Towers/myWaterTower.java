package com.levesteszta.towerdefend.GameObjects.Towers;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;

import java.util.ArrayList;

public class myWaterTower extends myTower {
    private static final int BASE_DAMAGE = 5;
    private static final int RANGE = 10;
    private static final float COOLDOWN = 3f;
    private static final int COST = 10;
    private static final String textureName = "water.png";

    private float timeSinceLastFire;

    public myWaterTower(TileGrid grid, WaveManager enemies) {
        super(grid, enemies);
        this.setName("Water");
        this.setHp(-1);
        this.setCost(COST);
        this.setTowerRange(RANGE);
        this.setDefaultDmg(BASE_DAMAGE);
        this.setTextures(getTexturesFromArea("towers/"+textureName,16)[0]);
        this.timeSinceLastFire = BASE_DAMAGE;
        this.bullets = new ArrayList<Bullet>();

        //draw();
    }

    public void attack(){
        timeSinceLastFire = 0;
        bullets.add(new Bullet(GetSprite("bullets/"+getTowerName()+"bullet.png"),target, x, y, BASE_DAMAGE,'w'));
        //draw();
    }

    public void update() {
        if(!targeted)
            target = getClosestEnemy();
        if(target == null || target.isDead())
            targeted = false;
        
        timeSinceLastFire += Delta();
        if(targeted && target != null)
            if(timeSinceLastFire > COOLDOWN) 
                this.attack();

        for(Bullet bullet : bullets)
            bullet.update();
        draw();
    }

    @Override
    public ArrayList<String> getStats(){
        return new ArrayList<String>(){
            {
                add("Water");
                add(textureName);
                add(String.valueOf(BASE_DAMAGE));
                add(String.valueOf(RANGE));
                add(String.valueOf(COOLDOWN));
                add(String.valueOf(COST));
            }
        };
    }
}