package pglp_9.pglp_9;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import pglp_9.Cercle;
import pglp_9.GroupeForme;
import pglp_9.Position;
public class GroupeFormeTest {
	@Test
	public void testConstructeur(){
		GroupeForme groupe = new GroupeForme("groupe");
		assertTrue(groupe.getList().isEmpty() && groupe.getvariable().equals("groupe"));
	}
	 public void testAdd() throws Exception{
		 GroupeForme groupe = new GroupeForme("groupe");
		 Cercle C = new Cercle("c", new Position(2,3),5);
		 groupe.add(C);
		 groupe.add(C);
		 groupe.add(groupe);
		 assertTrue(groupe.getList().size() == 2 && groupe.getList().get(0) == C);
	 }
	 
	 public void testAfficher()throws Exception{
		 GroupeForme groupe = new GroupeForme("groupe");
		 Cercle C = new Cercle("c", new Position(2,3),5);
		 groupe.add(C);
		 groupe.afficher();
		 
	 }
	 @Test
	 public void testDeplacer() throws Exception{
		 GroupeForme groupe = new GroupeForme("groupe");
		 Cercle C = new Cercle("c", new Position(2,3),5);
		 groupe.add(C);
		 groupe.deplacer(7, 6);
		  assertTrue(C.getCentre().toString().equals("(9,9)"));
	 }

	 public void testDelete() throws Exception{
		 GroupeForme groupe = new GroupeForme("groupe");
		 Cercle C = new Cercle("c", new Position(2,3),5);
		 groupe.add(C);
		 groupe.remove(C);
		 assertTrue(groupe.getList().isEmpty());
	 }
}
