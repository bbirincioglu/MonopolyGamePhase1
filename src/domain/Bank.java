package domain;

import java.util.ArrayList;

public class Bank {
	private ArrayList<BuyableSquare> buyableSquares;
	
	public Bank(ArrayList<Square> outerSquares, ArrayList<Square> middleSquares, ArrayList<Square> innerSquares) {
		setBuyableSquares(new ArrayList<BuyableSquare>());
		pickBuyableSquares(outerSquares, middleSquares, innerSquares);
	}

	private void pickBuyableSquares(ArrayList<Square> outerSquares,ArrayList<Square> middleSquares, ArrayList<Square> innerSquares) {
		ArrayList<BuyableSquare> buyableSquares = getBuyableSquares();
		Square square;
		
		for (int i = 0; i < outerSquares.size(); i++) {
			square = outerSquares.get(i);
			
			if (square instanceof BuyableSquare) {
				buyableSquares.add((BuyableSquare) square);
			}
		}
		
		for (int i = 0; i < middleSquares.size(); i++) {
			square = middleSquares.get(i);
			
			if (square instanceof BuyableSquare) {
				buyableSquares.add((BuyableSquare) square);
			}
		}
		
		for (int i = 0; i < innerSquares.size(); i++) {
			square = innerSquares.get(i);
			
			if (square instanceof BuyableSquare) {
				buyableSquares.add((BuyableSquare) square);
			}
		}
	}
	
	public BuyableSquare getBuyableSquare(String name) {
		BuyableSquare buyableSquare = null;
		ArrayList<BuyableSquare> buyableSquares = getBuyableSquares();
		int size = buyableSquares.size();
		
		for (int i = 0; i < size; i++) {
			BuyableSquare square = buyableSquares.get(i);
			
			if (square.getName().equals(name)) {
				buyableSquare = square;
				break;
			}
		}
		
		return buyableSquare;
	}
	
	public void addBuyableSquare(BuyableSquare square) {
		getBuyableSquares().add(square);
	}
	
	public void removeBuyableSquare(BuyableSquare square) {
		getBuyableSquares().remove(square);
	}
	
	public ArrayList<BuyableSquare> getBuyableSquares() {
		return buyableSquares;
	}

	public void setBuyableSquares(ArrayList<BuyableSquare> buyableSquares) {
		this.buyableSquares = buyableSquares;
	}
}
