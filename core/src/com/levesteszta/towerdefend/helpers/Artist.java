package com.levesteszta.towerdefend.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.GL20;

public class Artist {
    public static final float WINDOW_WIDTH = 997f, WINDOW_HEIGHT = 706f;
    public static int TILE_SIZE = 32;
    static SpriteBatch batch = new SpriteBatch(1);
    public static void DrawTex(Texture texture, float x, float y, float size){
        texture.bind(0);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        batch.begin();
        batch.draw(texture, x, y, size, size);
        batch.end();
    }

    public static TextureRegion[][] getTexturesFromArea(String name){
        return new TextureRegion(new Texture(name)).split(16, 16);
    }

    public static Texture GetInternalTexture(String name){ 
        return new Texture(Gdx.files.internal(name));
    }

    public static Texture GetTexture(String name){
        return new Texture(name);
    }

    public static void Dispose(){
        batch.dispose();
    }
}
