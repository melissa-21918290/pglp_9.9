package pglp_9.pglp_9;
import static org.junit.Assert.*;

import java.io.CharConversionException;

import org.junit.Test;

import pglp_9.Position;

public class PositionTest {
	@Test
	public void testConstructeur() throws CharConversionException{
		   Position p = new Position();
	        assertTrue(p.getX() == 0 && p.getY() == 0);
	      
	}
	@Test
	public void PosTest(){
		/*Position p3 = new Position(3,5);
        assertTrue(p3.getX() == 3 && p3.getY() == 5);*/
		  Position p = new Position(15,12);
	        assertTrue(p.getX() == 15 && p.getY() == 12);
	}

	 @Test
	    public void testConstructor3() throws CharConversionException {
	        Position p = new Position("(14,36)");
	        assertTrue(p.getX() == 14 && p.getY() == 36);
	    }
	 @Test
	    public void testDeplace() {
	        Position p = new Position(2,3);
	        p.deplacer(10, 5);
	        assertTrue(p.getX() == 12 && p.getY() == 8);
	    }
}
