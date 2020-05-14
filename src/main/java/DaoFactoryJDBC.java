

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
	public DaoFactoryJDBC(){
		connect=BDD.getConnection();
		
	}

}
