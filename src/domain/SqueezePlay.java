package domain;
import org.json.JSONObject;


public class SqueezePlay extends Square {

	public SqueezePlay(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		GamController.getInstance().doRoll();
		int dieValue1=GameConroller.getInstance().getDie1.getFaceValue();
		int dieValue2=GameConroller.getInstance().getDie2.getFaceValue();
		int faceValue=dieValue1+dieValue2;
		ArrayList<Player> players=GameController.getInstance().getPlayers();
		if(faceValue==5 || faceValue==6 || faceValue==7 || faceValue==8 || faceValue==9){
			for(Player player : players){
				player.makePayment(piece.getOwner(), 50);
			}
		}
		else if(faceValue==3 || faceValue==4 || faceValue==10 || faceValue==11){
			for(Player player : players){
				player.makePayment(piece.getOwner(), 100);
			}
		}
		else{
			for(Player player : players){
				player.makePayment(piece.getOwner(), 200);
			}
		}
		
	}

	@Override
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}
	
	public static SqueezePlay fromJSON(JSONObject squareAsJSON) {
		SqueezePlay squeezePlay = null;
		
		try {
			String name = squareAsJSON.getString("name");
			squeezePlay = new SqueezePlay(name);
		} catch (Exception e) {
			
		}
		
		return squeezePlay;
	}
}
