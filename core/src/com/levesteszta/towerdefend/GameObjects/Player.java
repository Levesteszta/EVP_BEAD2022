package com.levesteszta.towerdefend.GameObjects;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.levesteszta.towerdefend.GameObjects.Enemies.WaveManager;
import com.levesteszta.towerdefend.GameObjects.Towers.*;
import com.levesteszta.towerdefend.MapGen.*;
import com.levesteszta.towerdefend.helpers.TileType;

public class Player {
    private static TileGrid map;
    private int health = 100;
    private int money;
    private WaveManager waves;
    private ArrayList<myTower> towers;

    public Player(TileGrid room, WaveManager waves){
        this.health = 100;
        map = room;
        this.towers = new ArrayList<myTower>();
        this.waves = waves;
    }

    public void update(){
        for(myTower tower : this.towers){
            tower.update();
        }

        //Input checking
        int tmpX = Gdx.input.getX();
        int tmpY = (int)WINDOW_HEIGHT - Gdx.input.getY();
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(map.isInCoords(tmpX, tmpY)){
                myTower tmp = new myWaterTower(map,waves);
                tmp.setStandingTile(map.getTileDataesByCoord(tmpX, tmpY));
                towers.add(tmp);
            }
        }

    }

    public void setTile(myTower tower, int x, int y){
        map.setTileDataes(x, y, TileType.DIRT10);
        System.out.println(map.getTileDataesByCoord(x, y).toString());
        //map.draw();
    }

    public int getMoney() {
        return this.money;
    }
    
    public void setMoney(int plusMoney){
        this.money += plusMoney;
    }

    public boolean haveEnought(int checkMoney){
        if(this.money > checkMoney)
            return true;
        return false;
    }
    public void costMoney(int costMoney){
        if(this.haveEnought(costMoney))
            this.money -= costMoney;
    }
    
    public void takeDamage(int damage){
        if(this.health > damage)
            this.health -= damage;
    }

    public boolean isGameOver() {
        if(this.health <= 0)
            return true;
        else return false;
    }
}
