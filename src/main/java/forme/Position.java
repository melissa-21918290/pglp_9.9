package forme;
import java.io.CharConversionException;

public class Position {
	
	private int x;
	private int y;
	public Position(){
		x=0;
		y=0;
	}

	public Position(final int xValue, final int yValue){
		x= xValue;
		y=yValue;
	}
	public Position(final String position) throws CharConversionException {
        position.replace(" ", "");
        if (position.charAt(0) != '('
        || position.charAt(position.length() - 1) != ')') {
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
	
	public int getX(){
		return y;
	}
	public int getY(){
		return y;
	}
	public void deplacer(final int xValue, final int yValue){
		x+= xValue;
		y+= yValue;
	}
	public String toString(){
		return"("+ x + "," + y + ")";	}
	@Override
	public Position clone(){
		return new Position(x,y);
	}
}
