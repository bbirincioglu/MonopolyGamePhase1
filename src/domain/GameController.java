package domain;
import java.util.ArrayList;

public class GameController {
	private static GameController instance;
	
	private Die die1;
	private Die die2;
	private Die speedDie;
	
	private ArrayList<Player> players;
	private int currentPlayerIndex;
	
	private MonopolyBoard monopolyBoard;
	
	private Checker checker;
	//private CardEvaluator cardEvaluator;
	
	private static boolean isDiceRolled;
	private static boolean isGameOver;
	
	private GameController() {
		setDie1(new Die());
		setDie2(new Die());
		setSpeedDie(new Die());
		
		setMonopolyBoard(new MonopolyBoard());
		
		int playerNum = 8;
		setPlayers(new ArrayList<Player>());
		
		for (int i = 0; i < playerNum; i++) {
			getPlayers().add(new Player("Player" + i, 2500));
		}
		
		setCurrentPlayerIndex(0);
		//setChecker(new Checker());
		//setCardEvaluator(new CardEvaluator());
		
		setDiceRolled(false);
		setGameOver(false);
		new Thread(new GameLoop()).start();
	}
	
	public void doRoll() {
		Die die1 = getDie1();
		Die die2 = getDie2();
		Die speedDie = getSpeedDie();
		
		die1.roll();
		die2.roll();
		speedDie.roll();
		new Thread(new DieAnimationTask(3)).start();
	}
	
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		
		return instance;
	}
	
	public class DieAnimationTask implements Runnable {
		private int dieNum;
		
		public DieAnimationTask(int dieNum) {
			this.dieNum = dieNum;
		}
		
		public void run() {
			if (getDieNum() == 2) {
				Die.animate(getDie1(), getDie2());
			} else {
				Die.animate(getDie1(), getDie2(), getSpeedDie());
			}
			
			setDiceRolled(true);
		}
		
		public void setDieNum(int dieNum) {
			this.dieNum = dieNum;
		}
		
		public int getDieNum() {
			return dieNum;
		}
	}
	
	public class GameLoop implements Runnable {
		public GameLoop() {
		}
		
		public void run() {
			while (!isGameOver()) {
				if (isDiceRolled()) {
					Player currentPlayer = getPlayers().get(getCurrentPlayerIndex());
					
					Die die1 = getDie1();
					Die die2 = getDie2();
					Die speedDie = getSpeedDie();
					
					int die1Value = die1.getFaceValue();
					int die2Value = die2.getFaceValue();
					int speedDieValue = speedDie.getFaceValue();
					
					if (speedDieValue <= 3) {
						int diceValuesTotal = die1Value + die2Value + speedDieValue;
						currentPlayer.move(diceValuesTotal);
					} else {
						int diceValuesTotal = die1Value + die2Value;
						currentPlayer.move(diceValuesTotal);
					}
					
					updateCurrentPlayerIndex();
					setDiceRolled(false);
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

	public Die getDie1() {
		return die1;
	}

	public void setDie1(Die die1) {
		this.die1 = die1;
	}

	public Die getDie2() {
		return die2;
	}

	public void setDie2(Die die2) {
		this.die2 = die2;
	}

	public Die getSpeedDie() {
		return speedDie;
	}

	public void setSpeedDie(Die speedDie) {
		this.speedDie = speedDie;
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

	public static boolean isDiceRolled() {
		return isDiceRolled;
	}

	public static void setDiceRolled(boolean isDiceRolled) {
		GameController.isDiceRolled = isDiceRolled;
	}

	public static boolean isGameOver() {
		return isGameOver;
	}

	public static void setGameOver(boolean isGameOver) {
		GameController.isGameOver = isGameOver;
	}
}