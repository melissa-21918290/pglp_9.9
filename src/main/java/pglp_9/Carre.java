package pglp_9;
/**
 * 
 * @author Melissa
 *
 */
public class Carre extends Forme {
	/**
	 * position du coin gauche en haut du carré
	 */
	private Position hautGauche;
	/**
	 * longeur de chaque trai du carré
	 */
	private int longeur;
	
	
	
	/**
	 * constructeur du carré
	 * @param variable nom de la variable pour creer un carré
	 * @param hautGauchePosition position du coin en haut a gauche
	 * @param longeurCarre longeur du carré
	 */
	public Carre(final String variable, final Position hautGauchePosition,final int longeurCarre ) throws Exception{
	super(variable);
	this.hautGauche= hautGauchePosition.clone();
	this.longeur=longeurCarre;
	}
	
	

/**
 * deplacer la carré
 * @param x
 *@param y 
 */
	@Override
	public void deplacer(int x, int y) {
		hautGauche.deplacer(x,y);
		
	}

/**
 * affichage du carré
 */
	@Override
	public void afficher() {
		System.out.println("carre(longeur=" +longeur+",position du coin en haut à gauche="+hautGauche+")");;
	
} 
	
	/**
	 * 
	 * @return position du carré en haut a gauche
	 */
	public Position getHautGauche(){
		return hautGauche.clone();
	}
	/**
	 * modifier la position du carré
	 * @param hautgauchePosition position du coin en haut à gauche
	 */
	public void setHautGauche(final Position hautgauchePosition){
		this.hautGauche=hautGauche.clone();
	}
	/**
	 * longeur du carré
	 * @return la longeur du carré
	 */
	public int getLongeur(){
		return longeur;
	}
	/**
	 * modification de la longeur du carré
	 * @param longeurCarre valeur pour la longeur>0
	 * @throws Exception la longeur n'est pas valide
	 */
	
	public void setLongeur(final int longeurCarre)throws Exception{
		if(longeurCarre>0){
		
		this.longeur=longeurCarre;
	} else { throw new Exception();}}

	
	    
	
	
}
