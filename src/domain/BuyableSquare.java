
public class BuyableSquare {
	
	private Player owner;
	public int houseCost;
	public int hotelCost;
	public int skyscraperCost;
	public int buildingNum;
	public int mortgageValue;
	public boolean isMortgaged;
	
	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}

	public void setOwner(Player player) {
		// TODO Auto-generated method stub
		this.owner = player;
	}

	public Player getOwner() {
		return owner;
	}

	public int getHouseCost() {
		return houseCost;
	}

	public void setHouseCost(int houseCost) {
		this.houseCost = houseCost;
	}

	public int getHotelCost() {
		return hotelCost;
	}

	public void setHotelCost(int hotelCost) {
		this.hotelCost = hotelCost;
	}

	public int getSkyscraperCost() {
		return skyscraperCost;
	}

	public void setSkyscraperCost(int skyscraperCost) {
		this.skyscraperCost = skyscraperCost;
	}

	public int getBuildingNum() {
		return buildingNum;
	}

	public void setBuildingNum(int buildingNum) {
		this.buildingNum = buildingNum;
	}	
}
