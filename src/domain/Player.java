package domain;
import java.util.ArrayList;
/**
 * 
 * This class contains the necessary information about the player, stores his money, properties and state.
 *
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Player {
<<<<<<< Updated upstream
=======
	private static final String[] FIELD_NAMES = {"name", "money", "squares", "stocks", "cards", "piece", "isInJail", "isBargainSelected", "roundNumInJail"};


//@overview: Player class contains the necessary information about the player, stores his money, properties and state.
	
>>>>>>> Stashed changes
	private ArrayList<PlayerObserver> playerObservers;
	private String name;
	private int money;
	private ArrayList<BuyableSquare> squares;
	private ArrayList<Stock> stocks;
	private ArrayList<Card> cards;
	private Piece piece;
	private boolean isInJail;
	private boolean isBargainSelected;
	private int roundNumInJail;
public JSONObject toJSON(){
	JSONObject playerAsJSON=new JSONObject();
	try {
		playerAsJSON.put(FIELD_NAMES[0], getName());
		playerAsJSON.put(FIELD_NAMES[1], getMoney());
		ArrayList<BuyableSquare> squareArray=getSquares();
		JSONArray jsonArraySquare=new JSONArray(squareArray);
		playerAsJSON.put(FIELD_NAMES[2], jsonArraySquare);
		JSONArray jsonArrayStock=new JSONArray(getStocks());
		playerAsJSON.put(FIELD_NAMES[3], jsonArrayStock);
		JSONArray jsonArrayCards=new JSONArray(getCards());
		playerAsJSON.put(FIELD_NAMES[4], jsonArrayCards);
		playerAsJSON.put(FIELD_NAMES[5], getPiece().toJSON());
		playerAsJSON.put(FIELD_NAMES[6], isInJail());
		playerAsJSON.put(FIELD_NAMES[7], isBargainSelected());
		playerAsJSON.put(FIELD_NAMES[8], getRoundNumInJail());


		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return playerAsJSON;
}
public String toString(){
	String playerAsString = this.toJSON().toString();
	return playerAsString;
}
public boolean repOK(){
	if(getName()==null || getSquares()==null || getStocks()==null || getCards()==null || getPiece()==null ){
		return false;
	}
	ArrayList<BuyableSquare> sqrs=getSquares();
	for(int i=1; i<sqrs.size(); i++){
		Square sq1=sqrs.get(i);
		for(int j=0; j<i; j++){
		Square sq2=sqrs.get(j);
			if(sq1.equals(sq2))
				return false;	
		}
	}
	ArrayList<Stock> stcks=getStocks();
	for(int i=1; i<stcks.size(); i++){
		Stock st1=stcks.get(i);
		for(int j=0; j<i; j++){
		Stock st2=stcks.get(j);
			if(st1.equals(st2))
				return false;	
		}
	}
	ArrayList<Card> crds=getCards();
	for(int i=1; i<crds.size(); i++){
		Card cd1=crds.get(i);
		for(int j=0; j<i; j++){
		Card cd2=crds.get(j);
			if(cd1.equals(cd2))
				return false;	
		}
	}
	
<<<<<<< Updated upstream
	/**
	 * 	Player class constructor.
	 * @param name: name of the player
	 * @param money: initial money of the player which is 3200$ in the beginning of the game. 

	 */
	
	public Player(String name, int money) {
		//@requires: name is not null and 0<=money
		//@effects: Initialize player with name and money
		
=======
	return true;
	
}
/**
 * 	Player class constructor.
 * @param name: name of the player
 * @param money: initial money of the player which is 3200$ in the beginning of the game. 

 */
	public Player(String name, int money) {
		//@requires: name is not null and 0<=money
		//@effects:Initialize player with name and money
>>>>>>> Stashed changes
		super();
		this.name = name;
		this.money = money;
		this.playerObservers = new ArrayList<PlayerObserver>();
		this.cards = new ArrayList<Card>();
		this.squares = new ArrayList<BuyableSquare>();
		this.stocks = new ArrayList<Stock>();
		this.piece = new Piece(this);
		this.isInJail = false;
		this.isBargainSelected = false;
		this.roundNumInJail = 0;
	}
	/**
	 * 
	 * @return wealth of the player which is summation of worth of properties, assets and money
	 */
	
	/**
	 * 
	 * @return wealth of the player which is summation of worth of properties, assets and money
	 */
	
	public int getWealth() {
		//@effects: returns wealth of the player which is summation of worth of properties, assets and money
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		int wealth = getMoney();
		ArrayList<BuyableSquare> squares = getSquares();
		int size = squares.size();
		
		for (int i = 0; i < size; i++) {
			BuyableSquare square = squares.get(i);
			wealth += square.getPrice();
			
			if (square instanceof ColorSquare) {
				wealth += ((ColorSquare) square).getBuildingWealth();
			} else if (square instanceof RailRoadSquare) {
				RailRoadSquare railRoadSquare = (RailRoadSquare) square;
				
				if (railRoadSquare.isTrainDepotBuilt()) {
					wealth += 100;
				}
			}
		}
		
		return wealth;
	}
	
	/**
	 * 
	 * @return true if the player is in jail false otherwise.
	 */
