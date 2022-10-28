package com.levesteszta.towerdefend.helper;

public enum TileType {
    
    Fold("proba", false),
    Ut("proba2",false);
    
    public String TextureName;
    public boolean isClickable;

    TileType(String TextureName, boolean isClickable ){
        this.TextureName = TextureName;
        this.isClickable = isClickable;
    }
}
