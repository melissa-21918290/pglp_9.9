package forme;

public class Triangle extends Forme {
	private Position [] points;

	protected Triangle(String newVariableName) {
		super(newVariableName);
		// TODO Auto-generated constructor stub
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


