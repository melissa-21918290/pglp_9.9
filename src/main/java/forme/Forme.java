package forme;

public abstract class Forme {
	private String variable;

	public String getvariable(){
		return variable+ "";
	}
	public void setVariable(final String newVariable){
		variable=newVariable+ "";
	}

	  protected Forme(final String newVariableName) {
	        this.variable = newVariableName;
	    }
	  public abstract void deplacer(int x, int y);
	  public abstract void afficher();
	  }


