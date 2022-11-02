package com.levesteszta.towerdefend.helpers;

import com.badlogic.gdx.graphics.Texture;

public enum TileType {
    
    Fold(Artist.GetTexture("proba.jpg"), false),
    Ut(Artist.GetTexture("proba2.jpg"),false);
    
    public Texture Texture;
    public boolean isClickable;

    TileType(Texture Texture, boolean isClickable ){
        this.Texture = Texture;
        this.isClickable = isClickable;
    }
}
