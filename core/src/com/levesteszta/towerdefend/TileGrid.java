package com.levesteszta.towerdefend;

import com.levesteszta.towerdefend.helpers.TileType;

public class TileGrid{
    private float start_X, start_Y;
    private float WINDOW_HEIGHT, WINDOW_WIDTH;
    private Tile[][] map_grid2;
    private int[][] map;
    public TileGrid(float start_X, float start_Y, float end_X, float end_Y){
        this.start_X = start_X;
        this.start_Y = start_Y;

        this.WINDOW_WIDTH = end_X - start_X;    
        this.WINDOW_HEIGHT = end_Y - start_Y;
    }

    public void generate(){
        map = new Map().generate((int)WINDOW_HEIGHT, (int)WINDOW_WIDTH);
        map_grid2 = new Tile[(int)map.length][(int)map[0].length];
        for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
                switch(map[i][j]){
                    case 0:
					    map_grid2[i][j] = new Tile((start_X)+j*32,(start_Y)+i*32,32,TileType.getRandomDirt());break;
                    case 1:
					    map_grid2[i][j] = new Tile((start_X)+j*32,(start_Y)+i*32, 32,TileType.getRandomRoad());break;

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
        return map_grid2[(int)((xCord/32))][(int)((yCord/32))];
    }
    public Tile getTileDataesByInd(int oszlop, int sor) {
        return map_grid2[oszlop][sor];
    }

    public Tile setTileDataes(float xCord, float yCord,TileType type) {
        return map_grid2[(int)(xCord/32)][(int)(yCord/32)] = new Tile((int)(xCord/32), (int)(yCord/32), 32, type);
    }

}