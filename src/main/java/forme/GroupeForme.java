package forme;

import java.util.ArrayList;

/**
 * 
 * @author Melissa
 *
 */

public class GroupeForme extends Forme {
	
	private ArrayList<Forme> formes;
	
/**
 * constructeur de la classe 
 * @param newVariableName nom de la variable du groupe
 */
	protected GroupeForme(String newVariableName) {
		super(newVariableName);
		formes= new ArrayList<Forme>();
	}

	@Override
	public void deplacer(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

}
