package pglp_9;

/**
 * 
 * @author Melissa
 *
 */
public class CreationC implements Command {
/**
 * forme à creer
 */
	private Forme forme;
	/**
	 * constructeur de la classe
	 * @param f forme a créer
	 */
	public CreationC(final Forme f){
		forme=f;
	}
	/**
	 * 
	 */
	public void executer() {
		Forme f;
        DaoFactoryJDBC factory = new DaoFactoryJDBC();
        if (forme.getClass() == Cercle.class) {
            AbstractDao<Cercle> dao = factory.getDaoCercle();
            f = dao.create((Cercle) forme);
        } else if (forme.getClass() == Carre.class) {
            AbstractDao<Carre> dao = factory.getDaoCarre();
            f = dao.create((Carre) forme);
        } else if (forme.getClass() == Rectangle.class) {
            AbstractDao<Rectangle> dao = factory.getDaoRectangle();
            f = dao.create((Rectangle) forme);
        } else if (forme.getClass() == Triangle.class) {
            AbstractDao<Triangle> dao = factory.getDaoTriangle();
            f = dao.create((Triangle) forme);
        } else {
            AbstractDao<GroupeForme> dao = factory.getDaoGroupeForme();
            f = dao.create((GroupeForme) forme);
        }
        factory.close();
        if (f != null) {
            System.out.println("La forme "  + forme.getvariable()+" est ajoutée avec succes " );
        } else {
            System.out.println(" La forme" + forme.getvariable()+ " existe déjà veillez changer de nom ");
        }
    }

	
}
