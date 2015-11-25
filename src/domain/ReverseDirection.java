package domain;
import org.json.JSONObject;


public class ReverseDirection extends Square {

	public ReverseDirection(String name) {
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
	
	public static ReverseDirection fromJSON(JSONObject squareAsJSON) {
		ReverseDirection reverseDirection = null;
		
		try {
			String name = squareAsJSON.getString("name");
			reverseDirection = new ReverseDirection(name);
		} catch (Exception e) {
			
		}
		
		return reverseDirection;
	}
}
