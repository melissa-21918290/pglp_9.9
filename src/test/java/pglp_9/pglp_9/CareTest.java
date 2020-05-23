package pglp_9.pglp_9;



import static org.junit.Assert.*;
import org.junit.Test;
import pglp_9.Carre;
import pglp_9.Position;
import pglp_9.DaoCarreJDBC;
import pglp_9.BDD;


/**
 * Unit test for simple App.
 */
public class CareTest {
	
	@Test
	public void testConstructeurCarre() throws Exception {
        Carre C = new Carre("C", new Position (2,3), 5);
	 assertEquals(C.getvariable(), "C");
       assertTrue(C.getHautGauche().toString().equals("(2,3)"));
        assertTrue(C.getLongeur() == 5);
	

}
	
	 @Test
	    public void testDeplace() throws Exception {
	        Carre C = new Carre("C", new Position(2,3), 5);
	        C.deplacer(5, 10);
	        assertTrue(C.getHautGauche().toString().equals("(7,13)"));
	    }
	 
	 public void testAfficher()throws Exception{
		 Carre C = new Carre("C", new Position(2,3), 5);
		 C.afficher();
	 }

			
		
	 
	}