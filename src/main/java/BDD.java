import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
	/**
	 * restaurer la BDD
	 * @throws Exception
	 */
	public static void RestaurBDD() throws Exception{
		 Connection connect = BDD.getConnection();
	        BDD.delTables(connect);
	        Bdd.initTableForme(connect);
	        Bdd.initTableTriangle(connect);
	        Bdd.initTableCarre(connect);
	        initTableRectangle(connect);
	        initTableCercle(connect);
	        initTableGroupeForme(connect);
	        initTableRelation(connect);
	        connect.close();
	    }
	/**
	 * creation de BDD
	 */
	 public static void creerBDD()  {
	        Connection c;
	        try {
	            c = DriverManager.getConnection(
	                "jdbc:derby:" + NomBdd + ";create=true");
	            c.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 /**
	  * suppression des tables
	  * @param connect connection ala BDD
	  */
	 private static void supprimerTab(final Connection connect){
		 Statement stat =null;
		 try {
	            stat = connect.createStatement();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        try {
	            stat.execute("drop table Composition");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table GroupeForme");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table Cercle");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table Rectangle");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table Carre");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table Triangle");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table Forme");
	        } catch (SQLException e) {
	        }
		 
	 }
	 /**
	  * 
	  * @param connect
	  * @throws SQLException
	  */
	 private static void CreateTabForme(final Connection connect)
	      throws SQLException {
	          String table = "create table Forme ("
	                  + "variable varchar(30) primary key"
	                  + ")";
	          Statement stat = connect.createStatement();
	          stat.execute(table);
	      
	 }
	 /**
	  * creeation de la table triangle
	  * @param connect connection a la bdd
	  * @throws SQLException erreur sql
	  */
	 private void CreateTableTriangle(final Connection connect)
			  throws SQLException {
	        String table = "create table Triangle ("
	                + "variable varchar(30) primary key,"
	                + "point1X int,"
	                + "point1Y int,"
	                + "point2X int,"
	                + "point2Y int,"
	                + "point3X int,"
	                + "point3Y int,"
	                + "foreign key (variable) references Forme (variable)"
	                + ")";
	        Statement stat = connect.createStatement();
	        stat.execute(table);
	    }
	 /**
	  * creation de la table Crre
	  * @param connect conenctiona la bdd
	  * @throws SQLException erreur sql
	  */
	 private static void CreateTableCarre(final Connection connect)
			    throws SQLException {
	        String table = "create table Carre ("
	                + "variable varchar(30) primary key,"
	                + "HautGaucheX int,"
	                + "HautGaucheY int,"
	                + "longeur int,"
	                + "foreign key (variable) references Forme (variable)"
	                + ")";
	        Statement stat = connect.createStatement();
	        stat.execute(table);
	    }
	}
	