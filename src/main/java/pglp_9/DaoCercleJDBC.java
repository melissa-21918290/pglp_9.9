package pglp_9;


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
public class DaoCercleJDBC extends AbstractDao<Cercle> {
	/**
	 * connection a la BDD
	 */
	private final Connection connect;
	/**
	 * constructeur de la classe Cercle
	 * @param c connection pourla BDD
	 */
	public DaoCercleJDBC(final Connection c){
		connect =c;
		
	}
/**ajouter un élément au DAO
 * @param object l'élement à ajouter
 * @return creation
 * 
 */
	@Override
	public Cercle create(Cercle object) {
		 final int un = 1, deux = 2, trois = 3, quatre = 4;
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "INSERT INTO Forme"
	                    + " (variable)"
	                    + " VALUES(?)");
	                    prepare.setString(un, object.getvariable());
	                    prepare.executeUpdate();
	            prepare = connect.prepareStatement(
	                    "INSERT INTO Cercle"
	                    + " (variable,centreX,centreY,rayon)"
	                    + " VALUES(?, ?, ?, ?)");
	            prepare.setString(un, object.getvariable());
	            prepare.setInt(deux, object.getCentre().getX());
	            prepare.setInt(trois, object.getCentre().getY());
	            prepare.setInt(quatre, object.getRayon());
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	            return null;
	        }
	        return object;
	}
/**
 *recupererun élement  
 *@param id identifiant de l'element a obtenir
 *@return l'élement a recuperer
 */
	@Override
	public Cercle find(String id) {
		   final int un = 1;
	        Cercle find = null;
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT * FROM Cercle WHERE variable = ?");
	            prepare.setString(un, id);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	                Position centre = new Position(
	                        result.getInt("centreX"),
	                        result.getInt("centreY"));
	                try {
	                    find = new Cercle(id, centre, result.getInt("rayon"));
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
	public ArrayList<Cercle> findAll() {
		  ArrayList<Cercle> find = new ArrayList<Cercle>();
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT variable FROM Cercle");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                find.add(this.find(result.getString("variable")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Cercle>();
	        }
	        return find;
	}
	/**
	 * modifier un element du DAO
	 * @paramobject élement a modifier
	 * @return la modification
	 */

	@Override
	public Cercle update(final Cercle object) {
		  final int un = 1, deux = 2, trois = 3, quatre = 4;
	        final Cercle before = this.find(object.getvariable());
	        if (before != null) {
	            try {
	                PreparedStatement prepare = connect.prepareStatement(
	                "UPDATE Cercle SET centreX = ?, centreY = ?, "
	                + "rayon = ? WHERE id = ?");
	                prepare.setInt(un, object.getCentre().getX());
	                prepare.setInt(deux, object.getCentre().getY());
	                prepare.setInt(trois, object.getRayon());
	                prepare.setString(quatre, object.getvariable());
	                prepare.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                return before;
	            }
	        } else {
	            return null;
	}
			return object;}
/**
 * supprimer un element du DAO
 * @param object élement a supprimer
 */
	@Override
	public void delete(final Cercle object) {
	    final int un = 1;
        try {
            this.deleteAllCompo(object.getvariable());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM Cercle WHERE variable = ?");
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
	 * supprimer touttes les associaton de la forme dans le groupe
	 * @param id identififiant de la forme
	 */
	private void deleteAllCompo(final String id) {
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


