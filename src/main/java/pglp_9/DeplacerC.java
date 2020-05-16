package pglp_9;

/**
 * 
 * @author Melissa
 *
 */
public class DeplacerC implements Command {
	/**
	 * forme à déplacer
	 */
	private Forme forme;
	/**
	 * vecteur de deplacement
	 */
	private Position Tab;
	
public DeplacerC(final Forme f, final Position TabD){
	this.Tab=TabD;
	forme=f;
}
/**
 * executer de la commande
 */
	public void executer() {
		 forme.deplacer(Tab.getX(), Tab.getY());
	        DaoFactoryJDBC factory = new DaoFactoryJDBC();
	        if (forme.getClass() == Cercle.class) {
	            AbstractDao<Cercle> dao = factory.getDaoCercle();
	            dao.update((Cercle) forme);
	        } else if (forme.getClass() == Carre.class) {
	            AbstractDao<Carre> dao = factory.getDaoCarre();
	            dao.update((Carre) forme);
	        } else if (forme.getClass() == Rectangle.class) {
	            AbstractDao<Rectangle> dao = factory.getDaoRectangle();
	            dao.update((Rectangle) forme);
	        } else if (forme.getClass() == Triangle.class) {
	            AbstractDao<Triangle> dao = factory.getDaoTriangle();
	            dao.update((Triangle) forme);
	        } else {
	            AbstractDao<GroupeForme> dao = factory.getDaoGroupeForme();
	            dao.update((GroupeForme) forme);
	        }
	        factory.close();
	    }
		
	}


