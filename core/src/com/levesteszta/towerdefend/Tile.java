package com.levesteszta.towerdefend;

import static com.levesteszta.towerdefend.helpers.Artist.*;
import com.levesteszta.towerdefend.helpers.TileType;
import com.badlogic.gdx.graphics.Texture;

public class Tile {
    private float x, y, size;
    private TileType tile;
    private Texture texture;

    public Tile(float x, float y, float size, TileType tile){
        this.x = x;
        this.y = y;
        this.size = size;
        this.tile = tile;
        this.texture = tile.Texture;
    }

    public void draw(){
        DrawTex(this.texture, x, y, size);
    };

    // Tile
    public TileType getTile() {
        return tile;
    }
    public void setTile(TileType tile) {
        this.tile = tile;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x: "+x+", y: "+y+" , textureName: "+texture.getTextureData();    
    }
}