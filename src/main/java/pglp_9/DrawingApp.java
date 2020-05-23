package pglp_9;
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
	/**
	 * execution des commandes
	 */
	  public void run() {
	        System.out.print("Bonjour, pour commencer votre dessin entrez start, et exit pour quitter.\n>");
	        String cmd = saisir.nextLine();
	        Command c;
	        while (!cmd.equals("exit")) {
	            c = D.nextCommand(cmd);
	            if (c != null) {
	                c.executer();
	            }
	            D.affichageDuDessin();
	            System.out.print("saisir ==>");
	            cmd = saisir.nextLine();
	           
	        }
	    }


	    /**
	     * début du programme.
	     * @param args arguments
	     * @throws Exception erreur bdd
	     */
	    public static void main(final String[] args) throws Exception {
	        BDD.createDataBase();
	        BDD.RestaurBDD();
	        DrawingApp app = new DrawingApp();
	        app.run();
	    }
	}