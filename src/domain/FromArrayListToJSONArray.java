package domain;

import java.util.ArrayList;

import org.json.JSONArray;

public class FromArrayListToJSONArray {
	public JSONArray convert(ArrayList elements) {
		JSONArray elementsJSONArray = new JSONArray();
		
		for (Object element : elements) {
			if (element instanceof Square) {
				Square elementAsSquare = (Square) element;
				elementsJSONArray.put(elementAsSquare.toJSON());
			} else if (element instanceof Card) {
				Card elementAsCard = (Card) element;
				elementsJSONArray.put(elementAsCard.toJSON());
			}
		}
		
		return elementsJSONArray;
	}
}
