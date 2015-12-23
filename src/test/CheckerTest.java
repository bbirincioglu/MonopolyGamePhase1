package test;

import domain.Bank;
import domain.Checker;
import domain.ColorSquare;
import domain.GameController;
import domain.MonopolyBoard;
import domain.Player;

public class CheckerTest {
	public static void main(String[] args) {
		GameController gameController = GameController.getInstance();
		Checker checker = gameController.getChecker();
		MonopolyBoard monopolyBoard = gameController.getMonopolyBoard();
		Bank bank = monopolyBoard.getBank();
		Player currentPlayer = gameController.getCurrentPlayer();
		ColorSquare oriental = (ColorSquare) bank.getBuyableSquare("ORIENTAL AVENUE");
		ColorSquare vermont = (ColorSquare) bank.getBuyableSquare("VERMONT AVENUE");
		
		currentPlayer.buySquare(bank, oriental, 0);
		currentPlayer.buySquare(bank, vermont, 0);
		System.out.println(currentPlayer.getMoney());
		
		for (int i = 0; i < 6; i++) {
			String result = checker.checkBuyBuilding(oriental);
			
			if (result.equals("house")) {
				currentPlayer.buyHouse(oriental);
			} else if (result.equals("hotel")) {
				currentPlayer.buyHotel(oriental);
			} else if (result.equals("skyscraper")) {
				currentPlayer.buySkyscraper(oriental);
			}
			
			System.out.println(result);
			System.out.println(oriental.getBuildingNum());
			System.out.println(currentPlayer.getMoney());
		}
	}
}
