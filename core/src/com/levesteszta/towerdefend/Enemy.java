package com.levesteszta.towerdefend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Ős
class Enemy {
    protected int x, y, size, health, damage;   //Pozicio, méret,Életerő,dmg;                      
    protected static float speed = 1.0f;        //Jelenleg a mozgás sebessége adott mindenkinél
    protected Texture texture;                  //Textura, késöbbiekben ez Sprite-ra cserélendő

    Enemy(int spawnX, int spawnY, int size ,int health, int damage, String texture){
        this.health = health;
        this.damage = damage;
        this.x = spawnX;
        this.y = spawnY;
        this.texture = new Texture(texture);
    }

    protected void remove(SpriteBatch sprite){
        sprite.dispose();
    }

    protected void draw(SpriteBatch sprite){
        sprite.draw(this.texture, x, y);
    };
}

// Ellenfél fajták
class Basic extends Enemy{
    public Basic(int spawnX, int spawnY){
        super(spawnX,spawnY,32,100,10,"ellen.png");
        System.out.println(health+" hp ");
    }
}