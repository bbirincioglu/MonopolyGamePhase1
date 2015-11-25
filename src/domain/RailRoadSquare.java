package domain;
import java.util.ArrayList;
import org.json.JSONObject;


public class RailRoadSquare extends BuyableSquare {

	private static RailRoadDescription description;
	private TransitStation up;
	private boolean isTrainDepotBuilt;

	
	public RailRoadSquare(String name, int price) {
		super(name, price);
		isTrainDepotBuilt=false;
		// TODO Auto-generated constructor stub
	}

	public static RailRoadDescription getDescription() {
		return description;
	}

	public static void setDescription(RailRoadDescription description) {
		RailRoadSquare.description = description;
	}

	public TransitStation getUp() {
		return up;
	}

	public void setUp(TransitStation up) {
		this.up = up;
	}
	
	public boolean isTrainDepotBuilt() {
		return isTrainDepotBuilt;
	}

	public void setTrainDepotBuilt(boolean isTrainDepotBuilt) {
		this.isTrainDepotBuilt = isTrainDepotBuilt;
	}

	@Override
	public int getCurrentRent() {
		int currentRent;// TODO Auto-generated method stub
		int railRoadNum=0;
		ArrayList<BuyableSquare> squares=this.getOwner().getSquares();
		for(int i=0; i<squares.size(); i++){
			BuyableSquare square=squares.get(i);
			if(square instanceof RailRoadSquare){
				railRoadNum++;
			}
		}
		currentRent = description.getRents().get(railRoadNum-1);
		if(isTrainDepotBuilt())
			currentRent = currentRent *2;
		
		return currentRent;
	}
	
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		int dieValue1=GameConroller.getInstance().getDie1.getFaceValue();
		int dieValue2=GameConroller.getInstance().getDie2.getFaceValue();
		int dieValue3=GameConroller.getInstance().getSpeedDie.getFaceValue();
		int faceValue;
		if(faceValue%2==0){
			piece.move(up);
		}	
	}
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		int dieValue1=GameConroller.getInstance().getDie1.getFaceValue();
		int dieValue2=GameConroller.getInstance().getDie2.getFaceValue();
		int dieValue3=GameConroller.getInstance().getSpeedDie.getFaceValue();
		int faceValue;
		if(faceValue%2==0){
			piece.move(up);
		}
		
	}
	
	public static RailRoadSquare fromJSON(JSONObject squareAsJSON) {
		RailRoadSquare railRoadSquare = null;
		
		try {
			String name = squareAsJSON.getString("name");
			int price = squareAsJSON.getInt("price");
			railRoadSquare = new RailRoadSquare(name, price);
		} catch (Exception e) {
			
		}
		
		return railRoadSquare;
	}
}
