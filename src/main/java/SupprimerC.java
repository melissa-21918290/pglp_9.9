import java.util.ArrayList;

/**
 * 
 * @author Melissa
 *
 */
public class SupprimerC implements Command {
	/**
	 * listes des formes à supprimer
	 */
	 private ArrayList<Forme> list;
	 
	 public SupprimerC(final ArrayList<Forme>f){
		 list=f;
	 }
	 /**
	  * executer la commande
	  */
	public void executer() {
		 DaoFactoryJDBC factory = new DaoFactoryJDBC();
	        for (Forme forme : list) {
	            if (forme.getClass() == Cercle.class) {
	                AbstractDao<Cercle> dao = factory.getDaoCercle();
	                dao.delete((Cercle) forme);
	            } else if (forme.getClass() == Carre.class) {
	                AbstractDao<Carre> dao = factory.getDaoCarre();
	                dao.delete((Carre) forme);
	            } else if (forme.getClass() == Rectangle.class) {
	                AbstractDao<Rectangle> dao = factory.getDaoRectangle();
	                dao.delete((Rectangle) forme);
	            } else if (forme.getClass() == Triangle.class) {
	                AbstractDao<Triangle> dao = factory.getDaoTriangle();
	                dao.delete((Triangle) forme);
	            } else {
	                AbstractDao<GroupeForme> dao = factory.getDaoGroupeForme();
	                dao.delete((GroupeForme) forme);
	            }
	        }
	        factory.close();
	    }
		
	}


