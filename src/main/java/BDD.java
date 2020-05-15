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
	 private static String nameBdd = "tmp";
	    /**
	     * modifier le nom de la base de donnée.
	     * @param name nouveau nom
	     */
	    public static void setNomDessin(final String name) {
	        nameBdd = name + "";
	    }

	/**
	 * obtenir la connectiona BDD
	 * @return connection à la bdd
	 */
	public static Connection getConnection() {
	    try {
            return DriverManager.getConnection(
                    "jdbc:derby:" + nameBdd + ";create=false");
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
	        BDD.supprimerTab(connect);
	        BDD.CreateTableForme(connect);
	        BDD.CreateTableTriangle(connect);
	        BDD.CreateTableCarre(connect);
	        CreateTableRectangle(connect);
	        CreateTableCercle(connect);
	        CreateTableGroupeForme(connect);
	        createTableRelation(connect);
	        connect.close();
	    }
	/**
	 * creation de BDD
	 */
	 public static void creerBDD()  {
	        Connection c;
	        try {
	            c = DriverManager.getConnection(
	                "jdbc:derby:" + nameBdd + ";create=true");
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
	  * creation de la table Forme
	  * @param connect connectiona bdd
	  * @throws SQLException errur sql
	  */
	 private static void CreateTableForme(final Connection connect)
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
	 private static void CreateTableTriangle(final Connection connect)
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
	 /**
	  * creation de la table Rectangle
	  * @param connect connection a la bdd
	  * @throws SQLException erreur sql
	  */
	 private static void CreateTableRectangle(final Connection connect)
			 throws SQLException {
	        String table = "create table Rectangle ("
	                + "variable varchar(30) primary key,"
	                + "HautGaucheX int,"
	                + "HautGaucheY int,"
	                + "longeur int,"
	                + "largeur int,"
	                + "foreign key (variable) references Forme (variable)"
	                + ")";
	        Statement stat = connect.createStatement();
	        stat.execute(table);
	    }
	 /**
	  * creation de la table Cercle
	  * @param connect connection a la bdd
	  * @throws SQLException erreur sql
	  */
	 private static void CreateTableCercle(final Connection connect)
			    throws SQLException {
	        String table = "create table Cercle ("
	                + "variable varchar(30) primary key,"
	                + "centreX int,"
	                + "centreY int,"
	                + "rayon int,"
	                + "foreign key (variable) references Forme (variable)"
	                + ")";
	        Statement stat = connect.createStatement();
	        stat.execute(table);
	    }
	 /**
	  * creation de la table GroupeForme
	  * @param connect connection a la bdd
	  * @throws SQLException erreur sql
	  */
	 private static void CreateTableGroupeForme(final Connection connect)
			   throws SQLException {
	        String table = "create table GroupeForme ("
	                + "variable varchar(30) primary key,"
	                + "foreign key (variable) references Forme (variable)"
	                + ")";
	        Statement stat = connect.createStatement();
	        stat.execute(table);
	    }
	 private static void createTableRelation(final Connection connect)
		      throws SQLException {
	        String table = "create table Composition ("
	                + "idGroupe varchar(30),"
	                + "idComposant varchar(30),"
	                + "primary key (idGroupe, idComposant),"
	                + "foreign key (idGroupe) references "
	                + "GroupeForme (variableName),"
	                + "foreign key (idComposant) "
	                + "references Forme (variableName)"
	                + ")";
	        Statement stat = connect.createStatement();
	        stat.execute(table);
	    }
	}
	