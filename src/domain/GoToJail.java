package domain;
import org.json.JSONObject;

public class GoToJail extends Square {
	

	public GoToJail(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		GoToJail jail=GameController.getInstance().getMonopolyboard().getMiddleSquares().get(visitingJail);
		piece.getOwner().setInJail(true);
		piece.move(jail);
	}

	@Override
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}
	
	public static GoToJail fromJSON(JSONObject squareAsJSON) {
		GoToJail goToJail = null;
		
		try {
			String name = squareAsJSON.getString("name");
			goToJail = new GoToJail(name);
		} catch (Exception e) {
			
		}
		
		return goToJail;
	}
}
