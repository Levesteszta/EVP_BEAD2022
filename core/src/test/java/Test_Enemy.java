package test.java;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.levesteszta.towerdefend.*;

public class Test_Enemy {
    @Rule
	public ExpectedException thrown = ExpectedException.none();
    Enemy ellen;
    static TileGrid grid;

    @Before
    public void oneTimeSetUp() throws IllegalArgumentException{
        grid = new TileGrid(0, 0, (32*15), (32*10));
        grid.generate();
        Basic b = new Basic(grid);
        ellen = b;
    }

    @Test 
    public void isGeneratedAndFullHp(){
        assertEquals(ellen.getHp(), 100);
    }

    @Test 
    public void getHitWorking(){
        assertEquals(100, ellen.getHp());
        ellen.getHit(10);
        assertEquals(90, ellen.getHp());
    }

    @Test 
    public void isDead(){
        assertFalse(ellen.isDead());
    }
}
