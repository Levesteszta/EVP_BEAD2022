package com.levesteszta.towerdefend.GameObjects.Towers;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.badlogic.gdx.math.Vector2;
import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;
import java.util.ArrayList;

public class myAirTower extends myTower {
    private static final int BASE_DAMAGE = 10;
    private static final int RANGE = 5;
    private static final float COOLDOWN = 5f;
    private static final int COST = 50;
    private static final String textureName = "air.png";

    private float timeSinceLastFire;

    public myAirTower(TileGrid grid, WaveManager enemies) {
        super(grid, enemies);
        this.setName("Air");
        this.setHp(-1);
        this.setCost(COST);
        this.setTowerRange(RANGE);
        this.setDefaultDmg(BASE_DAMAGE);

        this.setTextures(getTexturesFromArea("towers/"+textureName,16)[0]);
        this.timeSinceLastFire = BASE_DAMAGE;
        this.bullets = new ArrayList<Bullet>();

        //draw();
    }

    @Override
    public void attack(Enemy target) {
        timeSinceLastFire += Delta();
        if (timeSinceLastFire > COOLDOWN) {
            target.takeDamage(BASE_DAMAGE);
            timeSinceLastFire = 0;
            bullets.add(new Bullet(GetSprite("bullet.png"),target, x, y, BASE_DAMAGE));
        }

        for(Bullet bullet : bullets) {
            bullet.update();
        }

        //draw();
    }

    @Override
    public void update() {
        if(enemies.getCurrentWave().getEnemies().size() > 0) {
            Enemy enemy = enemies.getCurrentWave().getNextAlive();
            if(enemy != null) {
                if(getRange(new Vector2(this.x, this.y), new Vector2(enemy.getX(), enemy.getY())) <= (TILE_SIZE * RANGE)){
                    this.attack(enemy);
                }
            }
        }
        draw();
    }

    @Override
    public ArrayList<String> getStats(){
        return new ArrayList<String>(){
            {
                add("Air");
                add(textureName);
                add(String.valueOf(BASE_DAMAGE));
                add(String.valueOf(RANGE));
                add(String.valueOf(COOLDOWN));
                add(String.valueOf(COST));
            }
        };
    }
}