package test.java;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.levesteszta.towerdefend.*;
import com.levesteszta.towerdefend.MapGen.Map;

public class Test_Map {
    @Rule
	public ExpectedException thrown = ExpectedException.none();

    private Map MAP;
    private int[][] map;
    private int WINDOW_WIDTH = (32*10);
    private int WINDOW_HEIGHT = (32*10);

    @Before public void initialize() throws IllegalArgumentException {
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

    @Test
    public void ishaveException() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        map = new Map().generate(0, WINDOW_HEIGHT);
    }
}
