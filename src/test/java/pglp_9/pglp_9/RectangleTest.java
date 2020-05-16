package pglp_9.pglp_9;
import static org.junit.Assert.*;

import org.junit.Test;
import pglp_9.Position;
import pglp_9.Rectangle;

public class RectangleTest {
	 @Test
	    public void testConstructorAndGetters() throws Exception {
	        Rectangle r = new Rectangle("r", new Position(1,1), 8, 6);
	        assertEquals(r.getvariable(), "r");
	        assertTrue(r.gethautGauche().toString().equals("(1,1)"));
	        assertTrue(r.getLongeur() == 8);
	        assertTrue(r.getLargeur() == 6);
	    }
	 @Test
	    public void testAffiche() throws Exception {
	        Rectangle r = new Rectangle("r", new Position(1,1), 8, 6);
	        r.afficher();
	    }
	 @Test
	    public void testDeplace() throws Exception {
	        Rectangle r = new Rectangle("r", new Position(1,1),5, 8);
	        r.deplacer(5, 10);
	        assertTrue(r.gethautGauche().toString().equals("(6,11)"));
	    }
}
