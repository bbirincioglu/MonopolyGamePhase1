package domain;
import org.json.JSONException;
import org.json.JSONObject;


public class Square {
	private static final String[] FIELD_NAMES = {"name"};
	private String name;
	
	public Square() {
		this.name = null;
	}
	
	public Square(String name) {
		this.name = name;
	}
	
	public static Square fromJSON(JSONObject squareAsJSON) {
		String name = null;
		
		try {
			name = squareAsJSON.getString(FIELD_NAMES[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Square square = new Square(name);
		return square;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
