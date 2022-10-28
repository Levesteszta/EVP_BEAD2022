package com.levesteszta.towerdefend;

import com.badlogic.gdx.graphics.Texture;

// Ős
class Enemy {
    protected int x, y, size, health, damage;   //Pozicio, méret,Életerő,dmg;                      
    protected static float speed = 1.0f;        //Jelenleg a mozgás sebessége adott mindenkinél
    protected Texture textue;                   //Textura, késöbbiekben ez Sprite-ra cserélendő

    Enemy(int health, int damage){
        this.health = health;
        this.damage = damage;
    }


    public void draw(){

    }
}

// Ellenfél fajták
class Basic extends Enemy{
    public Basic(){
        super(500,100);
    }
}