package com.levesteszta.towerdefend.GameObjects.Towers;

import static com.levesteszta.towerdefend.helpers.Clock.*;
import static com.levesteszta.towerdefend.helpers.Artist.*;

import com.levesteszta.towerdefend.GameObjects.Enemies.*;
import com.levesteszta.towerdefend.MapGen.TileGrid;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class myBaseTower extends myTower {
    private static final int BASE_DAMAGE = 50;
    private static final int RANGE = 10;
    private static final float COOLDOWN = 5f;

    private float timeSinceLastFire;

    public myBaseTower(TileGrid grid, WaveManager enemies) {
        super(grid, enemies);
        this.setHp(100);
        this.setCost(50);
        this.setTextures(getTexturesFromArea("air.png",16)[0]);
        this.setRange(RANGE);
        this.defaultDmg = BASE_DAMAGE;
        this.timeSinceLastFire = BASE_DAMAGE;
        this.bullets = new ArrayList<Bullet>();

        //draw();
    }

    @Override
    public void attack(Enemy target) {
        timeSinceLastFire += Delta();
        if (timeSinceLastFire > COOLDOWN) {
            target.takeDamage(defaultDmg);
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
}