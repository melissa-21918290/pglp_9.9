

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 
 * @author Melissa
 *
 */
public class DaoCarreJDBC extends AbstractDao<Carre> {
	/**
	 * connexion a BDD
	 *
	 */
private final Connection connect;
/**
 * constructeur de la classe
 *
 * @param c connection pour la BDD
 */
public DaoCarreJDBC(final Connection c){
	connect =c;
}


	@Override
	public Carre create(final Carre object) {
		// TODO Auto-generated method stub
	      
		  final int un = 1, deux = 2, trois = 3, quatre = 4;
		        try {
		            PreparedStatement prepare = connect.prepareStatement(
		                    "INSERT INTO Forme"
		                    + " (variable)"
		                    + " VALUES(?)");
		                    prepare.setString(un, object.getvariable());
		                    prepare.executeUpdate();
		            prepare = connect.prepareStatement(
		                    "INSERT INTO Carre"
		                    + " (variable,HautGaucheX,HautGaucheY,longeur)"
		                    + " VALUES(?, ?, ?, ?)");
		            prepare.setString(un, object.getvariable());
		            prepare.setInt(deux, object.getHautGauche().getX());
		            prepare.setInt(trois, object.getHautGauche().getY());
		            prepare.setInt(quatre, object.getLongeur());
		            prepare.executeUpdate();
		        } catch (SQLException e) {
		            return null;
		        }
		        return object;
		
	}
/**
 * recuperer un element
 * @param id identifiant de l'élément a recuperer
 * @return l'element
 */
	@Override
	public Carre find(final String id) {
		// TODO Auto-generated method stub
		 final int un = 1;
	        Carre find = null;
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT * FROM Carre WHERE variable = ?");
	            prepare.setString(un, id);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	                Position p = new Position(result.getInt("HautGaucheX"),
	                        result.getInt("HautGaucheY"));
	                try {
	                    find = new Carre(id, p, result.getInt("longeur"));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return find;
	}
/**
 * recuperer tous les elements
 * @return tous les elements
 */
	@Override
	public ArrayList<Carre> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Carre> find = new ArrayList<Carre>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT variable FROM Carre");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                find.add(this.find(result.getString("variable")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Carre>();
        }
        return find;
	}
/**
 * modifier un élément du DAO
 * @param object l'élément a modifier
 * @return la modification
 */
	@Override
	public Carre update(final Carre object) {
		final int un = 1, deux = 2, trois = 3, quatre = 4;
        final Carre before = this.find(object.getvariable());
        if (before != null) {
            try {
                PreparedStatement prepare = connect.prepareStatement(
                "UPDATE Carre SET HautGaucheX = ?, HautGaucheY = ?, "
                + "longeur = ? WHERE variable = ?");
                prepare.setInt(un, object.getHautGauche().getX());
                prepare.setInt(deux, object.getHautGauche().getY());
                prepare.setInt(trois, object.getLongeur());
                prepare.setString(quatre, object.getvariable());
                prepare.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return before;
            }
        } else {
            return null;
        }
        return object;
	}

	/**
	 * supprimer un élément du DAO
	 * @return object élément a supprimer
	 */
	@Override
	public void delete(final Carre object) {
		// TODO Auto-generated method stub
		   final int un = 1;
	        try {
	            this.deleteComposant(object.getvariable());
	            PreparedStatement prepare = connect.prepareStatement(
	                    "DELETE FROM Carre WHERE variable = ?");
	            prepare.setString(un, object.getvariable());
	            prepare.executeUpdate();
	            prepare = connect.prepareStatement(
	                    "DELETE FROM Forme WHERE variable = ?");
	            prepare.setString(un, object.getvariable());
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

/**
 * supprimer toutes les associatios de la forme dans le groupe
 * @param id identifiant de la forme
 */
	private void deleteComposant(final String id) {
		 final int un = 1;
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "DELETE FROM Composition WHERE idComposant = ?");
	            prepare.setString(un, id);
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	        }
		
	}
		
	}

	


