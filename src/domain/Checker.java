package domain;

public class Checker {
	private static final String NOT_OWNED_ERROR = "The square is not yours. You can't build anything.";
	private static final String MORTGAGED_ERROR = "The square is mortgaged. You can't build anything.";
	private static final String CANT_BUY_ANYMORE_ERROR = "The square already has a skyscraper. You can't buy anymore.";
	private static final String CANT_SELL_ANYMORE_ERROR = "The square already has 0 building. You can't sell anymore.";
	private static final String NOT_ENOUGH_MONEY_ERROR = "You don't have enough money.";
	
	private static final String RESULT_HOUSE = "house";
	private static final String RESULT_HOTEL = "hotel";
	private static final String RESULT_SKYSCRAPER = "skyscraper";
	
	public Checker() {		
	}
	
	public String checkBuyBuilding(ColorSquare square) {
		String result = null;
		Player squareOwner = square.getOwner();
		GameController gameController = GameController.getInstance();
		Player currentPlayer = gameController.getPlayers().get(gameController.getCurrentPlayerIndex());
	
		if (!currentPlayer.equals(squareOwner)) {
			result = NOT_OWNED_ERROR;
		} else if (square.isMortgaged()) {
			result = MORTGAGED_ERROR;
		} else if (square.getBuildingNum() == 6) {
			result = CANT_BUY_ANYMORE_ERROR;
		} else {
			int money = squareOwner.getMoney();
			int buildingNum = square.getBuildingNum();
			
			int houseCost = square.getHouseCost();
			int hotelCost = square.getHotelCost();
			int skyscraperCost = square.getSkyscraperCost();
			boolean isMajorityOwnership =  square.isMajorityOwnership();
			boolean isMonopoly = square.isMonopoly();
			boolean isTooMuchImprovementComparedToOthers = square.isTooMuchImprovementComparedToOthers();
			
			if (0 <= buildingNum && buildingNum < 4) {
				if (isMajorityOwnership || isMonopoly) {
					if (isTooMuchImprovementComparedToOthers) {
						// ERROR TOO MUCH IMPROVEMENTS.
					} else {
						if (money < houseCost) {
							result = NOT_ENOUGH_MONEY_ERROR;
						} else {
							result = RESULT_HOUSE;
						}
					}
				} else {
					//ERROR NO MAJORITY NO MONOPOLY
				}
			} else if (buildingNum == 4) {
				if (isMajorityOwnership || isMonopoly) {
					if (isTooMuchImprovementComparedToOthers) {
						// too mcuh imporomvemtn
					} else {
						if (money < hotelCost) {
							result = NOT_ENOUGH_MONEY_ERROR;
						} else {
							result = RESULT_HOTEL;
						}
					}
				} else {
					// ERROR NO MAJORITY OR NMONOPOYL.
				}
			} else if (buildingNum == 5) {
				if (isMonopoly) {
					if (isTooMuchImprovementComparedToOthers) {
						// too mcuh importvment.
					} else {
						if (money < skyscraperCost) {
							result = NOT_ENOUGH_MONEY_ERROR;
						} else {
							result = RESULT_SKYSCRAPER;
						}
					}
				} else {
					// MONOPOLY EROR.
				}
			}
		}
		
		return result;
	}
	
	public String checkSellBuilding(ColorSquare square) {
		String result = null;
		Player squareOwner = square.getOwner();
		GameController gameController = GameController.getInstance();
		Player currentPlayer = gameController.getCurrentPlayer();
		
		if (!currentPlayer.equals(squareOwner)) {
			result = NOT_OWNED_ERROR;
		} else if (square.isMortgaged()) {
			result = MORTGAGED_ERROR;
		} else if (square.getBuildingNum() == 0) {
			result = CANT_SELL_ANYMORE_ERROR;
		} else {
			int buildingNum = square.getBuildingNum();
			
			if (buildingNum <= 4) {
				result = RESULT_HOUSE;
			} else if (buildingNum == 5) {
				result = RESULT_HOTEL;
			} else if (buildingNum == 6) {
				result = RESULT_SKYSCRAPER;
			}
		}
		
		return result;
	}
}
