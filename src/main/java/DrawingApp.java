import java.io.File;
import java.util.Scanner;


/**
 * 
 * @author Melissa
 *
 */
public class DrawingApp {
	/**
	 * lecture In
	 */
	private Scanner saisir;
	/**
	 * interpreter les commandes
	 */
	private DrawingTUI  D;
	/**
	 * constructeur de la classe
	 */
	public DrawingApp(){
		D = new DrawingTUI();
        saisir = new Scanner(System.in);
	}
	public void run(){
		  System.out.print("Entrez help pour obtenir la liste des commandes.\n");
	        String cmd = saisir.nextLine();
	        Command c;
	        while (!cmd.equalsIgnoreCase("exit")) {
	            c = D.nextCommand(cmd);
	            if (c != null) {
	                c.executer();
	            }
	            D.afficheDessin();
	            cmd = saisir.nextLine();
	        }
	    }
	@SuppressWarnings("resource")
    public static String selectNameDessin() throws Exception {
        System.out.println("Modifier un dessin ? Y/N");
        Scanner s = new Scanner(System.in);
        String name = "";
        while (!name.equalsIgnoreCase("y") && !name.equalsIgnoreCase("n")
                && !name.equalsIgnoreCase("exit")) {
            name = s.nextLine();
        }
        if (name.equalsIgnoreCase("y")) {
            System.out.println("Entrez un nom de dessin : ");
            name = s.nextLine();
            BDD.setNomDessin(name);
            File f = new File(name);
            if (!f.exists()) {
                System.err.println("Aucun dessin n'existe à ce nom");
                throw new Exception();
            }
            return name;
        }
        return "tmp";
    }
    /**
     * enregistrer un dessin.
     * @param name nom du dessin qui a été modifié
     */
    public static void enregistre(final String name) {
        Scanner s = new Scanner(System.in);
        if (!name.equals("tmp")) {
            System.out.println("1. Enregistrer vers \"" + name + "\"");
            System.out.println("2. Enregistrer sous ...");
            String reponse  = "";
            while (!reponse.equals("1") && !reponse.equals("2")
                    && !reponse.equalsIgnoreCase("exit")) {
                s = new Scanner(System.in);
            }
            if (reponse.equals("1") || reponse.equalsIgnoreCase("exit")) {
                s.close();
                return;
            }
        }
        System.out.println("entrez un nom pour sauvegarder votre dessin :");
        String reponse  = "tmp";
        while (reponse.equals("tmp") && new File(reponse).exists()) {
            reponse = s.nextLine();
        }
        if (reponse.equalsIgnoreCase("exit")) {
            s.close();
            return;
        } else {
            File f = new File(name);
            if (!f.exists()) {
                f.renameTo(new File(reponse));
            }
        }
        s.close();
    }
    /**
     * début du programme.
     * @param args arguments
     * @throws Exception erreur bdd
     */
    public static void main(final String[] args) throws Exception {
        try {
            String name = selectNameDessin();
            if (name.equals("tmp")) {
                BDD.creerBDD();
                BDD.RestaurBDD();
            } else {
                BDD.setNomDessin(name);
            }
            DrawingApp app = new DrawingApp();
            app.run();
            enregistre(name);
        } catch (Exception e) {
            e.getMessage();
        }
    }
	}


