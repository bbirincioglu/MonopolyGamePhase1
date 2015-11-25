package domain;
import org.json.JSONObject;


public class CommunitySquare extends Square{

	public CommunitySquare(String name) {
		super(name);
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
	String s=piece.getOwner().selectCommunityCard(GameController.getInstance().getMonopolyBoard())

	}

	@Override
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}
	
	public static CommunitySquare fromJSON(JSONObject squareAsJSON) {
		CommunitySquare communitySquare = null;
		
		try {
			String name = squareAsJSON.getString("name");
			communitySquare = new CommunitySquare(name);
		} catch (Exception e) {
			
		}
		
		return communitySquare;
	}
}
