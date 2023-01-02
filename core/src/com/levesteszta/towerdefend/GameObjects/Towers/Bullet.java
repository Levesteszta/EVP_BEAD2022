package com.levesteszta.towerdefend.GameObjects.Towers;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import static com.levesteszta.towerdefend.helpers.Clock.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.levesteszta.towerdefend.GameObjects.Enemies.Enemy;

public class Bullet {
    private static final float SPEED = 550f;
    private Sprite texture;
    private boolean alive;
    private float x, y, xVelocity, yVelocity;
    private int damage, size;
    private Enemy target;

    public Bullet(Sprite texture,Enemy target, float x, float y, int damage){
        this.texture = texture;
        this.x = x; this.y = y;
        this.damage = damage;
        this.target = target;
        this.alive = true;
        this.size = (int)(x+((TILE_SIZE/2) / 2));
        this.xVelocity =0f; this.yVelocity = 0f;
        

        if(target != null)
            calcDirection();
    }

    public void calcDirection(){
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
        if(alive){
            if(target != null){
                this.x += xVelocity * Delta() * SPEED;
                this.y += yVelocity * Delta() * SPEED;
                if(CheckCollision(x, y, size, size, target.getX(), target.getY(), TILE_SIZE, TILE_SIZE)){
                    target.takeDamage(damage);
                    alive = false;
                }
                draw();
            }
        }
    }

    public void draw(){
        DrawTex(texture, x+((TILE_SIZE/2) / 2), y+((TILE_SIZE/2) / 2), TILE_SIZE/2);
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
