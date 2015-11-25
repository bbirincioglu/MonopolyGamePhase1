package domain;
import org.json.JSONObject;


public class ChanceSquare extends Square {

	public ChanceSquare(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		String s=piece.getOwner().selectCommunityCard(GameController.getInstance().getMonopolyBoard());
	}

	@Override
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}
	
	public static ChanceSquare fromJSON(JSONObject squareAsJSON) {
		ChanceSquare chanceSquare = null;
		
		try {
			String name = squareAsJSON.getString("name");
			chanceSquare = new ChanceSquare(name);
		} catch (Exception e) {
			
		}
		
		return chanceSquare;
	}
}
