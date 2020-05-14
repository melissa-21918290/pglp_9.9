
/**
 * 
 * @author Melissa
 *
 */
public abstract class Forme {
	/**
	 * nom de cariable
	 */
	private String variable;
	/**
	 * 
	 * @return le nom de la variable de la forme
	 */

	public String getvariable(){
		return variable+ "";
	}
	/**
	 * modifier le nom de variable de la forme
	 * @param newVariable
	 */
	public void setVariable(final String newVariable){
		variable=newVariable+ "";
	}
	/**
	 * constructeur pour definir la variable
	 * @param newVariableName le nom de la variable de la forme
	 */

	  protected Forme(final String newVariableName) {
	        this.variable = newVariableName;
	    }
	  /**
	   * deplacer la forme
	   * @param x en abcsisse
	   * @param yen ordonné
	   */
	  public abstract void deplacer(int x, int y);
	  /**
	   * afficher la forme
	   */
	  public  void afficher(){
		  System.out.print(variable + " : ");
	  }
	  }


