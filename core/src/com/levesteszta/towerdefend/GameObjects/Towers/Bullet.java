package com.levesteszta.towerdefend.GameObjects.Towers;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import static com.levesteszta.towerdefend.helpers.Clock.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.levesteszta.towerdefend.GameObjects.Enemies.Enemy;

public class Bullet {
    private static final float SPEED = 550f;
    private Sprite texture;
    private float x, y, xVelocity, yVelocity;
    private int damage;
    private Enemy target;

    public Bullet(Sprite texture,Enemy target, float x, float y, int damage){
        this.texture = texture;
        this.x = x; this.y = y;
        this.damage = damage;
        this.target = target;
        this.xVelocity =0f; this.yVelocity = 0f;
        
        calcDirection();
    }

    public void calcDirection(){
        // Saját Artsits.Range()-t nem tudom használni mert átlóba is nézné
        float totalAllowedMove = 1f;   // maximálisan Megengedett "közök", 
        float xDistanceFromTarget = Math.abs(target.getX() - x);    
        float yDistanceFromTarget = Math.abs(target.getY() - y);    
        float totalDistance = xDistanceFromTarget + yDistanceFromTarget;
        float xPercentMove = xDistanceFromTarget / totalDistance;
        xVelocity = xPercentMove;   //Mennyivel kell x mentén lőni a golyónkat
        yVelocity = totalAllowedMove - xPercentMove;    // Mennyivel kell y mentén lőni a golyónkat
        // Ha a golyó 'középontjához' képest negatív irányba lenne akkor arra menjen
        if(target.getX() < x)
            xVelocity *= -1;
        if(target.getY() < y)
            yVelocity *= -1;
    }

    public void update(){
        this.x += xVelocity * Delta() * SPEED;
        this.y += yVelocity * Delta() * SPEED;
        draw();
    }

    public void draw(){
        DrawTex(texture, x+((TILE_SIZE/2) / 2), y+((TILE_SIZE/2) / 2), TILE_SIZE/2);
    }
}
