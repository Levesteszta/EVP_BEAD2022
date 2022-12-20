/*package com.levesteszta.towerdefend;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

public abstract class Towers {

    public float x,y;
    public int atk, range, cost, health, defaultDMG;
    public char type;    // f - , i - , w - , e - 

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int damageDealt(char enemystate){
        int dmg = 0;
        switch (type) {
            case 'f':
                switch (enemystate) {
                    case 'i':
                        dmg = 4000;
                        break;
                    case 'w':
                        dmg = 3000;
                        break;
                    case 'e':
                        dmg = 2500;
                        break;
                    default:
                        dmg = 1000;
                        break;
                }
            break;
        
            case 'i':
                switch (enemystate) {
                    case 'f':
                        dmg = 500;
                        break;
                    case 'w':
                        dmg = 100;
                        break;
                    case 'e':
                        dmg = 100;
                        break;
                    default:
                        dmg = 100;
                        break;
                }
            break;

            case 'w':
                switch (enemystate) {
                    case 'f':
                        dmg = 150;
                        break;
                    case 'i':
                        dmg = 50;
                        break;
                    case 'e':
                        dmg = 50;
                        break;
                    default:
                        dmg = 50;
                        break;
                }
            break;

            case 'e':
                switch (enemystate) {
                    case 'f':
                        dmg = 375;
                        break;
                    case 'i':
                        dmg = 250;
                        break;
                    case 'w':
                        dmg = 250;
                        break;
                    default:
                        dmg = 250;
                        break;
                }
            break;
        }
        return dmg;
    }
}


class Fire extends Towers{

    public Fire(){
        this.atk = 1000;
        this.range = 4;
        this.cost = 120;
        this.type = 'f';
    }
}

class Ice extends Towers{

    public Ice(){
        this.atk = 100;
        this.range = 10;
        this.cost = 80;
        this.type = 'i';
    }
}

class Water extends Towers{

    public Water(){
        this.atk = 50;
        this.range = 2;
        this.cost = 100;
        this.type = 'w';
    }
}

class Electro extends Towers{

    public Electro(){
        this.atk = 250;
        this.range = 10;
        this.cost = 100;
        this.type = 'e';
    }
}

class Geo extends Towers{

    public Geo(){
        this.cost = 90;
        this.health = 2000;
        this.type = 'g';
    }
}

class Air extends Towers{

    public Air(){
        this.range = 6;
        this.cost = 120;
        this.type = 'a';
    }
}*/
