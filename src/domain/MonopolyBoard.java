import java.util.ArrayList;

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
		connectSquares();
		setCurrentChanceCardIndex(0);
		setCurrentCommunityCardIndex(0);
	}
	
	public void initializeCards() {
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
	}
	
	public void initializeSquares() {
		
	}
	
	public void connectSquares() {
		
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
