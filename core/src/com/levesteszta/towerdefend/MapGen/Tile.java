package com.levesteszta.towerdefend.MapGen;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.levesteszta.towerdefend.helpers.TileType;

public class Tile {
    private float x, y, size;
    private TileType tile;
    private Sprite sprite;

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

    // Tile
    public TileType getTile() {
        return tile;
    }
    public void setTile(TileType tile) {
        this.tile = tile;
    }
    public void setTileType(boolean bum) {
        tile.setAttackable(bum);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public int getXInd() {
        return (int) x/TILE_SIZE-2;
    }

    public int getYInd() {
        return (int) y/TILE_SIZE-6;
    }

    @Override
    public String toString() {
        return "x: "+x+", y: "+y+" , textureName: "+sprite.getTexture().getTextureData()+" id:"+tile.id;    
    }
}