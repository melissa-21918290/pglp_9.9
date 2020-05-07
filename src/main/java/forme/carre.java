package forme;

public class carre extends Forme {
	private Position hautGauche;
	private int longeur;
	
	public carre(final String variable, final Position hautGauche,final int longeurCarre ){
	super(variable);
	this.hautGauche= hautGauchePosition.clone();
	this.longeur=longeurCarre;
	}
	
	

	protected carre(String newVariableName) {
		super(newVariableName);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void deplacer(int x, int y) {
		hautGauche.delacer(x,y);
		
	}


	@Override
	public void afficher() {
		System.out.println("carre(longeur=" +longeur+",position du coin en haut à gauche="+hautgauche+")");;
	
}
	public Position getHautGauche(){
		return hautgauche.clone();
	}
	public void setHautGauche(final Position hautgauchePosition){
		this.hautGauche=hautgauche.clone();
	}
	public int getLongeur(){
		return longeur;
	}
	
	public void setLongeur(final int longeurCarre){
		this.longeur=longeurCarre;
	}

	
	    
	
	
}
