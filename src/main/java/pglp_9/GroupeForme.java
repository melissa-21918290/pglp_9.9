package pglp_9;


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

	/**
	 * deplacer les formes
	 */
	@Override
	public void deplacer(int x, int y) {
		for(Forme f: formes){
			f.deplacer(x, y);
		}
		
	}
	/**
	 * afficher le groupe
	 */

	@Override
	public void afficher() {
		 super.afficher();
	        System.out.println("Groupe (");
	        for (Forme f : formes) {
	            f.afficher();
	        }
	        System.out.println(")");
	    
		
	}

	/**
	 * ajouter un efomre ou un gourpe au groupe
	 * @param f forme ou groupe a ajouter
	 */
	public void add(final Forme f) {
		if(!formes.contains(f) && f!=this){
			formes.add(f);
			
		}
	}
	/**
	 * supprimer une forme ou un groupe du groupe
	 * @param f forme ou groupe a supprimer du groupe
	 */
	public void remove(final Forme f){
		formes.remove(f);
	}
	/**
	 * obtenir la liste des formes et groupes du groupe
	 * @return la liste des ofrmes et groupe du groupe
	 */
	public ArrayList<Forme>getList(){
		return(ArrayList<Forme>) formes.clone();
	}
}