<<<<<<< Updated upstream
	
	public boolean isInJail() {
		//@effect: returns true if the player is in jail, false otherwise
		
=======
	public boolean isInJail() {
		//@effect: returns true if the player is in jail, false otherwise
>>>>>>> Stashed changes
		return isInJail;
	}
	/**
	 * puts the player in jail if the input is true, or frees the player from the jail if input is false
	 * @param isInJail
	 */
	
	/**
	 * puts the player in jail if the input is true, or frees the player from the jail if input is false
	 * @param isInJail
	 */
	
	public void setInJail(boolean isInJail) {
		//@modifies: this
		//@effects: puts the player in jail if the input is true, or frees the player from the jail if input is false
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.isInJail = isInJail;
	}
	/**
	 * returns observers of the player class.
	 * @return observers of player class
	 */
	
	/**
	 * returns observers of the player class.
	 * @return observers of player class
	 */
	
	public ArrayList<PlayerObserver> getPlayerObservers() {
		//@effects: returns observers of the player class.
		return playerObservers;
	}
	/**
	 * sets player observers.
	 * @param playerObservers
	 */
	public void setPlayerObservers(ArrayList<PlayerObserver> playerObservers) {
		//@requires: piece is not null
		//@modifies: this
		//@effects: sets player observers.
		this.playerObservers = playerObservers;
	}
	/**
	 * 
	 * @return name of the player
	 */
	public String getName() {
		//@effects: returns the name of the player
		return name;
	}
	/**
	 * sets the name of the player with the given input
	 * @param name
	 */
	public void setName(String name) {
		//@requires: name is not null
		//@modifies: this
		//@effects: sets the name of the player with the given input.
		this.name = name;
	}
	/**
	 * 
	 * @returns the money of the player
	 */
	public int getMoney() {
		//@effects: returns the money of the player.
		return money;
	}
	/**
	 * sets the money of the the player as the given input and notifies observer.
	 * @param money
	 */
	public void setMoney(int money) {
		//@requires: 0<=money 
		//@modifies: this
		//@effects: sets the money of the the player as the given input and notifies observer.
		this.money = money;
		notifyPlayerObservers();
	}
	/**
	 * 
	 * @return the cards of the player.
	 */
	
	public ArrayList<Card> getCards() {
		//@effects: returns the cards of the player.
		return cards;
	}
	/**
	 * sets the cards of the player as the given input.
	 * @param cards
	 */
	public void setCards(ArrayList<Card> cards) {
		//@requires: cards is not null
		//@modifies: this
		//@effects: sets the cards of the player as the given input.
		this.cards = cards;
	}

	public ArrayList<BuyableSquare> getSquares() {
		//@effects: returns the squares of the player.
		return squares;
	}

	public void setSquares(ArrayList<BuyableSquare> squares) {
		//@requires: squares is not null
		//@modifies: this
		//@effects: sets the squares of the player as the given input.
		this.squares = squares;
	}

	public Piece getPiece() {
		//@effects: returns the pieces of the player.
		return piece;
	}

	public void setPiece(Piece piece) {
		//@requires: piece is not null
		//@modifies: this
		//@effects: sets the piece of the player as the given input.
		this.piece = piece;
	}
	
	public String getDirection() {
		//@effects: returns the direction of the player.
		return getPiece().getDirection();
	}
	
	public void setDirection(String direction) {
		//@requires: direction is not null		
		//@modifies: this
		//@effects: sets the direction of the player as the given input.
		getPiece().setDirection(direction);
	}
	
	public void addPlayerObserver(PlayerObserver playerObserver){
		//@requires: playerObserver is not null
		//@modifies: this
		//@effects: adds input player observer to the observers.
		getPlayerObservers().add(playerObserver);
	}
	
	public void notifyPlayerObservers(){
		//@effects: notifies all of the observers   
		for(PlayerObserver p: this.playerObservers){
			p.update(this);
		}
	}
	
	public void move(int stepNum){
		//@requires: stepNum>=0
		//@modifies: this
		//@effects: moves the piece of the player according to stepNum
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		getPiece().move(stepNum);
	}
	
	public void move(Square square) {
		//@requires: square is not null
		//@modifies: this
		//@effects: moves the piece of the player to the square
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		getPiece().move(square);
	}
	
	public void moveImmediate(Square square){
		//@requires: square is not null
		//@modifies: this
		//@effects: moves the piece of the player to the square
		getPiece().moveImmediate(square);
	}
	
	public ChanceCard selectChanceCard(MonopolyBoard monopolyBoard){
		//@requires: monopolyBoard is not null
		//@effects: returns randomly selected ChanceCard 
		return monopolyBoard.getChanceCard();
	}
	
	public CommunityCard selectCommunityCard(MonopolyBoard monopolyBoard){
		//@requires: monopolyBoard is not null
		//@effects: returns randomly selected CommunityCard 
		return monopolyBoard.getCommunityCard();
	}
	
	public void makePayment(Player player, int payment){
		//@requires: player is not null and payment<=money
		//@modifies: this, player
		//@effects: decreases the money of the player according to payment 
<<<<<<< Updated upstream
		
=======
		System.out.println("in the makePayment" + payment);
>>>>>>> Stashed changes
		receivePayment(payment * -1);
		player.receivePayment(payment);
	}
	
	public void makePayment(Bank bank, int payment) {
		//@requires: bank is not null 
		//@modifies: this
		//@effects: decreases the money of the player according to payment 
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		receivePayment(payment * -1);
		bank.receivePayment(payment);
	}
	
	public void receivePayment(int payment){
		//@modifies: this
<<<<<<< Updated upstream
		//@effects: player's money is increased by specified payment amount.
		
=======
		//@effects: increases the money of the player according to payment 
>>>>>>> Stashed changes
		setMoney(getMoney() + payment);
	}
	
	public void buySquare(Bank bank, BuyableSquare square, int payment) {
		//@requires: bank, square is not null 
		//@modifies: this, square, bank
		//@effects: decreases the money of the player according to payment, and adds square to the squares
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		receivePayment(payment * -1);
		bank.removeBuyableSquare(square);
		getSquares().add(square);
		square.setOwner(this);
		notifyPlayerObservers();
	}
	
	public void buySquare(Player player, BuyableSquare square, int payment){
		//@requires: player, square is not null 
		//@modifies: this, square, player
		//@effects: decreases the money of the player according to payment, and adds square to the squares and changes the owner of the square
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		makePayment(player, payment);
		player.getSquares().remove(square);
		getSquares().add(square);
		square.setOwner(this);
		notifyPlayerObservers();
		player.notifyPlayerObservers();
	}
	
	public void sellSquare(Bank bank, BuyableSquare square) {
		//@requires: bank, square is not null 
	    //@modifies: this, square, bank
		//@effects: increases the money of the player according to price of square, and changes the owner of square
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		receivePayment(square.getPrice());
		getSquares().remove(square);
		square.setOwner(null);
		bank.addBuyableSquare(square);
		notifyPlayerObservers();
	}
	
	public void sellSquare(Player player, BuyableSquare square, int payment){
		//@requires: player, square is not null 
	    //@modifies: this, square, player
		//@effects: increases the money of the player according to payment, and changes the owner of the square
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		player.buySquare(this, square, payment);
	}
	
	public void buyHouse(ColorSquare square){
		//@requires: square is not null 
	    //@modifies: square, this
		//@effects: adds house to the square and decreases the money and notifies the observer
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		receivePayment(-square.getHouseCost());
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}
	
	public void buyHotel(ColorSquare square){
		//@requires: square is not null 
	    //@modifies: square, this
		//@effects: adds hotel to the square and decreases the money and notifies the observer
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.money -= square.getHotelCost();
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}

	public void buySkyscraper(ColorSquare square){
		//@requires: square is not null 
	    //@modifies: square, this
		//@effects: adds skyscraper to the square and decreases the money and notifies the observer
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.money -= square.getSkyscraperCost();
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}
	
	public void sellHouse(ColorSquare square){
		//@requires: square is not null 
	    //@modifies: square, this
		//@effects: removes a house from the square and increases the money and notifies the observer
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.money += square.getHouseCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}
	
	public void sellHotel(ColorSquare square){
		//@requires: square is not null 
	    //@modifies: square, this
		//@effects: removes a hotel from the square and increases the money and notifies the observer
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.money += square.getHotelCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}

	public void sellSkyscraper(ColorSquare square){
		//@requires: square is not null 
	    //@modifies: square, this
		//@effects: removes a skyscraper from the square and increases the money and notifies the observer
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.money += square.getSkyscraperCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}
	
	public void buyTrainDepot(RailRoadSquare square) {
		//@requires: square != null
		//@modifies: this, square
		//@effects: train depot is built on specified square. Cost of train depot is cut from player's money.
		
		receivePayment(-100);
		square.setTrainDepotBuilt(true);
		notifyPlayerObservers();
		square.notifySquareObservers();
	}
	
	public void sellTrainDepot(RailRoadSquare square) {
		//@requires: square != null
		//@modifies: this, square
		//@effects: train depot on specified square is sold. Half of the cost of train depot
		//is added to player's money.
		
		receivePayment(50);
		square.setTrainDepotBuilt(false);
		notifyPlayerObservers();
		square.notifySquareObservers();
	}
	
	public Square getCurrentLocation(){
		//@effects: returns location of the piece
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		return this.piece.getCurrentLocation();
	}
	
	public boolean isBargainSelected() {
		//@effects: returns true if bargain is selected false otherwise
		return isBargainSelected;
	}

	public void setBargainSelected(boolean isBargainSelected) {
		//@modifies: this
		//@effects: updates isBargainSelected
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.isBargainSelected = isBargainSelected;
	}

	public boolean hasBargain() {
<<<<<<< Updated upstream
		//@effects: returns true if the player has any bargains
		
=======
		//@effects: returns true if the player has any bargains 
>>>>>>> Stashed changes
		boolean hasBargain = false;
		
		for (int i = 0; i < getCards().size(); i++) {
			if (getCards().get(i).getContent().contains("Bargain")) {
				hasBargain = true;
				break;
			}
		}
		
		return hasBargain;
	}
	
	public void applyMortgageTo(BuyableSquare square){
		//@requires: square is not null
		//@modifies: square, this
		//@effects: square is mortgaged, player receives mortgage value.
		
		receivePayment(square.getMortgageValue());
		square.setMortgaged(true);
		notifyPlayerObservers();
	}
	
	public void removeMortgageFrom(BuyableSquare square){
		//@requires: square is not null
		//@modifies: square, this
		//@effects: mortgage is removed from square , player pays the mortgage value with 10% interest rate.
		
		receivePayment((int) (1.1 * square.getMortgageValue() * -1));
		square.setMortgaged(false);
		notifyPlayerObservers();
	}
	
	public void applyMortgageTo(Stock stock) {
		//@requires: stock is not null
		//@modifies: stock, this
		//@effects: stock is mortgaged, player receives mortgage value.
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		int loanValue = stock.getLoanValue();
		receivePayment(loanValue);
		stock.setMortgaged(true);
		notifyPlayerObservers();
	}
	
	public void removeMortgageFrom(Stock stock) {
		//@requires: stock is not null
		//@modifies: stock, this
		//@effects: mortgage is removed from stock , player pays the mortgage value with 10% interest rate.
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		int loanValue = stock.getLoanValue();
		loanValue = ((int) (loanValue * 1.1)) * -1;
		receivePayment(loanValue);
		stock.setMortgaged(false);
		notifyPlayerObservers();
	}
	
	public void collectDivident(String companyName) {
		//@requires: companyName is not null
		//@modifies: this
<<<<<<< Updated upstream
		//@effects: player earns money according to his number of stocks with given companyName.
		
=======
		//@effects: player earns money according to his number of stocks with given companyName. 
>>>>>>> Stashed changes
		int stockNumberWithSameCompanyName = 0;
		int firstDivident = 0;
		ArrayList<Stock> stocks = getStocks();
		int size = stocks.size();
		
		for (int i = 0; i < size; i++) {
			Stock stock = stocks.get(i);
			String stockName = stock.getName();
			String stockCompanyName = stockName.substring(0, stockName.length() - 1);
			
			if (stockCompanyName.equals(companyName)) {
				firstDivident = stock.getFirstDivident();
				stockNumberWithSameCompanyName++;
			}
		}
		
		receivePayment(stockNumberWithSameCompanyName * stockNumberWithSameCompanyName * firstDivident);
	}
	
	
	public void collectAllDividents() {
		//@modifies: this
		//@effects: player earns money according to his number of stocks.
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		String[] companys_names = Stock.getCompanyNames();
		int length = companys_names.length;
		
		for (int i = 0; i < length; i++) {
			collectDivident(companys_names[i]);
		}
	}
	
<<<<<<< Updated upstream
	/*public void buyStock(Player player, Stock stock, int payment) {
		makePayment(player, payment);
		player.getStocks().remove(stock);
=======
	public void buyStock(Bank bank, Stock stock) {
		//@requires: bank, stock is not null 
		//@modifies: this, stock, bank
		//@effects: decreases the money of the player according to payment, and adds stock to the stocks and changes the owner of the stock
		receivePayment(stock.getParValue() * -1);
		bank.removeStock(stock);
>>>>>>> Stashed changes
		getStocks().add(stock);
		stock.setOwner(this);
		notifyPlayerObservers();
		player.notifyPlayerObservers();
	}
	
<<<<<<< Updated upstream
	public void sellStock(Player player, Stock stock, int payment) {
		player.buyStock(this, stock, payment);
	}*/
	
	public void buyStock(Bank bank, Stock stock, int payment) {
		//@requires: bank, stock is not null 
		//@modifies: this, stock, bank
		//@effects: decreases the money of the player according to payment, and adds stock to the stocks and changes the owner of the stock
		
		receivePayment(payment * -1);
		bank.removeStock(stock);
		getStocks().add(stock);
		stock.setOwner(this);
=======
	public void applyMortgageTo(BuyableSquare square){
		//@requires: square is not null
		//@modifies: square, this
		//@effects: square is mortgaged, player receives mortgage value.
		receivePayment(square.getMortgageValue());
		square.setMortgaged(true);
		notifyPlayerObservers();
	}
	
	public void removeMortgageFrom(BuyableSquare square){
		//@requires: square is not null
		//@modifies: square, this
		//@effects: mortgage is removed from square , player pays the mortgage value with 10% interest rate.
		receivePayment((int) (1.1 * square.getMortgageValue() * -1));
		square.setMortgaged(false);
>>>>>>> Stashed changes
		notifyPlayerObservers();
	}
	
	public void setRoundNumInJail(int roundNumInJail) {
		//@modifies: this
	    //@effects: sets given input  as the number of rounds spent in jail
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.roundNumInJail = roundNumInJail;
	}
	
	public int getRoundNumInJail() {
<<<<<<< Updated upstream
		//@effects: returns roundNumInJail.
		
=======
		//@effects: returns roundNumInJail. 
>>>>>>> Stashed changes
		return roundNumInJail;
	}
	
	public void increaseRoundNumInJail() {
		//@modifies: roundNumInJail
	    //@effects: increase the number of rounds spent in jail by 1
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		setRoundNumInJail(getRoundNumInJail() + 1);
	}

	public ArrayList<Stock> getStocks() {
<<<<<<< Updated upstream
		//@effects: returns stocks.
		
=======
		//@effects: returns stocks. 
>>>>>>> Stashed changes
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		//@requires: stocks is not null
		//@modifies: this
		//@effects: updates stocks 
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		this.stocks = stocks;
	}
	
}
