import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrawingTUI {

	public Command nextCommand(String cmd) {
		if (cmd.contains("=")) {
            Forme f = this.create(cmd);
            if (f != null) {
                return new CreationC(f);
            }
        } else if (cmd.contains("move")) {
            return this.depalcer(cmd);
        } else if (cmd.contains("delete")) {
            return this.supprimer(cmd);
        } else if (cmd.equals("help")) {
            System.out.println("Commandes disponibles : \n"
                    + "Créer un cercle :                  "
                    + "                  variableName = Ce"
                    + "rcle((x,y), rayon)\n"
                    + "Créer un carré :                   "
                    + "                  variableName = Ca"
                    + "rre((x,y), longueur)\n"
                    + "Créer un rectangle :               "
                    + "                  variableName = Re"
                    + "ctangle((x,y), longueur, largeur)\n"
                    + "Créer un triangle :                "
                    + "                  variableName = Tr"
                    + "iangle((x,y), (x,y), (x,y))\n"
                    + "Créer un groupe de forme(s) :      "
                    + "                  variableName = Gr"
                    + "oupe(variableName, ...)\n"
                    + "\n"
                    + "déplacer une forme ou un groupe :  "
                    + "                  move(variableName"
                    + ", (x,y))\n"
                    + "\n"
                    + "supprimer une forme ou un groupe : "
                    + "                  delete(variableNa"
                    + "me, ...)");
        } else if (!cmd.equalsIgnoreCase("exit")) {
            System.err.println("Commande non reconnu");
        }
        return null;
	}

	public void afficheDessin() {
		 DaoFactoryJDBC factory = new DaoFactoryJDBC();
	        AbstractDao<Cercle> daoCe = factory.getDaoCercle();
	        AbstractDao<Carre> daoCa = factory.getDaoCarre();
	        AbstractDao<Rectangle> daoR = factory.getDaoRectangle();
	        AbstractDao<Triangle> daoT = factory.getDaoTriangle();
	        AbstractDao<GroupeForme> daoG = factory.getDaoGroupeForme();
	        ArrayList<Forme> formes = new ArrayList<Forme>();
	        formes.addAll(daoCe.findAll());
	        formes.addAll(daoCa.findAll());
	        formes.addAll(daoR.findAll());
	        formes.addAll(daoT.findAll());
	        formes.addAll(daoG.findAll());
	        for (Forme f : formes) {
	            if (!this.estDansUnGroupe(f)) {
	                f.afficher();
	            }
	        }
	        factory.close();
	    }
		
	private boolean estDansUnGroupe(Forme f) {
		 Connection connect = BDD.getConnection();
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT * "
	                    + "FROM Composition WHERE idComposant = ?");
	            prepare.setString(1, f.getvariable());
	            ResultSet result = prepare.executeQuery();
	            boolean b = result.next();
	            connect.close();
	            return b;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            try {
	                connect.close();
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	            return false;
	        }
	}

	

	
	private Forme createCercle(final String variable, final String[] split2){
		
	        final int trois = 3;
	        String[] split = split2[1].split("Cercle");
	        if (!split[0].equals("")
	                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
	            System.err.println("Commande invalide, parenthèses manquantes");
	        } else {
	            split[1] = split[1].substring(1, split[1].length() - 1);
	            split = split[1].split(",");
	            if (split.length != trois) {
	                System.err.println("Commande invalide, "
	                        + split.length + "/" + trois + " arguments");
	            } else {
	                Position centre;
	                int rayon;
	                try {
	                    centre = new Position(split[0] + "," + split[1]);
	                    rayon = Integer.parseInt(split[2]);
	                    return new Cercle(variable, centre, rayon);
	                } catch (Exception e) {
	                    System.err.println("Commande invalide, "
	                            + "impossible de créer la forme");
	                }
	            }
	        }
	        return null;
	}
	
	public Forme createCarre(  final String variableName, final String[] split2) {
        final int trois = 3;
        String[] split = split2[1].split("Carre");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != trois) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + trois + " arguments");
            } else {
                Position topLeft;
                int longueur;
                try {
                    topLeft = new Position(split[0] + "," + split[1]);
                    longueur = Integer.parseInt(split[2]);
                    return new Carre(variableName, topLeft, longueur);
                } catch (Exception e) {
                    System.err.println("Commande invalide, "
                            + "impossible de créer la forme");
                }
            }
        }
        return null;
	}
	public Forme creatRectangle(final String variableName, final String[] split2) {
        final int quatre = 4;
        String[] split = split2[1].split("Rectangle");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != quatre) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + quatre + " arguments");
            } else {
                Position topLeft;
                int longueur;
                int largeur;
                try {
                    final int trois = 3;
                    topLeft = new Position(split[0] + "," + split[1]);
                    longueur = Integer.parseInt(split[2]);
                    largeur = Integer.parseInt(split[trois]);
                    return new Rectangle(
                            variableName, topLeft, longueur, largeur);
                } catch (Exception e) {
                    System.err.println("Commande invalide, "
                            + "impossible de créer la forme");
                }
            }
        }
        return null;
}
	private Forme createTriangle(  final String variableName, final String[] split2) {
        final int six = 6;
        String[]  split = split2[1].split("Triangle");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != six) {
                System.err.println("Commande invalide, "
            + split.length + "/" + six + " arguments");
            }
            Position[] point = {null, null, null};
            try {
                final int trois = 3, quatre = 4, cinq = 5;
                point[0] = new Position(split[0] + "," + split[1]);
                point[1] = new Position(split[2] + "," + split[trois]);
                point[2] = new Position(split[quatre] + "," + split[cinq]);
                return new Triangle(variableName, point[0], point[1], point[2]);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Commande invalide, "
                        + "impossible de créer la forme");
            }
        }
        return null;
}
	
