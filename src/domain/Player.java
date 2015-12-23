package domain;
import java.util.ArrayList;

public class Player {
	
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
	
	public Player(String name, int money) {
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
	
	public int getWealth() {
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
	
	public boolean isInJail() {
		return isInJail;
	}
	
	public void setInJail(boolean isInJail) {
		this.isInJail = isInJail;
	}
	
	public ArrayList<PlayerObserver> getPlayerObservers() {
		return playerObservers;
	}
	
	public void setPlayerObservers(ArrayList<PlayerObserver> playerObservers) {
		this.playerObservers = playerObservers;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
		notifyPlayerObservers();
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<BuyableSquare> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<BuyableSquare> squares) {
		this.squares = squares;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public String getDirection() {
		return getPiece().getDirection();
	}
	
	public void setDirection(String direction) {
		getPiece().setDirection(direction);
	}
	
	public void addPlayerObserver(PlayerObserver playerObserver){
		getPlayerObservers().add(playerObserver);
	}
	
	public void notifyPlayerObservers(){
		for(PlayerObserver p: this.playerObservers){
			p.update(this);
		}
	}
	
	public void move(int stepNum){
		getPiece().move(stepNum);
	}
	
	public void move(Square square) {
		getPiece().move(square);
	}
	
	public void moveImmediate(Square square){
		getPiece().moveImmediate(square);
	}
	
	public ChanceCard selectChanceCard(MonopolyBoard monopolyBoard){
		return monopolyBoard.getChanceCard();
	}
	
	public CommunityCard selectCommunityCard(MonopolyBoard monopolyBoard){
		return monopolyBoard.getCommunityCard();
	}
	
	public void makePayment(Player player, int payment){
		receivePayment(payment * -1);
		player.receivePayment(payment);
	}
	
	public void makePayment(Bank bank, int payment) {
		receivePayment(payment * -1);
		bank.receivePayment(payment);
	}
	
	public void receivePayment(int payment){
		setMoney(getMoney() + payment);
	}
	
	public void buySquare(Bank bank, BuyableSquare square, int payment) {
		receivePayment(payment * -1);
		bank.removeBuyableSquare(square);
		getSquares().add(square);
		square.setOwner(this);
		notifyPlayerObservers();
	}
	
	public void buySquare(Player player, BuyableSquare square, int payment){
		makePayment(player, payment);
		player.getSquares().remove(square);
		getSquares().add(square);
		square.setOwner(this);
		notifyPlayerObservers();
		player.notifyPlayerObservers();
	}
	
	public void sellSquare(Bank bank, BuyableSquare square) {
		receivePayment(square.getPrice());
		getSquares().remove(square);
		square.setOwner(null);
		bank.addBuyableSquare(square);
		notifyPlayerObservers();
	}
	
	public void sellSquare(Player player, BuyableSquare square, int payment){
		player.buySquare(this, square, payment);
	}
	
	public void buyHouse(ColorSquare square){
		receivePayment(-square.getHouseCost());
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}
	
	public void buyHotel(ColorSquare square){
		this.money -= square.getHotelCost();
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}

	public void buySkyscraper(ColorSquare square){
		this.money -= square.getSkyscraperCost();
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}
	
	public void sellHouse(ColorSquare square){
		this.money += square.getHouseCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}
	
	public void sellHotel(ColorSquare square){
		this.money += square.getHotelCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}

	public void sellSkyscraper(ColorSquare square){
		this.money += square.getSkyscraperCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}
	
	public void buyTrainDepot(RailRoadSquare square) {
		receivePayment(-100);
		square.setTrainDepotBuilt(true);
		notifyPlayerObservers();
		square.notifySquareObservers();
	}
	
	public void sellTrainDepot(RailRoadSquare square) {
		receivePayment(50);
		square.setTrainDepotBuilt(false);
		notifyPlayerObservers();
		square.notifySquareObservers();
	}
	
	public Square getCurrentLocation(){
		return this.piece.getCurrentLocation();
	}
	
	public boolean isBargainSelected() {
		return isBargainSelected;
	}

	public void setBargainSelected(boolean isBargainSelected) {
		this.isBargainSelected = isBargainSelected;
	}

	public boolean hasBargain() {
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
		receivePayment(square.getMortgageValue());
		square.setMortgaged(true);
		notifyPlayerObservers();
	}
	
	public void removeMortgageFrom(BuyableSquare square){
		receivePayment((int) (1.1 * square.getMortgageValue() * -1));
		square.setMortgaged(false);
		notifyPlayerObservers();
	}
	
	public void applyMortgageTo(Stock stock) {
		int loanValue = stock.getLoanValue();
		receivePayment(loanValue);
		stock.setMortgaged(true);
		notifyPlayerObservers();
	}
	
	public void removeMortgageFrom(Stock stock) {
		int loanValue = stock.getLoanValue();
		loanValue = ((int) (loanValue * 1.1)) * -1;
		receivePayment(loanValue);
		stock.setMortgaged(false);
		notifyPlayerObservers();
	}
	
	public void collectDivident(String companyName) {
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
		String[] companys_names = Stock.getCompanyNames();
		int length = companys_names.length;
		
		for (int i = 0; i < length; i++) {
			collectDivident(companys_names[i]);
		}
	}
	
	/*public void buyStock(Player player, Stock stock, int payment) {
		makePayment(player, payment);
		player.getStocks().remove(stock);
		getStocks().add(stock);
		stock.setOwner(this);
		notifyPlayerObservers();
		player.notifyPlayerObservers();
	}
	
	public void sellStock(Player player, Stock stock, int payment) {
		player.buyStock(this, stock, payment);
	}*/
	
	public void buyStock(Bank bank, Stock stock, int payment) {
		receivePayment(payment * -1);
		bank.removeStock(stock);
		getStocks().add(stock);
		stock.setOwner(this);
		notifyPlayerObservers();
	}
	
	public void setRoundNumInJail(int roundNumInJail) {
		this.roundNumInJail = roundNumInJail;
	}
	
	public int getRoundNumInJail() {
		return roundNumInJail;
	}
	
	public void increaseRoundNumInJail() {
		setRoundNumInJail(getRoundNumInJail() + 1);
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}
}
