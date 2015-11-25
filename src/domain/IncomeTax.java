package domain;
import org.json.JSONObject;


public class IncomeTax extends Square{

	public IncomeTax(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		Bank bank=GameController.getInstance().getMonopolyBoard().getBank();
		Player player=piece.getOwner();
		if(player.getWealth()>900){
		player.makePayment(bank,900);
		}
		else{
			player.makePayment(bank, player.getWealth());
		}
		
	}

	@Override
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}
	
	public static IncomeTax fromJSON(JSONObject squareAsJSON) {
		IncomeTax incomeTax = null;
		
		try {
			String name = squareAsJSON.getString("name");
			incomeTax = new IncomeTax(name);
		} catch (Exception e) {
			
		}
		
		return incomeTax;
	}
}
