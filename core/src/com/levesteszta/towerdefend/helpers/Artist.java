package com.levesteszta.towerdefend.helpers;

import com.levesteszta.towerdefend.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class Artist {
    //public static SpriteBatch batch = new SpriteBatch();
    private static AssetManager am = new AssetManager();
    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>("ui/uiskin.json",Skin.class,new SkinLoader.SkinParameter("ui/uiskin.atlas"));
    public static final float WINDOW_WIDTH = 997f, WINDOW_HEIGHT = 706f;
    public static int TILE_SIZE = 32;

    
    /** 
     * Kirajzoljuk a texture-t, adott x,y koordinátára, size mérettel
     * @param texture
     * @param x
     * @param y
     * @param size
     */
    public static void DrawTex(Texture texture, float x, float y, float size){
        texture.bind();
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        TowerDefend.batch.begin();
        TowerDefend.batch.draw(texture, x, y, size, size);
        TowerDefend.batch.end();
    }
    
    /** 
     * Kirajzoljuk a texture-t, adott x,y koordinátára, adott width szélességel, és height magassággal
     * @param texture
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void DrawTex(Texture texture, float x, float y, float width, float height){
        texture.bind();
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        TowerDefend.batch.begin();
        TowerDefend.batch.draw(texture, x, y, width, height);
        TowerDefend.batch.end();
    }
    
    /** 
     * Kirajzoljuk a sp-t spriteot, adott x,y koordinátára, size mérettel
     * @param sp
     * @param x
     * @param y
     * @param size
     */
    public static void DrawTex(Sprite sp, float x, float y, float size){
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        TowerDefend.batch.begin();
        TowerDefend.batch.draw(sp, x, y, size, size);
        TowerDefend.batch.end();
    }

    
    /** 
     * Kirajzoljuk az adott text szöveget, x,y kordinátára
     * @param text
     * @param x
     * @param y
     * @param size
     */
    public static void DrawText(String text, float x, float y, int size){
        BitmapFont font = new BitmapFont();
        GlyphLayout glyphLayout = new GlyphLayout(font,text);
        TowerDefend.batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(size,size);
        font.draw(TowerDefend.batch,glyphLayout, (x) , (y));
        TowerDefend.batch.end();
    }
    
    /** 
     * Kirajzoljuk az adott text szöveget, x,y kordinátára, adoww width alapján középre igaziottan
     * @param text
     * @param x
     * @param y
     * @param width
     * @param size
     */
    public static void DrawTextCenterAllign(String text, float x, float y, float width, int size){
        BitmapFont font = new BitmapFont();
        GlyphLayout layout = new GlyphLayout(font,text);
        TowerDefend.batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(size,size);
        float fontX = x + (width - layout.width)/2;
        font.draw(TowerDefend.batch,layout, fontX , (y));
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

    
    /** 
     * Adott filenameből betöltött textura területről(x koordináta mentén) adott size mérettel felosztott Sprite tömb visszadaása 
     * @param Filename
     * @param size
     * @return Sprite[]
     */
    public static Sprite[] getTexturesFromArea(String Filename, int size){
        Texture origin = GetTexture(Filename);
        int n = (int)(origin.getWidth()/size);
        Sprite[] sp = new Sprite[n];
        for(int i = 0; i < n; i++)
            sp[i] = new Sprite(origin,i*size,0,16,16);
        return sp;
    }

    
    /** 
     * Adott Fájlnémből visszaadott Textura
     * @param Filename
     * @return Texture
     */
    public static Texture GetTexture(String Filename){
        am.load(Filename,Texture.class);
        am.finishLoading();
        return am.get(Filename);
    }

    
    /** 
     * Adott Fájlnémből visszaadott Sprite
     * @param Filename
     * @return Sprite
     */
    public static Sprite GetSprite(String Filename){
        Texture origin = GetTexture(Filename);
        Sprite sp = new Sprite(origin,16,16);
        return sp;
    }

    public static void Dispose(){
        TowerDefend.batch.dispose();
    }

    
    /** 
     * Visszadja 2 Vector objektum közti távolságot
     * @param object1
     * @param object2
     * @return double
     */
    public static double getRange(Vector2 object1, Vector2 object2){
        return Math.sqrt(Math.pow((object2.x - object1.x), 2) + Math.pow((object2.y - object1.y), 2));
    }

    
    /** 
     * 2 "objektum" Collison azaz érintkezési vizsgálata
     * @param x1
     * @param y1
     * @param width1
     * @param height1
     * @param x2
     * @param y2
     * @param width2
     * @param height2
     * @return boolean
     */
    public static boolean CheckCollision(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2){
        if(x1 + width1 > x2 && x1 < x2 + width2 && y1 + height1 > y2 && y1 < y2 + height2)
            return true;
        else return false;
    }

    public static void LoadAll(){
        am.load(SKIN);
    }

    
    /** 
     * A 'filebetöltő' visszaadása
     * @return AssetManager
     */
    public static AssetManager getAssetManager(){
        return am;
    }
}
