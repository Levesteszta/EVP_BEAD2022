package com.levesteszta.towerdefend.MapGen;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.levesteszta.towerdefend.helpers.TileType;

public class Tile {
    private float x, y, size;
    private TileType tile;
    private Sprite sprite;
    private boolean isTower = false;

    public Tile(float x, float y, float size, TileType tile){
        this.x = x;
        this.y = y;
        this.size = size;
        this.tile = tile;
        this.sprite = tile.sprite;
    }

    public void draw(){
        DrawTex(this.sprite, x, y, size);
    };

    
    /** 
     * @return TileType
     */
    // Tile
    public TileType getTile() {
        return tile;
    }
    
    /** 
     * @param tile
     */
    public void setTile(TileType tile) {
        this.tile = tile;
    }
    
    /** 
     * @param bum
     */
    public void setTileType(boolean bum) {
        tile.setAttackable(bum);
    }

    
    /** 
     * @param isTower
     */
    public void setIsTower(boolean isTower) {
        this.isTower = isTower;
    }
    
    
    /** 
     * @return boolean
     */
    public boolean getIsTower() {
        return this.isTower;
    }

    
    /** 
     * @return float
     */
    public float getX() {
        return x;
    }

    
    /** 
     * @return float
     */
    public float getY() {
        return y;
    }
    
    /** 
     * @return int
     */
    public int getXInd() {
        return (int) x/TILE_SIZE-2;
    }

    
    /** 
     * @return int
     */
    public int getYInd() {
        return (int) y/TILE_SIZE-6;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "x: "+x+", y: "+y+" , textureName: "+sprite.getTexture().getTextureData()+" id:"+tile.id;    
    }
}