import java.util.ArrayList;

public class Player {
	
	private ArrayList<PlayerObserver> playerObservers;
	private String name;
	private int money;
	private ArrayList<BuyableSquare> squares;
	private ArrayList<Card> cards;
	private Piece piece;
	
	public Player(String name, int money, Square initialLocation) {
		super();
		this.name = name;
		this.money = money;
		this.playerObservers = new ArrayList<PlayerObserver>();
		this.cards = new ArrayList<Card>();
		this.squares = new ArrayList<BuyableSquare>();
		this.piece = new Piece(this, initialLocation);
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
	
	public ChanceCard selectChanceCard(MonopolyBoard monopolyBoard){
		return monopolyBoard.getChanceCard();
	}
	
	public CommunityCard selectCommunityCard(MonopolyBoard monopolyBoard){
		return monopolyBoard.getCommunityCard();
	}
	
	public void makePayment(Player player, int payment){
		this.money -= payment;
		player.receivePayment(payment);
	}
	
	public void receivePayment(int payment){
		this.money += payment;
	}
	
	public void buySquare(Bank bank, BuyableSquare square){
		setMoney(getMoney() - square.)
		this.squares.add(square);
	}
	
	public void buySquare(Player player, BuyableSquare square, int payment){
		this.makePayment(player, payment);
		this.squares.add(square);
		player.getSquares().remove(square);
		square.setOwner(this);
	}
	
	public void sellSquare(Bank bank, BuyableSquare square){
		/*
		 * 
		 * 
		 * 
		 */
		
		this.squares.add(square);
	}
	
	public void sellSquare(Player player, BuyableSquare square, int payment){
		player.buySquare(this, square, payment);
	}
	
	public void buyHouse(BuyableSquare square){
		this.money -= square.getHouseCost();
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}
	
	public void buyHotel(BuyableSquare square){
		this.money -= square.getHotelCost();
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}

	public void buySkyscraper(BuyableSquare square){
		this.money -= square.getSkyscraperCost();
		square.setBuildingNum(square.getBuildingNum()+1);
		notifyPlayerObservers();
	}
	
	public void sellHouse(BuyableSquare square){
		this.money += square.getHouseCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}
	
	public void sellHotel(BuyableSquare square){
		this.money += square.getHotelCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}

	public void sellSkyscraper(BuyableSquare square){
		this.money += square.getSkyscraperCost()/2;
		square.setBuildingNum(square.getBuildingNum()-1);
		notifyPlayerObservers();
	}
	
	public Square getCurrentLocation(){
		return this.piece.getCurrentLocation();
	}
	
	public void move(Square square){
		this.piece.move(square);
	}
	
	public void applyMortgageTo(BuyableSquare square){
		this.receivePayment(square.getMortgageValue());
		square.setMortgaged(true);
		notifyPlayerObservers();
	}
	
	public void removeMortgageFrom(BuyableSquare square){
		this.money -= (int) (1.1 * square.getMortgageValue());
		square.setMortgaged(false);
		notifyPlayerObservers();
	}
}
