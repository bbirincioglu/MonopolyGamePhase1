package domain;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class MonopolyBoard {
	private ArrayList<Square> outerSquares;
	private ArrayList<Square> middleSquares;
	private ArrayList<Square> innerSquares;
	
	private ArrayList<ChanceCard> chanceCards;
	private int currentChanceCardIndex;
	
	private ArrayList<CommunityCard> communityCards;
	private int currentCommunityCardIndex;
	
	private Bank bank;
	
	public MonopolyBoard() {
		initializeCards();
		initializeSquares();
		connectSquares(getOuterSquares(), getMiddleSquares(), getInnerSquares());
		setBank(new Bank(getOuterSquares(), getMiddleSquares(), getInnerSquares()));
		shuffleCards(getChanceCards(), getCommunityCards());
		setCurrentChanceCardIndex(0);
		setCurrentCommunityCardIndex(0);
	}
	
	private void shuffleCards(ArrayList<ChanceCard> chanceCards, ArrayList<CommunityCard> communityCards) {
		Random random = new Random();
		
		for (int i = 0; i < chanceCards.size(); i++) {
			int index1 = i;
			int index2 = i + random.nextInt(chanceCards.size() - i);
			
			ChanceCard temp = chanceCards.get(index1);
			chanceCards.set(index1, chanceCards.get(index2));
			chanceCards.set(index2, temp);
		}
		
		for (int i = 0; i < communityCards.size(); i++) {
			int index1 = i;
			int index2 = i + random.nextInt(communityCards.size() - i);
			
			CommunityCard temp = communityCards.get(index1);
			communityCards.set(index1, communityCards.get(index2));
			communityCards.set(index2, temp);
		}
	}
	
	/*public void initializeCards() {
		setChanceCards(new ArrayList<ChanceCard>());
		setCommunityCards(new ArrayList<CommunityCard>());
		ArrayList<JSONObject> chanceCardsAsJSON = Reader.readChanceCards(fileName);
		ArrayList<JSONObject> communityCardsAsJSON = Reader.readCommunityCards(fileName);
		
		for (int i = 0; i < chanceCardsAsJSON.size(); i++) {
			ChanceCard chanceCard = ChanceCard.fromJSON(chanceCardsAsJSON.get(i));
			getChanceCards().add(chanceCard);
		}
		
		for (int i = 0; i < communityCardsAsJSON.size(); i++) {
			CommunityCard communityCard = CommunityCard.fromJSON(communityCardsAsJSON.get(i));
			getCommunityCards().add(communityCard);
		}
	}*/
	
	public void initializeCards() {
		setChanceCards(new ArrayList<ChanceCard>());
		setCommunityCards(new ArrayList<CommunityCard>());
		setCurrentChanceCardIndex(0);
		setCurrentCommunityCardIndex(0);
		
		ArrayList<JSONObject> chanceCardsAsJSON = Reader.read("chance.txt");
		ArrayList<JSONObject> communityCardsAsJSON = Reader.read("community.txt");
		CardFactory cardFactory = CardFactory.getInstance();
		
		for (int i = 0; i < chanceCardsAsJSON.size(); i++) {
			try {
				getChanceCards().add(cardFactory.createChanceCard(chanceCardsAsJSON.get(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < communityCardsAsJSON.size(); i++) {
			try {
				getCommunityCards().add(cardFactory.createCommunityCard(communityCardsAsJSON.get(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void initializeSquares() {
		setOuterSquares(new ArrayList<Square>());
		setMiddleSquares(new ArrayList<Square>());
		setInnerSquares(new ArrayList<Square>());
		
		ArrayList<Square> outerSquares = getOuterSquares();
		ArrayList<Square> middleSquares = getMiddleSquares();
		ArrayList<Square> innerSquares = getInnerSquares();
		
		ArrayList<JSONObject> outerSquaresAsJSON = Reader.read("outerSquares.txt");
		ArrayList<JSONObject> middleSquaresAsJSON = Reader.read("middleSquares.txt");
		ArrayList<JSONObject> innerSquaresAsJSON = Reader.read("innerSquares.txt");
		SquareFactory squareFactory = SquareFactory.getInstance();
		
		for (int i = 0; i < outerSquaresAsJSON.size(); i++) {
			outerSquares.add(squareFactory.createSquare(outerSquaresAsJSON.get(i)));
		}
		
		for (int i = 0; i < middleSquaresAsJSON.size(); i++) {
			middleSquares.add(squareFactory.createSquare(middleSquaresAsJSON.get(i)));
		}
		
		for (int i = 0; i < innerSquaresAsJSON.size(); i++) {
			innerSquares.add(squareFactory.createSquare(innerSquaresAsJSON.get(i)));
		}
	}
	
	private void printNames(ArrayList<JSONObject> list) {
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println(list.get(i).getString("name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void connectSquares(ArrayList<Square> outerSquares, ArrayList<Square> middleSquares, ArrayList<Square> innerSquares) {
		connectRegularly(outerSquares);
		connectRegularly(middleSquares);
		connectRegularly(innerSquares);
		connectHollandTunnels(outerSquares, innerSquares);
		connectRailRoadsWithStations(outerSquares, middleSquares, innerSquares);
		connectGoToJailWithVisitingJail();
	}
	
	private void connectRailRoadsWithStations(ArrayList<Square> outerSquares, ArrayList<Square> middleSquares, ArrayList<Square> innerSquares) {
		RailRoadSquare readingRailRoad = (RailRoadSquare) getSquare("READING RAILROAD");
		RailRoadSquare bAndORailRoad = (RailRoadSquare) getSquare("B&0 RAILROAD");
		RailRoadSquare pennsylvaniaRailRoad = (RailRoadSquare) getSquare("PENNSYLVANIA RAILROAD");
		RailRoadSquare shortLineRailRoad = (RailRoadSquare) getSquare("SHORT LINE");
		
		TransitStation[] stations = new TransitStation[4];	
		int index = 0;
		
		for (int i = 0; i < middleSquares.size(); i++) {
			if (middleSquares.get(i).getName().equals("TransitStation")) {
				stations[index] = (TransitStation) middleSquares.get(i);
				index = index + 1;
			}
		}
		
		for (int i = 0; i < innerSquares.size(); i++) {
			if (innerSquares.get(i).getName().equals("TransitStation")) {
				stations[index] = (TransitStation) innerSquares.get(i);
				index = index + 1;
			}
		}
		
		readingRailRoad.setUp(stations[0]);
		stations[0].setDown(readingRailRoad);
		bAndORailRoad.setUp(stations[1]);
		stations[1].setDown(bAndORailRoad);
		pennsylvaniaRailRoad.setUp(stations[2]);
		stations[2].setDown(pennsylvaniaRailRoad);
		shortLineRailRoad.setUp(stations[3]);
		stations[3].setDown(shortLineRailRoad);
	}
	
	private void connectGoToJailWithVisitingJail() {
		GoToJail goToJail = (GoToJail) getSquare("GoToJail");
		VisitingJail visitingJail = (VisitingJail) getSquare("VisitingJail");
		goToJail.setVisitingJail(visitingJail);
	}
	
	private void connectHollandTunnels(ArrayList<Square> outerSquares, ArrayList<Square> innerSquares) {
		HollandTunnel outerHollandTunnel = (HollandTunnel) getSquareHelper("HollandTunnel", outerSquares);
		HollandTunnel innerHollandTunnel = (HollandTunnel) getSquareHelper("HollandTunnel", innerSquares);
		outerHollandTunnel.setOpposite(innerHollandTunnel);
		innerHollandTunnel.setOpposite(outerHollandTunnel);
	}
	
	private void connectRegularly(ArrayList<Square> squares) {
		int size = squares.size();
		
		for (int i = 0; i < size; i++) {
			Square current = squares.get(i);
			int previousIndex = i - 1;
			
			if (previousIndex < 0) {
				previousIndex = previousIndex + size;
			}
			
			int nextIndex = (i + 1) % size;
			
			Square previous = squares.get(previousIndex);
			Square next = squares.get(nextIndex);
			
			current.setNext(next);
			current.setPrevious(previous);
		}
	}
	
	public Square getSquare(String name) {
		Square square = null;
		ArrayList<Square> outerSquares = getOuterSquares();
		ArrayList<Square> middleSquares = getMiddleSquares();
		ArrayList<Square> innerSquares = getInnerSquares();
		
		square = getSquareHelper(name, outerSquares);
		
		if (square == null) {
			square = getSquareHelper(name, middleSquares);
			
			if (square == null) {
				square = getSquareHelper(name, innerSquares);
			}
		}
		
		return square;
	}
	
	private Square getSquareHelper(String name, ArrayList<Square> squares) {
		Square square = null;
		
		for (int i = 0; i < squares.size(); i++) {
			if (squares.get(i).getName().equals(name)) {
				square = squares.get(i);
				break;
			}
		}
		
		return square;
	}
	
	public ChanceCard getChanceCard() {
		ChanceCard chanceCard = getChanceCards().get(getCurrentChanceCardIndex());
		setCurrentChanceCardIndex((getCurrentChanceCardIndex() + 1) % getChanceCards().size());
		return chanceCard;
	}
	
	public CommunityCard getCommunityCard() {
		CommunityCard communityCard = getCommunityCards().get(getCurrentCommunityCardIndex());
		setCurrentCommunityCardIndex((getCurrentCommunityCardIndex() + 1) % getCommunityCards().size());
		return communityCard;
	}
	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public ArrayList<Square> getOuterSquares() {
		return outerSquares;
	}

	public void setOuterSquares(ArrayList<Square> outerSquares) {
		this.outerSquares = outerSquares;
	}

	public ArrayList<Square> getMiddleSquares() {
		return middleSquares;
	}

	public void setMiddleSquares(ArrayList<Square> middleSquares) {
		this.middleSquares = middleSquares;
	}

	public ArrayList<Square> getInnerSquares() {
		return innerSquares;
	}

	public void setInnerSquares(ArrayList<Square> innerSquares) {
		this.innerSquares = innerSquares;
	}

	public ArrayList<ChanceCard> getChanceCards() {
		return chanceCards;
	}

	public void setChanceCards(ArrayList<ChanceCard> chanceCards) {
		this.chanceCards = chanceCards;
	}

	public ArrayList<CommunityCard> getCommunityCards() {
		return communityCards;
	}

	public void setCommunityCards(ArrayList<CommunityCard> communityCards) {
		this.communityCards = communityCards;
	}

	public int getCurrentChanceCardIndex() {
		return currentChanceCardIndex;
	}

	public void setCurrentChanceCardIndex(int currentChanceCardIndex) {
		this.currentChanceCardIndex = currentChanceCardIndex;
	}

	public int getCurrentCommunityCardIndex() {
		return currentCommunityCardIndex;
	}

	public void setCurrentCommunityCardIndex(int currentCommunityCardIndex) {
		this.currentCommunityCardIndex = currentCommunityCardIndex;
	}
}
