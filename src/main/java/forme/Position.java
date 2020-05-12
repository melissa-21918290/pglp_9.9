package forme;
import java.io.CharConversionException;
/**
 * 
 * @author Melissa
 *
 */
public class Position {
	/**
	 * abscisse
	 */
	private int x;
	/**
	 * ordonné
	 */
	private int y;
	/**
	 * constructeur en (0,0)
	 */
	public Position(){
		x=0;
		y=0;
	}
/**
 * constructeur avec x et y
 * @param xValue abscisse
 * @param yValue ordonné
 */
	public Position(final int xValue, final int yValue){
		x= xValue;
		y=yValue;
	}
	/**
	 * constructeur de position 
	 * @param position  string
	 * @throws CharConversionException invalid string
	 */
	public Position(final String position) throws CharConversionException {
        position.replace(" ", "");
        if (position.charAt(0) != '('|| position.charAt(position.length() - 1) != ')') {
            System.err.println(position);
            throw new CharConversionException();
        }
        String position2 = position.substring(1, position.length() - 1);
        String[] positionSplit = position2.split(",");
        if (positionSplit.length != 2) {
            System.err.println(position);
            throw new CharConversionException();
        }
        try {
            x = Integer.parseInt(positionSplit[0]);
            y = Integer.parseInt(positionSplit[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw e;
        }
    }
	/**
	 * 
	 * @return la valeur de x en abscisse
	 */
	public int getX(){
		return y;
	}
	/**
	 * 
	 * @return la valeur de y en ordonné
	 */
	public int getY(){
		return y;
	}
	/**
	 * cepalcer la position
	 * @param xValue abcsisse
	 * @param yValue ordonnée
	 */
	public void deplacer(final int xValue, final int yValue){
		x+= xValue;
		y+= yValue;
	}
	/**
	 * conversion en string d'une posotion
	 */
	public String toString(){
		return"("+ x + "," + y + ")";	}
	/**
	 * retourne une copie de la position 
	 */
	@Override
	public Position clone(){
		return new Position(x,y);
	}
}
