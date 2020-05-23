package pglp_9;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoRectangleJDBC extends AbstractDao<Rectangle> {
/**
 * connection à la  bdd
 */
	private final Connection connect;
/**
 * constructeur de la classe
 * @param c
 */
	public DaoRectangleJDBC(Connection c) {
		connect =c;
	}
	/**ajouter un element au dao Rectangle
	 * 
	 */

	@Override
	public Rectangle create(final Rectangle object) {
		final int un = 1, deux = 2, trois = 3,
                quatre = 4, cinq = 5;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme"
                    + " (variable)"
                    + " VALUES(?)");
                    prepare.setString(un, object.getvariable());
                    prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO Rectangle"
                    + " (variable,hautGaucheX,hautGaucheY,longeur,largeur)"
                    + " VALUES(?, ?, ?, ?, ?)");
            prepare.setString(un, object.getvariable());
            prepare.setInt(deux, object.gethautGauche().getX());
            prepare.setInt(trois, object.gethautGauche().getY());
            prepare.setInt(quatre, object.getLongeur());
            prepare.setInt(cinq, object.getLargeur());
            prepare.executeUpdate();
        } catch (SQLException e) {
            return null;
        }
        return object;
	}
/**
 * recuperer un élément
 */
	@Override
	public Rectangle find(String id) {
		final int un = 1;
        Rectangle find = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Rectangle WHERE variable = ?");
            prepare.setString(un, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                Position p = new Position(
                        result.getInt("hautGaucheX"),
                        result.getInt("hautGaucheY")
                );
                try {
                    find = new Rectangle(id, p,
                            result.getInt("longeur"),
                            result.getInt("largeur")
                    );
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
 * recuperer tous les éléments
 */
	@Override
	public ArrayList<Rectangle> findAll() {
		ArrayList<Rectangle> find = new ArrayList<Rectangle>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT variable FROM Rectangle");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                find.add(this.find(result.getString("variable")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Rectangle>();
        }
        return find;
	}

	@Override
	public Rectangle update(Rectangle object) {
	     final int un = 1, deux = 2, trois = 3, quatre = 4, cinq = 5;
	        final Rectangle before = this.find(object.getvariable());
	        if (before != null) {
	            try {
	                PreparedStatement prepare = connect.prepareStatement(
	                "UPDATE Rectangle SET hautGaucheX = ?, hautGaucheY = ?, "
	                + "longeur = ?, largeur = ? WHERE variable = ?");
	                prepare.setInt(un, object.gethautGauche().getX());
	                prepare.setInt(deux, object.gethautGauche().getY());
	                prepare.setInt(trois, object.getLongeur());
	                prepare.setInt(quatre, object.getLargeur());
	                prepare.setString(cinq, object.getvariable());
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
 * supprimer le dao
 */
	@Override
	public void delete(Rectangle object) {
	    final int un = 1;
        try {
            this.deleteAssociation(object.getvariable());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM Rectangle WHERE variable = ?");
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
	 * supprimer toutes les associations
	 * @param ididentifiant de la forme
	 */
	private void deleteAssociation(final String id) {
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


