package com.levesteszta.towerdefend.MapGen;
import com.levesteszta.towerdefend.helpers.TileType;
import static com.levesteszta.towerdefend.helpers.Artist.*;

public class TileGrid{
    private float start_X, start_Y, end_X, end_Y;
    private float WINDOW_HEIGHT, WINDOW_WIDTH;
    private Tile[][] map_grid2;
    private int[][] map;
    TileType[] types = TileType.getTypes();

    public TileGrid(float start_X, float start_Y, float end_X, float end_Y){
        this.start_X = start_X;
        this.start_Y = start_Y;
        this.end_X = end_X;
        this.end_Y = end_Y;

        this.WINDOW_WIDTH = end_X - start_X;    
        this.WINDOW_HEIGHT = end_Y - start_Y;
        generate();
    }

    public void generate(){
        map = new Map().generate((int)WINDOW_HEIGHT, (int)WINDOW_WIDTH);
        map_grid2 = new Tile[(int)map.length][(int)map[0].length];
        for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
                switch(map[i][j]){
                    case 0:
					    map_grid2[i][j] = new Tile((start_X)+j*32,(start_Y)+(i*32),32,TileType.getRandomDirt());break;
                    case 1:
					    map_grid2[i][j] = new Tile((start_X)+j*32,(start_Y)+(i*32), 32,TileType.getRandomRoad());break;
                }
            };
		};
    }

    public void draw(){
        for(int i = 0; i < map_grid2.length; i++){
			for(int j = 0; j < map_grid2[i].length; j++){
				map_grid2[i][j].draw();
			}
		};
    }

    public int getStartIndex(){
        for(int i = 0; i < map_grid2[0].length;i++){
            if(map_grid2[i][0].getTile().id == 1)
                return i;
        }
        return 0;
    }

    public Tile getTileDataesByCoord(float xCord, float yCord) {
        int x_eltolas = 2; int y_eltolas = 6;
        if(this.isInCoords(xCord, yCord))
            return map_grid2[((int)(yCord/32)-y_eltolas)][((int)(xCord/32)-x_eltolas)];
        return null;
    }
    public Tile getTileDataesByInd(int oszlop, int sor) {
        return map_grid2[oszlop][sor];
    }

    public void setTileDataes(float xCord, float yCord,TileType type) {
        int x_eltolas = 2; int y_eltolas = 6;
        int tmpX = (int)xCord/TILE_SIZE; int tmpY = (int)yCord/TILE_SIZE;
        map_grid2[((int)(yCord/32)-y_eltolas)][((int)(xCord/32)-x_eltolas)] = 
            new Tile((map_grid2[((int)(yCord/32)-y_eltolas)][((int)(xCord/32)-x_eltolas)].getX()),(map_grid2[((int)(yCord/32)-y_eltolas)][((int)(xCord/32)-x_eltolas)].getY()),TILE_SIZE, type);
    }

    public int getWidth() {
        return map[0].length;
    }
    
    public int getHeight() {
        return map.length;
    }
    
    public boolean isInCoords(float x, float y){
        if(x >= this.start_X && y >= this.start_Y && x <= this.end_X && y <= this.end_Y) 
            return true;
        return false;
    }
    
    // Tesztek végett kell 
    public void TEST_Generate(){
        map = new Map().generate((int)WINDOW_HEIGHT, (int)WINDOW_WIDTH);
    }


}