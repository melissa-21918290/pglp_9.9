package forme;
/**
 * 
 * @author Melissa
 *
 */
public class Triangle extends Forme {
	/**
	 * les trois points du tiangle
	 */
	private Position [] points;
/**
 * constructeur de la classe triangle
 * @param newVariableName nom de l avariable pour creer le triangle
 * @param point1 point 1
 * @param point2 point 2
 * @param point3 point 3
 */
	protected Triangle(String newVariableName, final Position point1, final Position point2, final Position point3) {
		super(newVariableName);
      final int trois = 3;
	        points = new Position[trois];
	        points[0] = point1.clone();
	        points[1] = point2.clone();
	        points[2] = point3.clone();
	    }
	

	@Override
	public void deplacer(int x, int y) {
		 final int trois = 3;
	        for (int i = 0; i < trois; i++) {
	            points[i].deplacer(x, y);
	        }
	    }
		
	

	@Override
	public void afficher() {
		   System.out.println("Triangle ("
	                + "position des points = " + points[0] + ", "
	                + points[1] + ", " + points[2] + ")");
	    }
	}


