package forme;

public class Cercle extends Forme {
	
	private Position centre;
	private int rayon;
	
	public Cercle(final String variable, final Position p, final int rayonCercle){
		super(variable);
		centre= p.clone();
		rayon=rayonCercle;
	}
	


	protected Cercle(String newVariableName) {
		super(newVariableName);
		
	}


	@Override
	public void deplacer(int x, int y) {
		centre.deplacer(x, y);
		
	}


	@Override
	public void afficher() {
		System.out.println("Cercle ("
	               + "centre = " + centre + ", rayon = " + rayon + ")");
	   
	
	}
	
public int getRayon(){
	return rayon;
}
public void setRayon(final int newRayon) {
    this.rayon = newRayon;
}
public Position getCentre() {
    return centre.clone();
}
public void setCentre(final Position newCentre) {
    this.centre = newCentre.clone();
}
}
