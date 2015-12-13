package domain;
import java.util.ArrayList;

import org.json.JSONObject;


public class AuctionSquare extends Square {

	public AuctionSquare(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landedOn(Piece piece) {
		// TODO Auto-generated method stub
		GameController controller = GameController.getInstance();
		Bank bank = controller.getMonopolyBoard().getBank();
		
		if (bank.isUnownedBuyableSquareLeft()) {
			String squareName = DialogBuilder.pickAnUnownedSquareDialog(bank);
			BuyableSquare buyableSquare = bank.getBuyableSquare(squareName);
			int[] bids = DialogBuilder.auctionDialog(controller.getPlayers(), buyableSquare);
			int maximumBid = bids[0];
			int maximumBidIndex = 0;
			
			for (int i = 0; i < bids.length; i++) {
				if (bids[i] > maximumBid) {
					maximumBid = bids[i];
					maximumBidIndex = i;
				}
			}
			
			if (maximumBid >= buyableSquare.getPrice() / 2) {
				controller.getPlayers().get(maximumBidIndex).buySquare(bank, buyableSquare, maximumBid);
			}		
		} else {
			Player currentPlayer = piece.getOwner();
			BuyableSquare squareWithHighestRent = findSquareWithHighestRent(currentPlayer, controller.getMonopolyBoard());
			currentPlayer.move(squareWithHighestRent);
		}
	}
	
	private BuyableSquare findSquareWithHighestRent(Player currentPlayer, MonopolyBoard monopolyBoard) {
		BuyableSquare squareWithHighestRent = null;
		Square currentLocation = currentPlayer.getCurrentLocation();
		ArrayList<Square> innerSquares = monopolyBoard.getInnerSquares();
		ArrayList<Square> middleSquares = monopolyBoard.getMiddleSquares();
		int totalDiceValue = GameController.getInstance().getCup().getDiceValuesTotal();
		BuyableSquare innerSquareHighestRent;
		BuyableSquare middleSquareHighestRent;
		
		if (totalDiceValue % 2 == 0) {
			if (currentPlayer.getDirection().equals(Piece.Direction.CLOCKWISE)) {
				innerSquareHighestRent = highestRentHelper(innerSquares, 13, 21, currentPlayer);
			} else {
				innerSquareHighestRent = highestRentHelper(innerSquares, 10, 12, currentPlayer);
			}
			
			middleSquareHighestRent = highestRentHelper(middleSquares, 0, middleSquares.size(), currentPlayer);
			
			if (innerSquareHighestRent == null || middleSquareHighestRent == null) {
				if (innerSquareHighestRent == null) {
					squareWithHighestRent = middleSquareHighestRent;
				} else {
					squareWithHighestRent = innerSquareHighestRent;
				}
			} else {
				if (innerSquareHighestRent.getCurrentRent() >= middleSquareHighestRent.getCurrentRent()) {
					squareWithHighestRent = innerSquareHighestRent;
				} else {
					squareWithHighestRent = middleSquareHighestRent;
				}
			}		
		} else {
			squareWithHighestRent = highestRentHelper(innerSquares, 0, innerSquares.size(), currentPlayer);
		}
		
		return squareWithHighestRent;
	}
	
	private BuyableSquare highestRentHelper(ArrayList<Square> squares, int start, int end, Player currentPlayer) {
		BuyableSquare squareWithHighestRent = null;
		
		for (int i = start; i < end; i++) {
			Square square = squares.get(i);
			
			if (square instanceof BuyableSquare) {
				BuyableSquare buyableSquare = (BuyableSquare) square;
				
				if (squareWithHighestRent == null && (buyableSquare.getOwner() != null) && !(buyableSquare.getOwner().equals(currentPlayer))) {
					squareWithHighestRent = buyableSquare;
				} else {
					if (buyableSquare.getCurrentRent() > squareWithHighestRent.getCurrentRent() && (buyableSquare.getOwner() != null) && !(buyableSquare.getOwner().equals(currentPlayer))) {
						squareWithHighestRent = buyableSquare;
					}
				}
			}
		}
		
		return squareWithHighestRent;
	}

	@Override
	public void passedOn(Piece piece) {
		// TODO Auto-generated method stub
		
	}
	
	public static AuctionSquare fromJSON(JSONObject squareAsJSON) {
		AuctionSquare auctionSquare = null;
		
		try {
			String name = squareAsJSON.getString("name");
			auctionSquare = new AuctionSquare(name);
		} catch (Exception e) {
			
		}
		
		return auctionSquare;
	}
}
