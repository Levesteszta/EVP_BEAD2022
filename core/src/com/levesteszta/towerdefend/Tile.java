package com.levesteszta.towerdefend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levesteszta.towerdefend.helper.TileType;

public class Tile {
    private float x, y, size;
    private TileType tile;
    private Texture texture;

    public Tile(float x, float y, float size, TileType tile){
        this.x = x;
        this.y = y;
        this.size = size;
        this.tile = tile;
        this.texture = new Texture(tile.TextureName+".jpg");
    }

    public void draw(SpriteBatch sprite){
        sprite.draw(this.texture, x, y, size, size);
    };

    // Tile
    public TileType getTile() {
        return tile;
    }
    public void setTile(TileType tile) {
        this.tile = tile;
    }
}