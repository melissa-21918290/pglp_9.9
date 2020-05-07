package forme;

public class Rectangle extends Forme {
	private Position hautGauche;
	private int longeur;
	private int largeur;

	protected Rectangle(String newVariableName, final Position hautGauchePosition, final int longeurRectangle, final int largeurRectangle){
		
		super(newVariableName);
		this.hautGauche = hautGauchePosition.clone();
        this.longeur = longeurRectangle;
        this.largeur = largeurRectangle;
	}

	@Override
	public void deplacer(int x, int y) {
		hautGauche.deplacer(x, y);
		
	}

	@Override
	public void afficher() {
		System.out.println("Rectangle (longueur = "
                + longeur + ", largeur = " + largeur
                + ", position du coin en haut à gauche = " + hautGauche + ")");
	}

	public Position gethautGauche(){
		return hautGauche.clone();
		}
	public void sethautGauche(final Position hautGauchePosition){
		this.hautGauche =hautGauche.clone();
	}
	public int getLargeur(){
		return largeur;
	}
	public void setLargeur(final int largeurRectangle){
		this.largeur=largeurRectangle;
	}
	public int getLongeur(){
		return longeur;
	}
	public void setLongeur(final int longeurRectangle){
		this.longeur=longeurRectangle;
	}
	}
