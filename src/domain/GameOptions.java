package domain;
import org.json.JSONException;
import org.json.JSONObject;


public class GameOptions {
	private static final String[] FIELD_NAMES = {"playerNum"};
	private int playerNum;
	
	public GameOptions(int playerNum) {
		this.playerNum = playerNum;
	}
	
	public static GameOptions fromJSON(JSONObject gameOptionsAsJSON) {
		GameOptions gameOptions;
		int playerNum = 0;

		try {
			playerNum = gameOptionsAsJSON.getInt(FIELD_NAMES[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gameOptions = new GameOptions(playerNum);
		return gameOptions;
	}
	
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
}
