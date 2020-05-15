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
public class DaoGroupeFormeJDBC extends AbstractDao<GroupeForme> {
/**
 * connectiona bdd
 */
	private final Connection connect;
	/**
	 * constructeur de la classe
	 * @param c connection pour la bdd
	 */
	 public DaoGroupeFormeJDBC(final Connection c) {
	        connect = c;
	    }
	@Override
	public GroupeForme create(GroupeForme object) {
		final int un = 1;
        DaoFactoryJDBC factory = new DaoFactoryJDBC();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme"
                    + " (variable)"
                    + " VALUES(?)");
            prepare.setString(un, object.getvariable());
            prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO GroupeForme"
                    + " (variable)"
                    + " VALUES(?)");
            prepare.setString(un, object.getvariable());
            prepare.executeUpdate();
            for (Forme f : object.getList()) {
                if (f.getClass() == Cercle.class) {
                    AbstractDao<Cercle> dao = factory.getDaoCercle();
                    dao.create((Cercle) f);
                } else if (f.getClass() == Carre.class) {
                    AbstractDao<Carre> dao = factory.getDaoCarre();
                    dao.create((Carre) f);
                } else if (f.getClass() == Rectangle.class) {
                    AbstractDao<Rectangle> dao = factory.getDaoRectangle();
                    dao.create((Rectangle) f);
                } else if (f.getClass() == Triangle.class) {
                    AbstractDao<Triangle> dao = factory.getDaoTriangle();
                    dao.create((Triangle) f);
                } else {
                    this.create((GroupeForme) f);
                }
                this.createAssociation(
                        object.getvariable(), f.getvariable());
            }
            factory.close();
        } catch (SQLException e) {
            factory.close();
            return null;
        }
        return object;
	}
/**
 * creer une association d'un groupe contenant une forme.
 * @param idGroupe groupe 
 * @param idComposant forme
 */
	private void createAssociation(final String idGroupe, final String idComposant) {
		        final int un = 1, deux = 2;
		        try {
		            PreparedStatement prepare = connect.prepareStatement(
		            "INSERT INTO Composition"
		            + " (idGroupe, idComposant)"
		            + " VALUES(?, ?)");
		            prepare.setString(un, idGroupe);
		            prepare.setString(deux, idComposant);
		            prepare.executeUpdate();
		        } catch (SQLException e) {
		        }
		    }
		
	

	@Override
	public GroupeForme find(String id) {
		 final int un = 1;
	        GroupeForme find = null;
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT * FROM GroupeForme WHERE variable= ?");
	            prepare.setString(un, id);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	                find = new GroupeForme(id);
	                ArrayList<Forme> list = findComposition(id);
	                for (Forme f : list) {
	                    find.add(f);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return find;
	}

	private ArrayList<Forme> findComposition(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<GroupeForme> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupeForme update(GroupeForme object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(GroupeForme object) {
		// TODO Auto-generated method stub

	}

}
