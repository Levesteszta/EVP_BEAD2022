package com.levesteszta.towerdefend.helper;

public enum TileType {
    
    Fold("proba.jpg", false),
    Ut("proba2.jpg",false);
    
    public String TextureName;
    public boolean isClickable;

    TileType(String TextureName, boolean isClickable ){
        this.TextureName = TextureName;
        this.isClickable = isClickable;
    }
}
