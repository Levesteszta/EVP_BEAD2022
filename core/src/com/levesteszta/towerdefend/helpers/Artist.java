package com.levesteszta.towerdefend.helpers;

import com.levesteszta.towerdefend.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;

public class Artist {
    //public static SpriteBatch batch = new SpriteBatch();
    public static final float WINDOW_WIDTH = 997f, WINDOW_HEIGHT = 706f;
    public static int TILE_SIZE = 32;

    public static void DrawTex(Texture texture, float x, float y, float size){
        texture.bind();
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        TowerDefend.batch.begin();
        TowerDefend.batch.draw(texture, x, y, size, size);
        TowerDefend.batch.end();
    }
    public static void DrawTex(Sprite sp, float x, float y, float size){
        TowerDefend.batch.begin();
        TowerDefend.batch.draw(sp, x, y, size, size);
        TowerDefend.batch.end();
    }

    public static Sprite[] getTexturesFromArea(String Filename, int size){
        Texture origin = GetTexture(Filename);
        int n = (int)(origin.getWidth()/size);
        Sprite[] sp = new Sprite[n];
        for(int i = 0; i < n; i++)
            sp[i] = new Sprite(origin,i*size,0,16,16);
        return sp;
    }

    public static Texture GetTexture(String Filename){
        AssetManager am = new AssetManager();
        am.load(Filename,Texture.class);
        am.finishLoading();
        return am.get(Filename);
    }

    public static void Dispose(){
        TowerDefend.batch.dispose();
    }
}
