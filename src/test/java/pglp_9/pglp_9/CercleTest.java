package pglp_9.pglp_9;

import pglp_9.Carre;
import pglp_9.Cercle;
import pglp_9.Position;
import static org.junit.Assert.*;

import org.junit.Test;

public class CercleTest {
@Test
	public void testConstructeur() throws Exception{
		Cercle C = new Cercle("C", new Position(2,3), 5);
		assertEquals(C.getvariable(), "C");
        assertTrue(C.getCentre().toString().equals("(2,3)"));
        assertTrue(C.getRayon() == 5);
	}

@Test
public void testAfficher() throws Exception{
	Cercle C = new Cercle("C", new Position(2,3), 5);
	C.afficher();
}

public void testDeplacer()throws Exception{
	Cercle C = new Cercle("C", new Position(2,3), 5);
	C.deplacer(5, 10);
	assertTrue(C.getCentre().toString().equals("(7,13"));
}

}
