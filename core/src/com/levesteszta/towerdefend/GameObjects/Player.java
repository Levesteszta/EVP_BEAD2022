package com.levesteszta.towerdefend.GameObjects;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.levesteszta.towerdefend.GameObjects.Enemies.WaveManager;
import com.levesteszta.towerdefend.GameObjects.Towers.*;
import com.levesteszta.towerdefend.GameObjects.UI.DeckofCard;
import com.levesteszta.towerdefend.GameObjects.UI.myCard;
import com.levesteszta.towerdefend.MapGen.*;
import com.levesteszta.towerdefend.helpers.Clock;
import com.levesteszta.towerdefend.helpers.TileType;

public class Player {
    private static TileGrid map;
    private static float health, startHealth; 
    private static int money;
    private static ArrayList<myTower> towers;
    private String selectedTower = "";
    private WaveManager waves;
    private DeckofCard deck;
    private int selectedTowerPrice = 0;

    public Player(TileGrid room, WaveManager waves) throws Throwable{
        health = 100;
        money = 50;
        startHealth = health;
        map = room;
        towers = new ArrayList<myTower>();
        this.waves = waves;
        deck = new DeckofCard(room, waves);
    }

    public void update(){

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Clock.Stop();
        }

        //TowerSelection Top-Left Corner 
        DrawText("Selected Tower: "+selectedTower, 15, WINDOW_HEIGHT-15, 1);
        
        //BaseHP Top-Middle Corner
        float hpPercent = health / startHealth;
        DrawTex(GetTexture("ui/healthbar/hpbg.png"), (WINDOW_WIDTH/2)-(TILE_SIZE*3), WINDOW_HEIGHT-40, (TILE_SIZE*6),25);
        DrawTex(GetTexture("ui/healthbar/hpfill.png"), (WINDOW_WIDTH/2)-(TILE_SIZE*3), WINDOW_HEIGHT-40, (TILE_SIZE*6) * hpPercent,25);
        DrawTex(GetTexture("ui/healthbar/hpborder.png"), (WINDOW_WIDTH/2)-(TILE_SIZE*3), WINDOW_HEIGHT-40, (TILE_SIZE*6),25);
        DrawTextCenterAllign(String.valueOf((int)(hpPercent*100))+" %", (WINDOW_WIDTH/2)-(TILE_SIZE*3)-2, WINDOW_HEIGHT-22,(TILE_SIZE*6), 1);
        
        //Money Top-Right
        DrawTex(GetTexture("ui/money.png"), WINDOW_WIDTH-100, WINDOW_HEIGHT-25-5, 25);
        DrawText(String.valueOf(money)+" $", WINDOW_WIDTH-60, WINDOW_HEIGHT-13, 1);

        for(myTower tower : towers){
            tower.update();
            tower.updateEnemyList(waves.getCurrentWave().getEnemies());
        }
        deck.draw();

        //Input checking
        int tmpX = Gdx.input.getX();
        int tmpY = (int)WINDOW_HEIGHT - Gdx.input.getY();
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            selectedTowerPrice = 0;
            selectedTower = "";
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(map.isInCoords(tmpX, tmpY)){
                myTower tmp;
                if(this.haveEnought(selectedTowerPrice)){
                    switch(selectedTower){
                        case "Air":
                            tmp = new myAirTower(map,waves);break;
                        case "Fire":
                            tmp = new myFireTower(map,waves);break;
                        case "Electro":
                            tmp = new myElectroTower(map,waves);break;
                        case "Water":
                            tmp = new myWaterTower(map,waves);break;
                        case "Ice":
                            tmp = new myIceTower(map,waves);break;
                        case "Earth":
                            tmp = new myGeoTower(map,waves);break;
                        default: tmp = null;
                    }
                    if(tmp != null){
                        tmp.setStandingTile(map.getTileDataesByCoord(tmpX, tmpY));
                        if(tmp.getStandingTile() != null && tmp.getStandingTile().getIsTower() == false){
                            costMoney(selectedTowerPrice);
                            map.getTileDataesByCoord(tmpX, tmpY).setIsTower(true);
                            towers.add(tmp);
                        }
                    }
                }
            }
            if(myCard.isClicked(tmpX, tmpY)){
                myCard tmp = deck.getInCoord(tmpX, tmpY);
                selectedTower = tmp.getTowerTypeName();
                selectedTowerPrice = tmp.getCost(); 
            }else {
                //TODO: Notification for no money
            }
        }

    }

    
    /** 
     * Be??ll??tja az adott Csemp??t az adott toronyra
     * @param tower
     * @param x
     * @param y
     */
    public void setTile(myTower tower, int x, int y){
        map.setTileDataes(x, y, TileType.DIRT10);
        System.out.println(map.getTileDataesByCoord(x, y).toString());
        //map.draw();
    }

    
    /** 
     * Visszadja a p??nz??nket
     * @return int
     */
    public int getMoney() {
        return money;
    }
    
    
    /** 
     * Hozz??adja a p??nzt a jelenlegihez
     * @param plusMoney
     */
    public static void setMoney(int plusMoney){
        money += plusMoney;
    }

    
    /** 
     * Megvizsg??ljuk a p??nz??nk el??g e a vizsg??lt ??rt??khez
     * @param checkMoney
     * @return boolean
     */
    public boolean haveEnought(int checkMoney){
        if(money >= checkMoney)
            return true;
        return false;
    }
    
    /** 
     * Kivonjuka p??nz??nkb??l 
     * @param costMoney
     */
    public void costMoney(int costMoney){
        money -= costMoney;
    }
    
    
    /** 
     * B??zisunk sebz??snek van kit??ve, ha v??letlen meghaln??nk akkor feldobjuk a pacskert
     * @param damage
     */
    public static void takeDamage(int damage){
        if(health > damage)
            health -= damage;
        else
            health = -1;
    }

    
    /** 
     * Visszaadja a p??ly??n jelenl??v?? tornyokat
     * @return ArrayList<myTower>
     */
    public static ArrayList<myTower> getTower(){
        return towers;
    }

    
    /** 
     * Visszaadja hogy ??ppen v??ge van e a j??t??knak
     * @return boolean
     */
    public boolean isGameOver() {
        if(health <= 0)
            return true;
        else return false;
    }
}
