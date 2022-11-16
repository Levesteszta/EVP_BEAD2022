package test.java;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.levesteszta.towerdefend.Map;

public class Test_Map {

    private Map MAP;
    private int[][] map;
    private int WINDOW_WIDTH = (32*10);
    private int WINDOW_HEIGHT = (32*10);

    @Before public void initialize(){
        MAP = new Map();
        map = MAP.generate(WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    @Test
    public void isGoodSize(){
        assertEquals(map.length, 10);
        assertEquals(map[0].length, 10);
    }

    @Test
    public void isHaveStart(){
        assertNotNull(MAP.getStart());
    }
}
