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
		String result;
		Player squareOwner = square.getOwner();
		GameController gameController = GameController.getInstance();
		Player currentPlayer = gameController.getCurrentPlayer();
		
		if (!currentPlayer.equals(squareOwner)) {
			result = NOT_OWNED_ERROR;
		} else if (square.isMortgaged()) {
			result = MORTGAGED_ERROR;
		} else if (square.getBuildingNum() == 6) {
			result = CANT_BUY_ANYMORE_ERROR;
		} else {
			int buildingNum = square.getBuildingNum();
			boolean isMajorityOwnership = square.isMajorityOwnership();
			boolean isMonopoly = square.isMonopoly();
			
			if (0 <= buildingNum && buildingNum <= 4) {
				if (isMajorityOwnership) {
					if (!square.isMoreDeveloped()) {
						if (buildingNum == 4) {
							if (currentPlayer.getMoney() < square.getHotelCost()) {
								result = NOT_ENOUGH_MONEY_ERROR;
							} else {
								result = RESULT_HOTEL;
							}
 						} else {
							if (currentPlayer.getMoney() < square.getHouseCost()) {
								result = NOT_ENOUGH_MONEY_ERROR;
							} else {
								result = RESULT_HOUSE;
							}
						}
					} else {
						result = "Too much improvement compared to others.";
					}
				} else {
					result = "You don't have majority ownership.";
				}
			} else {
				if (isMonopoly) {
					if (!square.isMoreDeveloped()) {
						if (currentPlayer.getMoney() < square.getSkyscraperCost()) {
							result = NOT_ENOUGH_MONEY_ERROR;
						} else {
							result = RESULT_SKYSCRAPER;
						}
					} else {
						result = "Too much improvoement compared to others.";
					}
				} else {
					result = "You don't have monopoly.";
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
