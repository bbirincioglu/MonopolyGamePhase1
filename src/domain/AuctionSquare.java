package domain;
import org.json.JSONObject;


public class AuctionSquare extends Square {

	public AuctionSquare(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}
	
	public static AuctionSquare fromJSON(JSONObject squareAsJSON) {
		AuctionSquare auctionSquare = null;
		
		try {
			String name = squareAsJSON.getString("name");
			auctionSquare = new AuctionSquare(name);
		} catch (Exception e) {
			
		}
		
		return auctionSquare;
	}
}
