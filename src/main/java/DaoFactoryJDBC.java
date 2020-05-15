

import java.sql.Connection;

/**
 * 
 * @author Melissa
 *
 */
public class DaoFactoryJDBC {
	/**
	 * connectiona la base de données
	 */
	private Connection connect ;
	/**
	 * constructeur de la classe
	 */
	public DaoFactoryJDBC(){
		connect=BDD.getConnection();
		
	}
	
	/**
	 * recuper le dao groupeForme
	 * @return le dao
	 */
public AbstractDao<GroupeForme>getDaoGroupeForme(){
	return new DaoGroupeFormeJDBC();
	
}
/**
 * recuperer le dao du Triangle
 * @return le dao
 */
public AbstractDao<Triangle> getDaoTriangle() {
    return new DaoTriangleJDBC(connect);
}
/**
 * recupere le dao du Rectangle
 * @return le dao
 */
public AbstractDao<Rectangle> getDaoRectangle() {
    return new DaoRectangleJDBC(connect);
}
/**
 * recuperer le dao du Cercle
 * @return le dao
 */
public AbstractDao<Cercle> getDaoCercle() {
    return new DaoCercleJDBC(connect);
}
/**
 * recuperer le dao Carre
 * @return le dao
 */
public AbstractDao<Carre> getDaoCarre() {
    return new DaoCarreJDBC(connect);
}
}
