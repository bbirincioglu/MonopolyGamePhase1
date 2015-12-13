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
		setCardEvaluator(new CardEvaluator());
		int playerNum = 8;
		setPlayers(new ArrayList<Player>());
		
		for (int i = 0; i < playerNum; i++) {
			getPlayers().add(new Player("Player" + i, 2500));
		}
		
		setCurrentPlayerIndex(0);
		
		setChecker(new Checker());
		setCardEvaluator(new CardEvaluator());
		
		setRollButtonClicked(false);
		setGameOver(false);
		new Thread(new GameLoop()).start();
	}
	
	public void doRoll() {
		setRollButtonClicked(true);
	}
	
	public void doApplyMortgage(String squareName) {
		BuyableSquare buyableSquare = (BuyableSquare) getMonopolyBoard().getSquare(squareName);
		System.out.println("doApplyMortgage");
		Checker checker = getChecker();
	}
	
	public void doRemoveMortgage(String squareName) {
		BuyableSquare buyableSquare = (BuyableSquare) getMonopolyBoard().getSquare(squareName);
		System.out.println("doRemoveMortgage");
		Checker checker = getChecker();
	}
	
	public void doBuyBuilding(String squareName) {
		BuyableSquare buyableSquare = (BuyableSquare) getMonopolyBoard().getSquare(squareName);
		System.out.println("doBuyBuilding");
		Checker checker = getChecker();
	}
	
	public void doSellBuilding(String squareName) {
		BuyableSquare buyableSquare = (BuyableSquare) getMonopolyBoard().getSquare(squareName);
		System.out.println("doSellBuilding");
		Checker checker = getChecker();
	}
	
	public void doBuyTrainDepot(String squareName) {
		BuyableSquare buyableSquare = (BuyableSquare) getMonopolyBoard().getSquare(squareName);
		System.out.println("doBuyTrainDepot");
		Checker checker = getChecker();
	}
	
	public void doSellTrainDepot(String squareName) {
		BuyableSquare buyableSquare = (BuyableSquare) getMonopolyBoard().getSquare(squareName);
		System.out.println("doSellTrainDepot");
		Checker checker = getChecker();
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
								System.out.println("mr monopoly 1");
							} else {
								System.out.println("mr monopoly 2");
								currentPlayer.move(getClosestSquareToBuy(currentPlayer));
							}
						} else if (cup.isBusRolled()) {
							int die1Value = cup.getDie1().getFaceValue();
							int die2Value = cup.getDie2().getFaceValue();
							int choice = DialogBuilder.busDialog(die1Value, die2Value);
							System.out.println("choice" +choice);
							currentPlayer.move(choice);
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
	
	public Player getCurrentPlayer() {
		return getPlayers().get(getCurrentPlayerIndex());
	}
	
	public Square getClosestSquareToPayRent(Player currentPlayer) {
		Square currentPlayerLocation = currentPlayer.getCurrentLocation();
		int diceValuesTotal = getCup().getDiceValuesTotal();
		
		if (currentPlayer.getDirection().equals(Piece.Direction.CLOCKWISE)) {
			currentPlayerLocation = currentPlayerLocation.getNext();
		} else {
			currentPlayerLocation = currentPlayerLocation.getPrevious();
		}
		
		while (!(currentPlayerLocation instanceof BuyableSquare) || (((BuyableSquare) currentPlayerLocation).getOwner() == null) || (((BuyableSquare) currentPlayerLocation).getOwner().equals(currentPlayer))) {
			if (diceValuesTotal % 2 == 0) {
				if (currentPlayerLocation instanceof RailRoadSquare) {
					currentPlayerLocation = ((RailRoadSquare) currentPlayerLocation).getUp();
				} else if (currentPlayerLocation instanceof TransitStation) {
					currentPlayerLocation = ((TransitStation) currentPlayerLocation).getDown();
				} else {
					if (currentPlayer.getDirection().equals(Piece.Direction.CLOCKWISE)) {
						currentPlayerLocation = currentPlayerLocation.getNext();
					} else {
						currentPlayerLocation = currentPlayerLocation.getPrevious();
					}
				}
			} else {
				if (currentPlayer.getDirection().equals(Piece.Direction.CLOCKWISE)) {
					currentPlayerLocation = currentPlayerLocation.getNext();
				} else {
					currentPlayerLocation = currentPlayerLocation.getPrevious();
				}
			}
		}
		
		return currentPlayerLocation;
	}
	
	public Square getClosestSquareToBuy(Player currentPlayer) {
		Square currentPlayerLocation = currentPlayer.getCurrentLocation();
		int diceValuesTotal = getCup().getDiceValuesTotal();
		
		if (currentPlayer.getDirection().equals(Piece.Direction.CLOCKWISE)) {
			currentPlayerLocation = currentPlayerLocation.getNext();
		} else {
			currentPlayerLocation = currentPlayerLocation.getPrevious();
		}
		
		while (!(currentPlayerLocation instanceof BuyableSquare) || ((BuyableSquare) currentPlayerLocation).getOwner() != null) {
			if (diceValuesTotal % 2 == 0) {
				if (currentPlayerLocation instanceof TransitStation) {
					currentPlayerLocation = ((TransitStation) currentPlayerLocation).getDown();
				} else if (currentPlayerLocation instanceof RailRoadSquare) {
					currentPlayerLocation = ((RailRoadSquare) currentPlayerLocation).getUp();
				} else {
					if (currentPlayer.getDirection().equals(Piece.Direction.CLOCKWISE)) {
						currentPlayerLocation = currentPlayerLocation.getNext();
					} else {
						currentPlayerLocation = currentPlayerLocation.getPrevious();
					}
				}
			} else {
				if (currentPlayer.getDirection().equals(Piece.Direction.CLOCKWISE)) {
					currentPlayerLocation = currentPlayerLocation.getNext();
				} else {
					currentPlayerLocation = currentPlayerLocation.getPrevious();
				}
			}
		}
		
		return currentPlayerLocation;
	}
}