private Forme createGroupe(     final String variableName, final String[] split2) {
    String[] split = split2[1].split("Groupe");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parenthèses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        return createGroupeComposants(variableName, split);
    }
    return null;
}

private Forme createGroupeComposants(
        final String variableName, final String[] split) {
    GroupeForme gf = new GroupeForme(variableName);
    for (String s : split) {
        Forme f = this.getForme(s);
        if (f != null) {
            gf.add(f);
        } else {
            return null;
        }
    }
    return gf;
}

private Forme create(final String command){
	String[] split;
    split = command.split("=");
    split[0] = split[0].trim();
    String variableName = split[0];
    if (split[0].contains(" ")) {
        System.out.println("Le nom de la variable contient des espaces");
    } else {
        split[1] = split[1].replace(" ", "");
        Forme f = null;
        if (split[1].contains("Cercle")) {
            f = this.createCercle(variableName, split);
        } else if (split[1].contains("Carre")) {
            f = this.createCarre(variableName, split);
        } else if (split[1].contains("Rectangle")) {
            f = this.creatRectangle(variableName, split);
        } else if (split[1].contains("Triangle")) {
            f = this.createTriangle(variableName, split);
        } else if (split[1].contains("Groupe")) {
            f = this.createGroupe(variableName, split);
        }
        return f;
    }
    return null;
}

private Command depalcer(final String command){
	 final int trois = 3;
     String cmd = command.replace(" ", "");
     String[] split = cmd.split("move");
     if (!split[0].equals("")
             || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
         System.err.println("Commande invalide, parenthèses manquantes");
     } else {
         split[1] = split[1].substring(1, split[1].length() - 1);
         split = split[1].split(",");
         if (split.length != trois) {
             System.err.println("Commande invalide, "
                     + split.length + "/" + trois + " arguments");
         } else {
             String variableName;
             Position deplacement;
             try {
                 variableName = split[0];
                 deplacement = new Position(split[1] + "," + split[2]);
                 Forme f = this.getForme(variableName);
                 if (f != null) {
                     return new DeplacerC(f, deplacement);
                 }
             } catch (Exception e) {
                 System.err.println("Commande invalide");
                 e.printStackTrace();
             }
         }
     }
     return null;
 }
private Command supprimer(final String command){
	String cmd = command.replace(" ", "");
    String[] split = cmd.split("delete");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parenthèses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        ArrayList<Forme> list = new ArrayList<Forme>();
        for (String var : split) {
            Forme f = this.getForme(var);
            if (f != null) {
                list.add(f);
            } else {
                System.err.println("Commande invalide, forme inconnu");
                return null;
            }
        }
        return new SupprimerC(list);
    }
    return null;
}

private Forme getForme(final String variable) {
	 DaoFactoryJDBC factory = new DaoFactoryJDBC();
     AbstractDao<Cercle> daoCe = factory.getDaoCercle();
     AbstractDao<Carre> daoCa = factory.getDaoCarre();
     AbstractDao<Rectangle> daoR = factory.getDaoRectangle();
     AbstractDao<Triangle> daoT = factory.getDaoTriangle();
     AbstractDao<GroupeForme> daoG = factory.getDaoGroupeForme();
     Forme f = daoCe.find(variable);
     if (f == null) {
         f = daoCa.find(variable);
     }
     if (f == null) {
         f = daoR.find(variable);
     }
     if (f == null) {
         f = daoT.find(variable);
     }
     if (f == null) {
         f = daoG.find(variable);
     }
     if (f == null) {
         System.err.println("Aucune forme n'existe à ce nom : "
                 + variable);
     }
     factory.close();
     return f;
 }
}