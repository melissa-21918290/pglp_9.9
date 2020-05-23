package pglp_9;

/**
 * 
 * @author Melissa
 *
 */
public class Rectangle extends Forme {
	/**
	 * positoin du coin du rectangle een haut à gauche
	 */
	private Position hautGauche;
	/**
	 * longeur du rectangle
	 */
	private int longeur;
	/**
	 * largeur du rectangle
	 */
	private int largeur;
	/**
	 * constructeur de la classe rectangle
	 * @param newVariableName nom de variable du rectangle
	 * @param hautGauchePosition position du coin en haut
	 * @param longeurRectangle longeur du rectangle
	 * @param largeurRectangle largeur du rectangle
	 */

	public Rectangle(String variable, final Position hautGauchePosition, final int longeurRectangle, final int largeurRectangle) throws Exception{
		
		super(variable);
		this.hautGauche = hautGauchePosition.clone();
        this.longeur = longeurRectangle;
        this.largeur = largeurRectangle;
	}
/**
 * deplacer un rectangle
 */
	@Override
	public void deplacer(int x, int y) {
		hautGauche.deplacer(x, y);
		
	}
/**
 * affichage du rectangle
 */
	@Override
	public void afficher() {
		System.out.println("Rectangle (longueur = "
                + longeur + ", largeur = " + largeur
                + ", position du point a l'extremité haut gauche = " + hautGauche + ")");
	}
	
	/**
	 * 
	 * @return la position du coin en haut a gauche
	 */

	public Position gethautGauche(){
		return hautGauche.clone();
		}
	/**
	 * modifier la position du rectangle
	 * @param hautGauchePosition
	 */
	public void sethautGauche(final Position hautGauchePosition){
		this.hautGauche =hautGauche.clone();
	}
	/**
	 * recuperer la largeur
	 * @return la largeur du rectangle
	 */
	public int getLargeur(){
		return largeur;
	}
	/**
	 * modifier la largeur du rectngle
	 * @param largeurRectangle 
	 * @throws Exception
	 */
	public void setLargeur(final int largeurRectangle) throws Exception{
		if(largeurRectangle>0){
		this.largeur=largeurRectangle;
	}else{ throw new Exception();}}
	
	/**
	 * recuperer la longeur
	 * @return longeur
	 */
	public int getLongeur(){
		return longeur;
	}
	
	
	/**
	 * modifoer la longeur
	 * @param longueurRectangle
	 * @throws Exception longeur non valide
	 */
	
	public void setLongueur(final int longueurRectangle) throws Exception {
        if (longueurRectangle > 0) {
            this.longeur = longueurRectangle;
        } else {
            throw new Exception();
        }
    }
	}
