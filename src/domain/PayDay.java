package domain;
import org.json.JSONObject;


public class PayDay extends Square {

	public PayDay(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		//gameController'da refactor et.
		int dieValue1=GameConroller.getInstance().getDie1.getFaceValue();
		int dieValue2=GameConroller.getInstance().getDie2.getFaceValue();
		int dieValue3=GameConroller.getInstance().getSpeedDie.getFaceValue();
		int faceValue;
		if(dieValue3!=4 && dieValue3!=5 && dieValue3!=6 ){
		 faceValue=dieValue1+dieValue2+dieValue3;
	}
		else {
			faceValue=dieValue1+dieValue2;
		}
		if(faceValue==0){
			piece.getOwner().receivePayment(400);
		}
		else if((faceValue%2)==1){
			piece.getOwner().receivePayment(300);

		}
		else{
			piece.getOwner().receivePayment(400);
		}
			
	}
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
		int faceValue;
		if(GameConroller.getInstance().getSpeedDie.getFaceValue()!=4 && GameConroller.getInstance().getSpeedDie.getFaceValue()!=5 && GameConroller.getInstance().getSpeedDie.getFaceValue()!=6 ){
		 faceValue=GameConroller.getInstance().getDie1.getFaceValue()+GameConroller.getInstance().getDie2.getFaceValue()+GameConroller.getInstance().getSpeedDie.getFaceValue();
	}
		else {
			faceValue=GameConroller.getInstance().getDie1.getFaceValue()+GameConroller.getInstance().getDie2.getFaceValue();
		}
		
		if((faceValue%2)==1){
			piece.getOwner().receivePayment(300);

		}
		else{
			piece.getOwner().receivePayment(400);
		}
			
	}
	
	public static PayDay fromJSON(JSONObject squareAsJSON) {
		PayDay payDay = null;
		
		try {
			String name = squareAsJSON.getString("name");
			payDay = new PayDay(name);
		} catch (Exception e) {
			
		}
		
		return payDay;
	}
}
