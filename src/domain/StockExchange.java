package domain;
import org.json.JSONObject;


public class StockExchange extends Square {

	public StockExchange(String name) {
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
	
	public static StockExchange fromJSON(JSONObject squareAsJSON) {
		StockExchange stockExchange = null;
		
		try {
			String name = squareAsJSON.getString("name");
			stockExchange = new StockExchange(name);
		} catch (Exception e) {
			
		}
		
		return stockExchange;
	}
}
