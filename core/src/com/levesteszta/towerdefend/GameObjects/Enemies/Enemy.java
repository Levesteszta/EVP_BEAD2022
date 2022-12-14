package com.levesteszta.towerdefend.GameObjects.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.levesteszta.towerdefend.GameObjects.Player;
import com.levesteszta.towerdefend.MapGen.*;

import static com.levesteszta.towerdefend.helpers.Artist.*;

// Ős
public abstract class Enemy {
    private float x, y, health, startHealth; 
    private static int ENEMY_ID = 0;
    private static char weakElemental; 
    private int[] direction;
    private float[] oldDir = {0f, 0f} , newDir;
    protected int id;
    protected static float SPEED = 0.1f;        //Jelenleg a mozgás sebessége adott mindenkinél, egy multiplifáció hogy ne szaladjon ki , inkább csak sétáljon nyugodtan
    protected Tile startTile;
    protected int size, prize, damage;           //méret, Életerő, sebzés;                      
    protected Sprite[] textures;                  //Textura, késöbbiekben ez Sprite-ra cserélendő
    protected boolean first = true;
    protected TileGrid grid;

    protected boolean flagedToDead = false;

    Enemy(TileGrid grid, int size ,float health,int prize, int damage, Sprite[] textures){
        this.id = ENEMY_ID++;
        this.prize = prize;
        this.health = health;
        this.startHealth = health;
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

    public void update(){
        if(first)
            first = false;
        else{
            // Új út lehetőségnél áll neki csak keresni, ne legyen az hogy a le nem tett út után akarna fel alá rohangálni
            if((int)this.y == (int)newDir[0] && (int)this.x == (int)newDir[1]){ 
                direction = this.FindNextRoadTile(grid.getTileDataesByInd(Math.round(this.y/TILE_SIZE-6), Math.round(this.x/TILE_SIZE-2)));
            }
            else{
                // Ha nem talált útat, akkor a már ledefiiniált irány fele menjen csak szép nyugodtan
                // egy lineáris függvény modjára mozgatja a -ból => b -be az értéket 
                if(oldDir[0]>newDir[0]){    // Y 
                    this.y = Interpolation.linear.apply(this.y, (int)(oldDir[0]+((TILE_SIZE*direction[0]))), SPEED/1.75f);
                    this.x = Interpolation.linear.apply(this.x, (int)(oldDir[1]+((TILE_SIZE*direction[1]))), SPEED/1.75f);
                }
                else{
                    this.y = Interpolation.linear.apply(this.y, (int)(oldDir[0]+((TILE_SIZE*direction[0])+1)), SPEED/1.75f);
                    this.x = Interpolation.linear.apply(this.x, (int)(oldDir[1]+((TILE_SIZE*direction[1])+1)), SPEED/1.75f);
                }
            }
        }

        if(Math.round(this.x) > Math.round(grid.getWidth()*TILE_SIZE)){
            Player.takeDamage(damage);
            Die();
        }
    }

    public void draw(){
        float hpPercent = health / startHealth;
        DrawTex(this.textures[0], x, y, TILE_SIZE);
        DrawTex(GetTexture("ui/healthbar/hpbg.png"), x, y+32, TILE_SIZE,6);
        DrawTex(GetTexture("ui/healthbar/hpfill.png"), x, y+32, TILE_SIZE * hpPercent,6);
        DrawTex(GetTexture("ui/healthbar/hpborder.png"), x, y+32, TILE_SIZE,6);
    };

    
    /** 
     * A következő útírányhoz szükséges írányadó adatcsomag + az előző - rákövetkező utírány figyelése
     * @param now
     * @return int[]
     */
    private int[] FindNextRoadTile(Tile now){
        int[] dir = new int[2]; //x,y érték mennyivel változik adott részen hogy lássuk merre is kell menni
        newDir = new float[2];
        Tile up = grid.getTileDataesByInd(now.getYInd()+1, now.getXInd());
        Tile down = grid.getTileDataesByInd(now.getYInd()-1, now.getXInd());
        //Tile left = grid.getTileDataesByInd(now.getXInd() - 1, now.getYInd());    //Jelenleg nem igazán van ilyen eset inkább ki is veszem minthogy baj legyen belöle
        Tile right = grid.getTileDataesByInd(now.getYInd(), now.getXInd()+1);

        if((now.getTile().id == up.getTile().id) && (oldDir[0] != up.getY())){
            dir[0] = 1; dir[1] = 0;
            oldDir[0] = now.getY(); oldDir[1] = now.getX();
            newDir[0] = up.getY(); newDir[1] = up.getX();
        }else if( (now.getTile().id == right.getTile().id)){
            dir[0] = 0; dir[1] = 1;
            oldDir[0] = now.getY(); oldDir[1] = now.getX();
            newDir[0] = right.getY(); newDir[1] = right.getX();
        }else if((now.getTile().id == down.getTile().id) && (oldDir[0] != down.getY())){
            dir[0] = -1; dir[1] = 0;
            oldDir[0] = now.getY(); oldDir[1] = now.getX();
            newDir[0] = down.getY(); newDir[1] = down.getX();
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

    
    /** 
     * Visszaadja a kezdőCsempét
     * @return Tile
     */
    public Tile getStartTile() {
        return this.startTile;
    }
    
    /** 
     * Visszaadja a 'mapot'
     * @return TileGrid
     */
    public TileGrid getTileGrid(){
        return this.grid;
    }

    
    /** 
     * Visszaadja az Életerőt
     * @return float
     */
    //getter-setter methods
    public float getHp(){
        return this.health;
    }
    
    /** 
     * A Hp beállítása, ha véletlen kevesebb lenne mint 0 akkor a halál beáltját is megadjuk akkor már itt
     * @param hp
     */
    protected void setHp(int hp){
        this.health = hp;

        if(this.health <= 0){
            Player.setMoney(prize);
            Die();
        }
    }

    
    /** 
     * A sebzés kezelése, + ha gyengesége a sebzés típusa
     * @param towerDamageValue
     * @param type
     */
    public void takeDamage(int towerDamageValue,char type){
        if(type == weakElemental){
            towerDamageValue*=1.5;
        }
        this.setHp((int)this.getHp()-towerDamageValue);
    }

    
    /** 
     * Halot-e
     * @return boolean
     */
    public boolean isDead(){
        return this.flagedToDead;
    }

    public void Die(){
        this.flagedToDead = true;
    }

    
    /** 
     * Teszt jelelggel van csak, de visszaadja mi az id
     * @return int
     */
    public int getID(){
        return this.id;
    }

    
    /** 
     * X koordinátája
     * @return float
     */
    public float getX() {
        return x;
    }
    
    /** 
     * Y koordinátája
     * @return float
     */
    public float getY() {
        return y;
    }
    
    /** 
     * Gyengeség elementáljának beállítása
     * @param weakElemental
     */
    public static void setWeakElemental(char weakElemental) {
        Enemy.weakElemental = weakElemental;
    }
    
    /** 
     * Visszaadja a gyengeség elementálját
     * @return char
     */
    public static char getWeakElemental() {
        return weakElemental;
    }
}