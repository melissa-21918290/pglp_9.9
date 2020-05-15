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
public class DaoTriangleJDBC extends AbstractDao<Triangle> {
/**
 * connection a bdd
 */
	private final Connection connect;
	/**
	 * constructeur de la classe
	 * @param c connection pour la bdd
	 */
	public DaoTriangleJDBC(final Connection c) {
		connect = c;
	}
/**
 * ajouter un élément au dao
 */
	@Override
	public Triangle create(Triangle object) {
		final int un = 1, deux = 2, trois = 3,
                quatre = 4, cinq = 5, six = 6, sept = 7;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme"
                    + " (variable)"
                    + " VALUES(?)");
                    prepare.setString(un, object.getvariable());
                    prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO Triangle"
                    + " (variable,"
                    + "point1X,point1Y,"
                    + "point2X,point2Y,"
                    + "point3X,point3Y)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)");
            prepare.setString(un, object.getvariable());
            prepare.setInt(deux, object.getPosition(0).getX());
            prepare.setInt(trois, object.getPosition(0).getY());
            prepare.setInt(quatre, object.getPosition(1).getX());
            prepare.setInt(cinq, object.getPosition(1).getY());
            prepare.setInt(six, object.getPosition(2).getX());
            prepare.setInt(sept, object.getPosition(2).getY());
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
	public Triangle find(String id) {
		  final int un = 1;
	        Triangle find = null;
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT * FROM Triangle WHERE variable = ?");
	            prepare.setString(un, id);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	                Position[] p = {
	                    new Position(
	                            result.getInt("point1X"),
	                            result.getInt("point1Y")),
	                    new Position(
	                            result.getInt("point2X"),
	                            result.getInt("point2Y")),
	                    new Position(
	                            result.getInt("point3_x"),
	                            result.getInt("point3_y")),
	                };
	                find = new Triangle(id, p[0], p[1], p[2]);
	            }
	        } catch (SQLException e) {
	            return null;
	        }
	        return find;
	}

	@Override
	public ArrayList<Triangle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Triangle update(final Triangle object) {
		final int un = 1, deux = 2, trois = 3,
                quatre = 4, cinq = 5, six = 6, sept = 7;
        final Triangle before = this.find(object.getvariable());
        if (before != null) {
            try {
                PreparedStatement prepare = connect.prepareStatement(
                "UPDATE Triangle SET point1X = ?, point1Y = ?, "
                + "point2X = ?, point2Y= ?, point3X = ?, point3Y = ?"
                + " WHERE variable = ?");
                prepare.setInt(un, object.getPosition(0).getX());
                prepare.setInt(deux, object.getPosition(0).getY());
                prepare.setInt(trois, object.getPosition(1).getX());
                prepare.setInt(quatre, object.getPosition(1).getY());
                prepare.setInt(cinq, object.getPosition(2).getX());
                prepare.setInt(six, object.getPosition(2).getY());
                prepare.setString(sept, object.getvariable());
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
 * supprimer un element du dao
 */
	@Override
	public void delete(Triangle object) {
		 final int un = 1;
	        try {
	            this.deleteAssociation(object.getvariable());
	            PreparedStatement prepare = connect.prepareStatement(
	                    "DELETE FROM Triangle WHERE variableName = ?");
	            prepare.setString(un, object.getvariable());
	            prepare.executeUpdate();
	            prepare = connect.prepareStatement(
	                    "DELETE FROM Forme WHERE variableName = ?");
	            prepare.setString(un, object.getvariable());
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	/**
	 * supprimer toutes les associations de la forme dans le groupe
	 * @param id identifiant de la forme
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


