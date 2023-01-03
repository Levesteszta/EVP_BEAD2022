package com.levesteszta.towerdefend.GameObjects.Towers;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;
import java.util.ArrayList;

public class myElectroTower extends myTower {
    private static final int BASE_DAMAGE = 20;
    private static final int RANGE = 9;
    private static final float COOLDOWN = 2f;
    private static final int COST = 15;
    private static final String textureName = "electro.png";

    private float timeSinceLastFire;

    public myElectroTower(TileGrid grid, WaveManager enemies) {
        super(grid, enemies);
        this.setName("Electro");
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
        bullets.add(new Bullet(GetSprite("bullets/"+getTowerName()+"bullet.png"),target, x, y, BASE_DAMAGE,'e'));
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
    
    /** 
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getStats(){
        return new ArrayList<String>(){
            {
                add("Electro");
                add(textureName);
                add(String.valueOf(BASE_DAMAGE));
                add(String.valueOf(RANGE));
                add(String.valueOf(COOLDOWN));
                add(String.valueOf(COST));
            }
        };
    }
}