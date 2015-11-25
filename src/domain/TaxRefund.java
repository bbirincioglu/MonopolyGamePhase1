package domain;
import org.json.JSONObject;


public class TaxRefund extends Square{

	public TaxRefund(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		GameController.getInstance().getMonopolyBoard().getPoolMoney(piece.getOwner());
	}

	@Override
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}
	
	public static TaxRefund fromJSON(JSONObject squareAsJSON) {
		TaxRefund taxRefund = null;
		
		try {
			String name = squareAsJSON.getString("name");
			taxRefund = new TaxRefund(name);
		} catch (Exception e) {
			
		}
		
		return taxRefund;
	}
}
