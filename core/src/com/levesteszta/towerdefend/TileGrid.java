package com.levesteszta.towerdefend;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.levesteszta.towerdefend.helper.TileType;

public class TileGrid{
    private float start_X, start_Y, end_X, end_Y;
    private float WINDOW_HEIGHT, WINDOW_WIDTH;
    private Tile[][] map_grid2;
    private int[][] map;
    public TileGrid(float start_X, float start_Y, float end_X, float end_Y){
        this.start_X = start_X;
        this.start_Y = start_Y;
        this.end_X = end_X;
        this.end_Y = end_Y;

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
					    map_grid2[i][j] = new Tile((start_X)+j*32,(start_Y)+i*32,32,TileType.Fold);break;
                    case 1:
					    map_grid2[i][j] = new Tile((start_X)+j*32,(start_Y)+i*32, 32,TileType.Ut);

                }
            };
		};
    }

    public void draw(SpriteBatch batch){
        for(int i = 0; i < map_grid2.length; i++){
			for(int j = 0; j < map_grid2[i].length; j++){
				map_grid2[i][j].draw(batch);
			}
		};
    }

    public Tile getTileDataes(float xCord, float yCord) {
        return map_grid2[(int)(xCord/32)][(int)(yCord/32)];
    }

    public Tile setTileDataes(float xCord, float yCord,TileType type) {
        return map_grid2[(int)(xCord/32)][(int)(yCord/32)] = new Tile((int)(xCord/32), (int)(yCord/32), 32, type);
    }

}