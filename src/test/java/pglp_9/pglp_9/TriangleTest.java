package pglp_9.pglp_9;
import static org.junit.Assert.*;

import org.junit.Test;

import pglp_9.Position;
import pglp_9.Triangle;

public class TriangleTest {
@Test
	public void testConstructeur(){
		  Triangle t = new Triangle("t", new Position(1,2), new Position(2,3), new Position(3,4));
	        assertEquals(t.getvariable(), "t");
	        assertTrue(t.getPosition(0).toString().equals("(1,2)"));
	        assertTrue(t.getPosition(1).toString().equals("(2,3)"));
	        assertTrue(t.getPosition(2).toString().equals("(3,4)"));
	}
@Test
public void testAffiche() {
    Triangle t = new Triangle("t", new Position(1,2), new Position(2,3), new Position(3,4));
    t.afficher();
}
@Test
public void testDeplace() {
    Triangle t = new Triangle("t", new Position(1,2), new Position(2,3), new Position(3,4));
    t.deplacer(5, 10);
    assertTrue(t.getPosition(0).toString().equals("(6,12)"));
    assertTrue(t.getPosition(1).toString().equals("(7,13)"));
    assertTrue(t.getPosition(2).toString().equals("(8,14)"));
}
@Test (expected = IndexOutOfBoundsException.class)
public void testUp() {
    Triangle t = new Triangle("t", new Position(1,2), new Position(2,3), new Position(3,4));
    t.getPosition(3);
}
}
