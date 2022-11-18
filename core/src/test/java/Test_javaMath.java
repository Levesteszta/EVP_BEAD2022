package test.java;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Test_javaMath {
    
    @Test
    public void canJavaAddNums(){
        assertEquals(2,Math.addExact(1, 1));
        assertEquals(152,Math.addExact(32, 120));
        assertEquals(3,(int)(1.5 + 1.5));
    }

    @Test
    public void canJavaSubNums(){
        assertEquals(0,Math.subtractExact(1, 1));
        assertEquals(88,Math.subtractExact(120, 32));
        assertEquals(0,(int)(1.5 - 1.5));
    }

    @Test
    public void canJavaMultNums(){
        assertEquals(1,Math.multiplyExact(1, 1));
        assertEquals(3840,Math.multiplyExact(120, 32));
        assertEquals(2.25,((1.5)*(1.5)), 0.1);
    }

    @Test
    public void canJavaDivNums(){
        assertEquals(1,Math.floorDiv(1, 1));
        assertEquals(3,Math.floorDiv(120, 32));
        assertEquals((int)1.0,(int)((double)1.5/(double)1.5));
    }

    @Test
    public void canJavaMinMaxNums(){
        assertEquals(5,Math.min(10, 5));
        assertEquals(120,Math.max(120, 32));
    }
}
