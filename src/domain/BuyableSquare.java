package domain;
public abstract class BuyableSquare extends Square {
	
	private int price;
	private boolean isMortgaged;
	private Player owner;
	
	public BuyableSquare(String name, int price) {
		super(name);
		this.price=price;
		this.isMortgaged=false;
		this.owner=null;	
		// TODO Auto-generated constructor stub
	}
	
	public Player getOwner() {
		return owner;
	}


	public void setOwner(Player owner) {
		this.owner = owner;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	public abstract int getCurrentRent();
	
	public boolean isMortgaged() {
		return isMortgaged;
	}


	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}
	
	public void landedOn(Piece piece){
		Player pieceOwner = piece.getOwner();
		Player squareOwner = getOwner();
		
		if (squareOwner == null) {
			if (pieceOwner.getMoney() >= getPrice()) {
				pieceOwner.buySquare(GameController.getInstance().getMonopolyBoard().getBank(), this);
			}
		} else {
			if (!pieceOwner.equals(squareOwner)) {
				if (!isMortgaged()) {
					if (this instanceof UtilitySquare) {
						pieceOwner.makePayment(squareOwner, ((UtilitySquare) this).getCurrentRent());
					} else {
						pieceOwner.makePayment(squareOwner, getCurrentRent());
					}
				}
			}
		}
	}
}
