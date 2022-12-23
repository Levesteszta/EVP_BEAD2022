package com.levesteszta.towerdefend.helpers;

import com.levesteszta.towerdefend.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.GL20;

public class Artist {
    //public static SpriteBatch batch = new SpriteBatch();
    private static AssetManager am = new AssetManager();
    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>(Gdx.files.internal("uiskin.json"),Skin.class,new SkinLoader.SkinParameter(Gdx.files.internal("uiskin.atlas").path()));
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
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        TowerDefend.batch.begin();
        TowerDefend.batch.draw(sp, x, y, size, size);
        TowerDefend.batch.end();
    }

    public static void DrawDebugLines(){
        ShapeRenderer render = new ShapeRenderer();
        render.begin(ShapeType.Line);
		for(int i = 0; i < WINDOW_WIDTH/32;i++){
			for(int j = 0; j < WINDOW_HEIGHT/32; j++){
				render.line(i*32, 0,i*32, j*32);
				render.line(0, j*32,i*32, j*32);
			}
		}
		render.end();
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
        am.load(Filename,Texture.class);
        am.finishLoading();
        return am.get(Filename);
    }

    public static Sprite GetSprite(String Filename){
        Texture origin = GetTexture(Filename);
        Sprite sp = new Sprite(origin,16,16);
        return sp;
    }

    public static void Dispose(){
        TowerDefend.batch.dispose();
    }

    public static double getRange(Vector2 object1, Vector2 object2){
        return Math.sqrt(Math.pow((object2.x - object1.x), 2) + Math.pow((object2.y - object1.y), 2));
    }

    public static void LoadAll(){
        am.load(SKIN);
    }

    public static AssetManager getAssetManager(){
        return am;
    }
}
