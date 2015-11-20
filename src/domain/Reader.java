package domain;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;


public class Reader {
	public Reader() {
		
	}
	
	public static ArrayList<Square> readSQ(String fileName) {
		ArrayList<Square> squares = new ArrayList<Square>();
		
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			
			while (line != null) {
				squares.add(Square.fromJSON(new JSONObject(line)));
				line = reader.readLine();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return squares;
	}
	
	public static GameOptions readGO(String fileName) {
		GameOptions gameOptions = null;
		
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			gameOptions = GameOptions.fromJSON(new JSONObject(line));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gameOptions;
	}
}
