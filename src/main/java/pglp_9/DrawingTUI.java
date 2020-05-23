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

public class DrawingTUI {
	/**
	 * interpretation de la commande de creation du Cercle
	 * @param variable nom de la forme
	 * @param split2 la donnée
	 * @return la forme cercle
	 */
private Forme createCercle( final String variable, final String[] split2){
	
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





/**
 * interpretation de la commande de creation du Carre
 * @param variable nom de la variable 
 * @param split2 la donnée
 * @return la fomre Carre
 */

private Forme createCarre(  final String variable, final String[] split2){
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
            Position hautGauche;
            int longeur;
            try {
                hautGauche = new Position(split[0] + "," + split[1]);
                longeur = Integer.parseInt(split[2]);
                return new Carre(variable, hautGauche, longeur);
            } catch (Exception e) {
                System.err.println("Commande invalide, "
                        + "impossible de créer la forme");
            }
        }
    }
    return null;
}



/**
 * interpretation de la commande de creation du Rectangle
 * @param variable nom de la variable
 * @param split2 la donnée
 * @return la forme Rectangle
 */

private Forme createRectangle(final String variable, final String[] split2){
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
            Position hautGauche;
            int longeur;
            int largeur;
            try {
                final int trois = 3;
                hautGauche = new Position(split[0] + "," + split[1]);
                longeur = Integer.parseInt(split[2]);
                largeur = Integer.parseInt(split[trois]);
                return new Rectangle(
                        variable, hautGauche, longeur, largeur);
            } catch (Exception e) {
                System.err.println("Commande invalide, "
                        + "impossible de créer la forme");
            }
        }
    }
    return null;
}
	/**
	 * interpretation dela commande de creation du Triangle
	 * @param variable nom de la variable
	 * @param split2 la donnée
	 * @return la forme Triangle
	 */
private Forme createTriangle(final String variable, final String[] split2){
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
            return new Triangle(variable, point[0], point[1], point[2]);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Commande invalide, "
                    + "impossible de créer la forme");
        }
    }
    return null;
}
/**
 * interpretation de la commande Groupe forme
 * @param variable non de la variable
 * @param split2 la données
 * @return le Groupe de formes
 */
private Forme createGroupe(final String variable, final String[] split2){
	String[] split = split2[1].split("Groupe");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parenthèses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        return createGroupeComposants(variable, split);
    }
    return null;
}

/**
 * interpretation de la commande pour la greation d'une partie composante
 * @param variable nom de la variable
 * @param split la données
 * @return le groupe
 */
private Forme createGroupeComposants(String variable, String[] split) {
    GroupeForme gf = new GroupeForme(variable);
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

/**
 * recupere la forme 
 * @param variable nom de la forme
 * @return la forme
 */
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
/**
 * interpretation de la commande de creation de la forme
 * @param cmd2 la commande
 * @return la forme
 */
private Forme create(final String cmd2){
	String[] split;
    split = cmd2.split("=");
    split[0] = split[0].trim();
    String variable = split[0];
    if (split[0].contains(" ")) {
        System.out.println("Le nom de la variable contient des espaces");
    } else {
        split[1] = split[1].replace(" ", "");
        Forme f = null;
        if (split[1].contains("Cercle")) {
            f = this.createCercle(variable, split);
        } else if (split[1].contains("Carre")) {
            f = this.createCarre(variable, split);
        } else if (split[1].contains("Rectangle")) {
            f = this.createRectangle(variable, split);
        } else if (split[1].contains("Triangle")) {
            f = this.createTriangle(variable, split);
        } else if (split[1].contains("Groupe")) {
            f = this.createGroupe(variable, split);
        }
        return f;
    }
    return null;
}
/**
 * interpretation de la commande pour deplacer la forme
 * @param cmd2 la commande
 * @return la coommande de deplacement
 */
private Command move(final String cmd2){
	final int trois = 3;
    String cmd = cmd2.replace(" ", "");
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
            String variable;
            Position deplacement;
            try {
                variable = split[0];
                deplacement = new Position(split[1] + "," + split[2]);
                Forme f = this.getForme(variable);
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
/**
 * interpretation de la commande pour supprimer la forme
 * @param cmd2 la commande
 * @return la commande de suppression
 */
private Command remove(final String cmd2) {
    String cmd = cmd2.replace(" ", "");
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
/**
 * interpretation d'une commande
 * @param cmd commande à interpreter
 * @return la commande
 */
public Command nextCommand(final String cmd) {
    if (cmd.contains("=")) {
        Forme f = this.create(cmd);
        if (f != null) {
            return new CreationC(f);
        }
    } else if (cmd.contains("move")) {
        return this.move(cmd);
    } else if (cmd.contains("delete")) {
        return this.remove(cmd);
    } else if (cmd.equals("start")) {
        System.out.println("Liste des commandes: \n"
                + "* Cercle:....."
                + "variable = Ce"+ "rcle((x,y), rayon)\n"
                + "* Carré :....."+ " variable = Ca"
                + "rre((x,y), longueur)\n"
                + "* Rectangle : ......"
                + " variable = Re"
                + "ctangle((x,y), longueur, largeur)\n"
                + "* Triangle :....."
                + "variable = Tr"
                + "iangle((x,y), (x,y), (x,y))\n"
                + "* Groupe de formes :....."
                + "variable = Gr"
                + "oupe(variable, ...)\n"
                + "\n"
                + "* déplacer une forme ou un groupe :....."
                + "  move(variable"
                + ", (x,y))\n"
                + "* supprimer une forme ou un groupe :....."
                + " delete(variable, ...)"
                +"\n\n\n ............veuillez introduire une commande tout en respectant la syntaxe.............\n");
    } else if (!cmd.equalsIgnoreCase("exit")) {
        System.err.println("Commande non reconnue");
    }
    return null;
}

/**
 * voir si une forme existe dans un groupe
 * @param f forme a rechercher
 * @return vrai si la forme existe dans le groupe
 */
private boolean estDansUnGroupe(final Forme f) {
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
/**
 * affichage des formes
 */
public  void affichageDuDessin() {
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

}