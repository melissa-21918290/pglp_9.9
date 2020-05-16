package pglp_9;
/**
 * 
 * @author Melissa
 *
 */
public class Cercle extends Forme {
	/**
	 * position du centre du cercle
	 */
	private Position centre;
	/**
	 * rayon su cercle
	 */
	private int rayon;
	/**
	 * constructeur du cercle
	 * @param variable nom de la variable pour creer le cercle
	 * @param p position tu cercle
	 * @param rayonCercle rayon du cercle
	 * @throws Exception rayon invalide
	 */
	
	public Cercle(final String variable, final Position p, final int rayonCercle) throws Exception{
		super(variable);
		centre= p.clone();
		rayon=rayonCercle;
	}
	

/**
 * deplacer le cercle
 * @param x abscisse
 * @param y ordonnée
 */
	@Override
	public void deplacer(int x, int y) {
		centre.deplacer(x, y);
		
	}

/**
 * affichage du cercle
 */
	@Override
	public void afficher() {
		System.out.println("Cercle ("
	               + "centre = " + centre + ", rayon = " + rayon + ")");
	   
	
	}
	/**
	 * 
	 * @return le rayon
	 */
public int getRayon(){
	return rayon;
}
/**
 * nouvelle valeur du rayon
 * @param nouvRayon valeur pour le rayon
 * @throws Exception rayon invalide
 */
public void setRayon(final int nouvRayon) throws Exception {
    if (nouvRayon>0){
	this.rayon = nouvRayon;
}else{ throw new Exception();}
}

/**
 * 
 * @return centre du rayon
 */
public Position getCentre() {
    return centre.clone();
}
/**
 * definir la valeur pour le cnetre du rayon
 * @param nouvCentre nouvelle valeur pour le centre
 */
public void setCentre(final Position nouvCentre) {
    this.centre = nouvCentre.clone();
}
}
