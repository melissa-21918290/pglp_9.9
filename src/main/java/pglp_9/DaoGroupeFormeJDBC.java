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
		
/**
 * recuperer le groupe	
 */

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
	                ArrayList<Forme> list = findAssociation(id);
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
/** recherche toutes les associations d'un groupe
 * 
 * @param id identifiant du groupe
 * @return tous les associations du groupe
 */
	private ArrayList<Forme> findAssociation(String id) {
		final int un = 1;
        ArrayList<Forme> find = new ArrayList<Forme>();
        DaoFactoryJDBC factory = new DaoFactoryJDBC();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT idComposant "
                    + "FROM Composition WHERE idGroupe = ?");
            prepare.setString(un, id);
            ResultSet result = prepare.executeQuery();
            AbstractDao<Cercle> daoCe = factory.getDaoCercle();
            AbstractDao<Carre> daoCa = factory.getDaoCarre();
            AbstractDao<Rectangle> daoR = factory.getDaoRectangle();
            AbstractDao<Triangle> daoT = factory.getDaoTriangle();
            while (result.next()) {
                Forme f = daoCe.find(result.getString("idComposant"));
                if (f == null) {
                    f = daoCa.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = daoR.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = daoT.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = this.find(result.getString("idComposant"));
                }
                find.add(f);
            }
            factory.close();
        } catch (SQLException e) {
            factory.close();
            return new ArrayList<Forme>();
        }
        return find;
	}
	/**
	 * recuperer tous les element
	 */
	@Override
	public ArrayList<GroupeForme> findAll() {
		   ArrayList<GroupeForme> find = new ArrayList<GroupeForme>();
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT variable FROM GroupeForme");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                find.add(this.find(result.getString("variableName")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<GroupeForme>();
	        }
	        return find;
	    }
	/**
	 * modification 'un groupe
	 */

	@Override
	public GroupeForme update(GroupeForme object) {
		ArrayList<Forme> contenu = this.findAssociation(
                object.getvariable());
        if (!contenu.isEmpty()) {
            this.deleteAssociation(object.getvariable());
            DaoFactoryJDBC factory = new DaoFactoryJDBC();
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
        } else {
            return null;
        }
        return object;
	}
/**
 * suppression d'un groupe
 */
	@Override
	public void delete(GroupeForme object) {
		 final int un = 1;
	        try {
	            this.deleteAssociation(object.getvariable());
	            this.deleteAsso(object.getvariable());
	            PreparedStatement prepare = connect.prepareStatement(
	                    "DELETE FROM GroupeForme WHERE variableName = ?");
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
	 * supprimer toutes le association d'un groupe
	 * @param id identiafiant du groupe
	 */
	private void deleteAssociation(final String id) {
		  final int un = 1;
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "DELETE FROM Composition WHERE idGroupe = ?");
	            prepare.setString(un, id);
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	        }
	    }
		
	/**
	 * supprimer toutesles associations de la forme dzans le groupe
	 * @param id identifiant de la forme
	 */
	private void deleteAsso(final String id) {
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


