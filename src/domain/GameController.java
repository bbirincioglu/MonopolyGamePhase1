package domain;
import java.util.ArrayList;

public class GameController {
	private ArrayList<ControllerObserver> observers;
	private static GameController instance;
	
	private Cup cup;
	
	private ArrayList<Player> players;
	private int currentPlayerIndex;
	
	private MonopolyBoard monopolyBoard;
	
	private Checker checker;
	private CardEvaluator cardEvaluator;
	
	private static boolean isRollButtonClicked;
	private static boolean isGameOver;
	
	private GameController() {
		setObservers(new ArrayList<ControllerObserver>());
		setCup(new Cup());
		
		setMonopolyBoard(new MonopolyBoard());
		//setChecker(new Checker();
		setCardEvaluator(new CardEvaluator());
		
		int playerNum = 8;
		setPlayers(new ArrayList<Player>());
		
		for (int i = 0; i < playerNum; i++) {
			getPlayers().add(new Player("Player" + i, 2500));
		}
		
		setCurrentPlayerIndex(0);
		//setChecker(new Checker());
		setCardEvaluator(new CardEvaluator());
		
		setRollButtonClicked(false);
		setGameOver(false);
		new Thread(new GameLoop()).start();
	}
	
	public void doRoll() {
		setRollButtonClicked(true);
	}
	
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		
		return instance;
	}
	
	public class GameLoop implements Runnable {
		public GameLoop() {
		}
		
		public void run() {
			while (!isGameOver()) {
				if (isRollButtonClicked()) {
					Player currentPlayer = getPlayers().get(getCurrentPlayerIndex());
					
					if (currentPlayer.isInJail()) {
						if (currentPlayer.getRoundNumInJail() == 3) {
							if (currentPlayer.getMoney() >= 50) {
								currentPlayer.makePayment(getMonopolyBoard().getBank(), 50);
								DialogBuilder.informativeDialog(currentPlayer.getName() + " is forced to pay $50 to the bank to get out of jail.");
								// no continuity.
							} else {
								if (currentPlayer.getWealth() < 50) {
									// check or mortgage.
								} else {
									DialogBuilder.forcePlayerToSellPropertyToGetOutOfJailDialog(currentPlayer);
								}
							}
						} else {
							// would you like to pay 50 dollar or roll 2 dice.
						}
					} else {
						Cup cup = getCup();
						cup.roll3Dice();
						int diceValuesTotal = cup.getDiceValuesTotal();
						
						if (cup.isMrMonopolyRolled()) {
							currentPlayer.move(diceValuesTotal);
							Bank bank = getMonopolyBoard().getBank();
							int unownedSquareSize = bank.getBuyableSquares().size();
							if (unownedSquareSize == 0) {
								currentPlayer.move(getClosestSquareToPayRent(currentPlayer));
							} else {
								currentPlayer.move(getClosestSquareToBuy());
							}
						} else if (cup.isBusRolled()) {
							System.out.println("in the bus");
						} else {
							currentPlayer.move(diceValuesTotal);
						}
					}
					
					updateCurrentPlayerIndex();
					setRollButtonClicked(false);
				} else {
					try {
						//System.out.println("in the if");
						Thread.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void updateCurrentPlayerIndex() {
		setCurrentPlayerIndex((getCurrentPlayerIndex() + 1) % getPlayers().size());
		notifyObservers();
	}
	
	/*public void doBuyBuilding(String squareName){

		Player p = players.get(currentPlayerIndex);

		Square s = monopolyBoard.getSquare(squareName);

		String whatCanBeBuild = checker.checkBuyBuilding((BuyableSquare) s);

		switch (whatCanBeBuild) {

		case "house":

			p.buyHouse((ColorSquare) s);

			((ColorSquare) s).updateCurrentRent();

			break;

		case "hotel":

			p.buyHotel((ColorSquare) s);

			((ColorSquare) s).updateCurrentRent();

			break;

		case "skyscraper":

			p.buySkyScraper((ColorSquare) s);

			((ColorSquare) s).updateCurrentRent();

			break;



		default:

			dialogBuilder.buildInformativeDialog(whatCanBeBuild);

			break;

		}

	}
	
	public void doSellBuilding(String squareName){

		Player p = players.get(currentPlayerIndex);

		Square s = monopolyBoard.getSquare(squareName);

		String whatCanBeSold = checker.checkSellBuilding((BuyableSquare) s);

		switch (whatCanBeSold) {

		case "house":

			p.sellHouse((ColorSquare) s);

			((ColorSquare) s).updateCurrentRent();

			break;

		case "hotel":

			p.sellHotel((ColorSquare) s);

			((ColorSquare) s).updateCurrentRent();

			break;

		case "skyscraper":

			p.sellSkyScraper((ColorSquare) s);

			((ColorSquare) s).updateCurrentRent();

			break;

		default:

			dialogBuilder.buildInformativeDialog(whatCanBeSold);

			break;

		}

	}

	public boolean isGameOverFor(Player currentPlayer){

		int money = currentPlayer.getMoney();

		

		if(money<0) return true;

		

		int debt = 0;

		int mortgageDebt = 0;

		int debtToAnotherPlayer = 0;

		ArrayList<Square> squares = currentPlayer.getSquares();

		

		for (int i = 0; i < squares.size(); i++) {

			Square s = squares.get(i);

			if(s.isMortgaged()){

				int mortgageValue = ((ColorSquare) s).getMortgageValue();

				mortgageDebt += mortgageValue+mortgageValue/10;

			}

		}

		

		if(currentPlayer.getPiece().getCurrentLocation().getOwner()!=null)

			debtToAnotherPlayer += ((BuyableSuare) currentPlayer.getPiece().getCurrentLocation()).getCurrentRent();

		

		debt = mortgageDebt+debtToAnotherPlayer;

		return money<debt;

		

	}

	public void doApplyMortgageTo(String squareName){

		Square s = monopolyBoard.getSquare(squareName);

		Player p = players.get(currentPlayerIndex);

		boolean canBeMortgaged = checker.checkMortgage(s);

		

		if(canBeMortgaged) p.applyMortgageTo(s);

		else dialogBuilder.buildInformativeDialog("Cannot mortgate");

	}

	

	public void doRemoveMortgageFrom(String squareName){

		Square s = monopolyBoard.getSquare(squareName);

		Player p = players.get(currentPlayerIndex);

		

		boolean canBeMortgaged = checker.checkMortgage(s);

		

		if(!canBeMortgaged) p.removeMortgageFrom(s);

		else dialogBuilder.buildInformativeDialog("Cannot mortgate");

	}*/
	
	public void setCup(Cup cup) {
		this.cup = cup;
	}
	
	public Cup getCup() {
		return cup;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}

	public MonopolyBoard getMonopolyBoard() {
		return monopolyBoard;
	}

	public void setMonopolyBoard(MonopolyBoard monopolyBoard) {
		this.monopolyBoard = monopolyBoard;
	}

	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}
	
	public static void setInstance(GameController instance) {
		GameController.instance = instance;
	}
	
	public static boolean isRollButtonClicked() {
		return isRollButtonClicked;
	}

	public static void setRollButtonClicked(boolean isRollButtonClicked) {
		GameController.isRollButtonClicked = isRollButtonClicked;
	}

	public static boolean isGameOver() {
		return isGameOver;
	}

	public static void setGameOver(boolean isGameOver) {
		GameController.isGameOver = isGameOver;
	}

	public ArrayList<ControllerObserver> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<ControllerObserver> observers) {
		this.observers = observers;
	}
	
	public void addObserver(ControllerObserver observer) {
		getObservers().add(observer);
	}
	
	public void notifyObservers() {
		for (int i = 0; i < getObservers().size(); i++) {
			getObservers().get(i).update(this);
		}
	}

	public CardEvaluator getCardEvaluator() {
		return cardEvaluator;
	}

	public void setCardEvaluator(CardEvaluator cardEvaluator) {
		this.cardEvaluator = cardEvaluator;
	}
	
	public Square getClosestSquareToPayRent(Player currentPlayer) {
		Square currentPlayerLocation = currentPlayer.getCurrentLocation();
		int diceValuesTotal = getCup().getDiceValuesTotal();
		
		if (diceValuesTotal % 2 == 0) {
			if (currentPlayerLocation instanceof RailRoadSquare) {
				currentPlayerLocation = ((RailRoadSquare) currentPlayerLocation).getUp();
			} else if (currentPlayerLocation instanceof TransitStation) {
				currentPlayerLocation = ((TransitStation) currentPlayerLocation).getDown();
			}
		} else {
			currentPlayerLocation = currentPlayerLocation.getNext();
		}
		
		while (!(currentPlayerLocation instanceof BuyableSquare) || (((BuyableSquare) currentPlayerLocation).getOwner() == null) || (((BuyableSquare) currentPlayerLocation).getOwner().equals(currentPlayer))) {
			if (diceValuesTotal % 2 == 0) {
				if (currentPlayerLocation instanceof RailRoadSquare) {
					currentPlayerLocation = ((RailRoadSquare) currentPlayerLocation).getUp();
				} else if (currentPlayerLocation instanceof TransitStation) {
					currentPlayerLocation = ((TransitStation) currentPlayerLocation).getDown();
				}
			} else {
				currentPlayerLocation = currentPlayerLocation.getNext();
			}
		}
		
		return currentPlayerLocation;
	}
	
	public Square getClosestSquareToBuy(Player currentPlayer) {
		Square currentPlayerLocation = currentPlayer.getCurrentLocation();
		int diceValuesTotal = getCup().getDiceValuesTotal();
		
		if (diceValuesTotal % 2 == 0) {
			if (currentPlayerLocation instanceof RailRoadSquare) {
				currentPlayerLocation = ((RailRoadSquare) currentPlayerLocation).getUp();
			} else if (currentPlayerLocation instanceof TransitStation) {
				currentPlayerLocation = ((TransitStation) currentPlayerLocation).getDown();
			}
		} else {
			currentPlayerLocation = currentPlayerLocation.getNext();
		}
		
		while (!(currentPlayerLocation instanceof BuyableSquare) || (((BuyableSquare) currentPlayerLocation).getOwner() != null)) {
			if (diceValuesTotal % 2 == 0) {
				if (currentPlayerLocation instanceof RailRoadSquare) {
					currentPlayerLocation = ((RailRoadSquare) currentPlayerLocation).getUp();
				} else if (currentPlayerLocation instanceof TransitStation) {
					currentPlayerLocation = ((TransitStation) currentPlayerLocation).getDown();
				}
			} else {
				currentPlayerLocation = currentPlayerLocation.getNext();
			}
		}
		
		return currentPlayerLocation;
	}
}