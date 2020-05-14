import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author Melissa
 *
 */
public class BDD {
/**
 * nom du dessin
 */
	private static String NomBdd="bdd";
	/**
	 * modification du nom de la BDD
	 * @param nom nouveau nom
	 */
	public static void setNomDessin(final String nom){
		NomBdd= nom+ "";
	}
	/**
	 * obtenir la connectiona BDD
	 * @return connection à la bdd
	 */
	public static Connection getConnection() {
	    try {
            return DriverManager.getConnection(
                    "jdbc:derby:" + NomBdd + ";create=false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
