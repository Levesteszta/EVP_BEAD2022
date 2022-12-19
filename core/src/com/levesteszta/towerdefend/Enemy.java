package com.levesteszta.towerdefend;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import static com.levesteszta.towerdefend.helpers.Artist.*;

// Ős
public class Enemy {
    private static int ENEMY_ID = 0;
    private int[] direction;
    private float[] oldDir = {0f, 0f} , newDir;
    protected int id;
    protected static float SPEED = 0.1f;        //Jelenleg a mozgás sebessége adott mindenkinél, egy multiplifáció hogy ne szaladjon ki , inkább csak sétáljon nyugodtan
    protected float x, y; 
    protected Tile startTile;
    protected int size, health, damage;           //méret, Életerő, sebzés;                      
    protected Sprite[] textures;                  //Textura, késöbbiekben ez Sprite-ra cserélendő
    protected boolean first = true;
    protected TileGrid grid;

    protected boolean flagedToDead = false;

    Enemy(TileGrid grid, int size ,int health, int damage, Sprite[] textures){
        this.id = ENEMY_ID++;
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
        this.x += delta;
    }
    protected void setY(float delta){
        this.y += delta;
    }

    protected void update(){
        if(first)
            first = false;
        else{
            //System.out.println("x: "+(int)(x/TILE_SIZE-2)+ " --> "+(int)((oldDir[1]+(TILE_SIZE*direction[1]))/TILE_SIZE-2));
            //System.out.println("y: "+(int)(y/TILE_SIZE-6)+ " --> "+(int)((oldDir[0]+(TILE_SIZE*direction[0]))/TILE_SIZE-6));
            System.out.println(" Honnan: "+oldDir[1]+" "+oldDir[0]);
            System.out.println(" Most: "+this.x+" "+this.y);
            System.out.println(" Hová: "+newDir[1]+" "+newDir[0]);
            if((int)this.y == (int)newDir[0] && (int)this.x == (int)newDir[1]){
                System.out.println("Új út!");
                direction = this.FindNextRoadTile(grid.getTileDataesByInd(Math.round(this.y/TILE_SIZE-6), Math.round(this.x/TILE_SIZE-2)));
            }
            else{
                if(oldDir[0]>newDir[0]){    // Y 
                    this.y = Interpolation.smooth.apply(this.y, (int)(oldDir[0]+((TILE_SIZE*direction[0]))), SPEED/0.95f);
                    this.x = Interpolation.smooth.apply(this.x, (int)(oldDir[1]+((TILE_SIZE*direction[1]))), SPEED/0.95f);
                }
                else{
                    this.y = Interpolation.smooth.apply(this.y, (int)(oldDir[0]+((TILE_SIZE*direction[0])+1)), SPEED/0.95f);
                    this.x = Interpolation.smooth.apply(this.x, (int)(oldDir[1]+((TILE_SIZE*direction[1])+1)), SPEED/0.95f);
                }
            }
        }
        if(this.health <= 0)
            this.flagedToDead = true;
    }

    protected void draw(){
        DrawTex(this.textures[0], x, y, TILE_SIZE);
    };

    private int[] FindNextRoadTile(Tile now){
        int[] dir = new int[2]; //x,y érték mennyivel változik adott részen hogy lássuk merre is kell menni
        newDir = new float[2];
        Tile up = grid.getTileDataesByInd(now.getYInd()+1, now.getXInd());
        Tile down = grid.getTileDataesByInd(now.getYInd()-1, now.getXInd());
        //Tile left = grid.getTileDataesByInd(now.getXInd() - 1, now.getYInd());    //Jelenleg nem igazán van ilyen eset inkább ki is veszem minthogy baj legyen belöle
        Tile right = grid.getTileDataesByInd(now.getYInd(), now.getXInd()+1);

        System.out.println(" Régi Tile :: [ "+oldDir[1]+" , "+ oldDir[0]+" ]");
        System.out.println(" Most Tile :: [ "+now.getX()+" , "+now.getY()+ " ]");

        System.out.println(" Fel Tile :: [ "+up.getX()+" , "+up.getY()+ " ]");
        System.out.println(" Jobbra Tile :: [ "+right.getX()+" , "+right.getY()+ " ]");
        System.out.println(" Le Tile :: [ "+down.getX()+" , "+down.getY()+ " ]");

        if( (now.getTile().id == up.getTile().id) && (oldDir[0] != up.getY())){
            dir[0] = 1; dir[1] = 0;
            oldDir[0] = now.getY(); oldDir[1] = now.getX();
            newDir[0] = up.getY(); newDir[1] = up.getX();
            System.out.println("Fel kéne menni");
        }else if( (now.getTile().id == right.getTile().id)){
            dir[0] = 0; dir[1] = 1;
            oldDir[0] = now.getY(); oldDir[1] = now.getX();
            newDir[0] = right.getY(); newDir[1] = right.getX();
            System.out.println("Jobbra kéne menni");
        }else if((now.getTile().id == down.getTile().id) && (oldDir[0] != down.getY())){
            dir[0] = -1; dir[1] = 0;
            oldDir[0] = now.getY(); oldDir[1] = now.getX();
            newDir[0] = down.getY(); newDir[1] = down.getX();
            System.out.println("Le kéne menni");
        }
        /* 
        else if(now.getTile().id == left.getTile().id){
            dir[0] = -1;dir[1] = 0;
        }*/
        else {
            dir[0] = 0; dir[1] = 0;
            System.out.println("Nem találtam útvonalat");
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