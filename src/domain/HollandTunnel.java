package domain;
import org.json.JSONObject;


public class HollandTunnel extends Square {
	
	private HollandTunnel outerHolland;
	private HollandTunnel innerHollandTunnel;

	public HollandTunnel(String name) {
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
	
	public static HollandTunnel fromJSON(JSONObject squareAsJSON) {
		HollandTunnel hollandTunnel = null;
		
		try {
			String name = squareAsJSON.getString("name");
			hollandTunnel = new HollandTunnel(name);
		} catch (Exception e) {
			
		}
		
		return hollandTunnel;
	}
}
