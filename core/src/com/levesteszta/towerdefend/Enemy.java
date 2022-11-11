package com.levesteszta.towerdefend;

import com.badlogic.gdx.graphics.Texture;
import static com.levesteszta.towerdefend.helpers.Artist.*;

// Ős
class Enemy {
    protected static float SPEED = 1.0f, MULT = 0.2f;        //Jelenleg a mozgás sebessége adott mindenkinél, egy multiplifáció hogy ne szaladjon ki , inkább csak sétáljon nyugodtan
    protected float x, y; 
    protected Tile startTile;
    protected int size, health, damage;   //Pozicio, méret,Életerő,dmg;                      
    protected Texture texture;                  //Textura, késöbbiekben ez Sprite-ra cserélendő
    private boolean first = true;

    Enemy(Tile spawnTile, int size ,int health, int damage, Texture texture){
        this.health = health;
        this.damage = damage;
        this.startTile = spawnTile;
        this.x = spawnTile.getX();
        this.y = spawnTile.getY();
        this.texture = texture;
    }

    protected void remove(){
        Dispose();
    }

    protected void setX(float deltaX){
        this.x += (deltaX + SPEED) * MULT;
    }

    protected void update(float deltaX){
        if(first)
            first = false;
        else
            this.setX(deltaX);
    }

    protected void draw(){
        DrawTex(this.texture, x, y, TILE_SIZE);
        System.out.println("-->Változás poziciója Korodinátákban: "+x+" - "+y+"\r\n");
    };

    protected Tile getStartTile() {
        return startTile;
    }
}

// Ellenfél fajták
class Basic extends Enemy{
    public Basic(Tile spawnTile){
        super(spawnTile,32,100,10, GetTexture("ellen.png"));
        System.out.println(" Poziciója Korodinátákban: "+x+" - "+y+";\r\n Életereje: "+health+" , Sebzése: "+damage);
    }
}