package com.levesteszta.towerdefend;

import com.badlogic.gdx.graphics.g2d.Sprite;
import static com.levesteszta.towerdefend.helpers.Artist.*;

// Ős
public class Enemy {
    protected static float SPEED = 0.4f;        //Jelenleg a mozgás sebessége adott mindenkinél, egy multiplifáció hogy ne szaladjon ki , inkább csak sétáljon nyugodtan
    protected float x, y; 
    protected Tile startTile;
    protected int size, health, damage;           //méret, Életerő, sebzés;                      
    protected Sprite[] textures;                  //Textura, késöbbiekben ez Sprite-ra cserélendő
    protected int[] direction;
    protected boolean first = true;
    protected TileGrid grid;

    protected boolean flagedToDead = false;

    Enemy(TileGrid grid, int size ,int health, int damage, Sprite[] textures){
        this.health = health;
        this.damage = damage;
        this.startTile = grid.getTileDataesByInd(grid.getStartIndex(), 0);
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.textures = textures;
        this.grid = grid;
        this.direction = new int[2];
        // X - Y , Az útirányját belőni
        this.direction[0] = 0; this.direction[1] = 0;
        this.direction = FindNextRoadTile(startTile);
    }

    protected void remove(){
        Dispose();
    }

    protected void setX(float delta){
        this.x += (delta*SPEED);
    }
    protected void setY(float delta){
        this.y += (delta*SPEED);
    }

    protected void update(float delta){
        if(first)
            first = false;
        else{
            this.setX(delta * direction[1]);
            this.setY(delta * direction[0]);
            direction = this.FindNextRoadTile(grid.getTileDataesByInd((int)(y/TILE_SIZE)-6, (int)(x/TILE_SIZE)-2));
        }

        if(this.health <= 0)
            this.flagedToDead = true;
    }

    protected void draw(){
        DrawTex(this.textures[0], x, y, TILE_SIZE);
    };

    private int[] FindNextRoadTile(Tile now){
        int[] dir = new int[2]; //x,y érték mennyivel változik adott részen hogy lássuk merre is kell menni
        Tile up = grid.getTileDataesByInd(now.getYInd()+1, now.getXInd());
        Tile down = grid.getTileDataesByInd(now.getYInd()-1, now.getXInd());
        //Tile left = grid.getTileDataesByInd(now.getXInd() - 1, now.getYInd());    //Jelenleg nem igazán van ilyen eset inkább ki is veszem minthogy baj legyen belöle
        Tile right = grid.getTileDataesByInd(now.getYInd(), now.getXInd()+1);

        if(now.getTile().id == up.getTile().id){
            dir[0] = 1; dir[1] = 0;
        }if(now.getTile().id == down.getTile().id){
            dir[0] =-1; dir[1] = 0;
        }if(now.getTile().id == right.getTile().id){
            dir[0] = 0; dir[1] = 1;
        }
        /* 
        else if(now.getTile().id == left.getTile().id){
            dir[0] = -1;
            dir[1] = 0;
        }*/
        else{
            System.out.println("Nem találtam útirányt");
        }
        return dir;
    }

    public Tile getStartTile() {
        return this.startTile;
    }
    public TileGrid getTileGrid(){
        return this.grid;
    }

    //getter-setter methods
    public int getHp(){
        return this.health;
    }
    protected void setHp(int hp){
        this.health = hp;
    }

    public void getHit(int towerDamageValue){
        this.damage -= towerDamageValue;
    }

    public boolean isDead(){
        return this.flagedToDead;
    }
}