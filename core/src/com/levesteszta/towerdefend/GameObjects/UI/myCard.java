package com.levesteszta.towerdefend.GameObjects.UI;

import static com.levesteszta.towerdefend.helpers.Artist.*;

import java.util.ArrayList;
public class myCard {
    private float x, y, width, height;
    private ArrayList<String> towerStat;

    // HEIGHT = 6*TILE_SIZE ; 
    // WIDTH = 164 - 165 körül papíron a az ideális szélesség 

    public myCard(ArrayList<String> towerStat){
        this.towerStat = towerStat;
        this.height = 6*TILE_SIZE; this.width = 164;
    }

    
    /** 
     * Kirajzolja a kártyát
     * @param x
     * @param y
     */
    public void draw(int x, int y){
        this.x = x; this.y = y;
        //Card Background
        DrawTex(GetTexture("ui/card.png"), this.x+2, this.y+2, this.width-2, this.height-2);
        //Tower Name
        DrawTextCenterAllign(towerStat.get(0), (this.x), (this.height-25), (this.width),1);
        //Tower Stat
        DrawTex(GetTexture("towers/"+towerStat.get(1)), this.x+(this.width/2)-16, (this.height-80), 32);
        int paddingDown = 70;
        DrawText(
            String.format(" %-10s%5s%n %-12s%5s%n %-16s%5.0f%n %-13s%5s%n", 
                "DAMAGE ", towerStat.get(2), 
                "RANGE ", towerStat.get(3),
                "CD ", Float.parseFloat(towerStat.get(4)),
                "COST ", towerStat.get(5)
                ).toString()   
        , (this.x)+20, (this.height-25)-paddingDown,1);
    }

    
    /** 
     * Megnézi hogy a kártyára lett e nyomva az adott x,y koordinátán
     * @param x
     * @param y
     * @return boolean
     */
    public static boolean isClicked(int x, int y){
        if(x >= 2 && x<= WINDOW_WIDTH-2 && y <= 6*TILE_SIZE-2 && y >= 0)
            return true;
        return false;
    }

    
    /** 
     * yes
     * @param x
     * @param y
     * @return boolean
     */
    public boolean thisClicked(int x, int y){
        if(x>= this.x && x<= this.x+this.width && this.y<= this.y+this.height)
            return true;
        return false;
    }
    
    
    /** 
     * Visszaadja az árat
     * @return int
     */
    public int getCost() {
        return Integer.parseInt(this.towerStat.get(5));
    }
    
    /** 
     * Visszadja a kártya szélleségét
     * @return float
     */
    public float getWidth() {
        return width;
    }
    
    /** 
     * Visszadja a kártya magasságát
     * @return float
     */
    public float getHeight() {
        return height;
    }
    
    /** 
     * X koordinátája
     * @return float
     */
    public float getX() {
        return x;
    }
    
    /** 
     * Y koordinátája
     * @return float
     */
    public float getY() {
        return y;
    }
    
    
    /** 
     * Visszaadja a Kártyán szereplő torony nevét
     * @return String
     */
    public String getTowerTypeName(){
        return towerStat.get(0);
    }
   
}
