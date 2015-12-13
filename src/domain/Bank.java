package domain;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONObject;

public class Bank {
	private ArrayList<BankObserver> bankObservers;
	private ArrayList<BuyableSquare> buyableSquares;
	private ArrayList<Stock> stocks;
	private int poolMoney;
	
	public Bank(ArrayList<Square> outerSquares, ArrayList<Square> middleSquares, ArrayList<Square> innerSquares) {
		setBankObservers(new ArrayList<BankObserver>());
		setBuyableSquares(new ArrayList<BuyableSquare>());
		setStocks(composeStocks());
		pickBuyableSquares(outerSquares, middleSquares, innerSquares);
		setPoolMoney(0);
	}
	
	private ArrayList<Stock> composeStocks() {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		Reader reader = new Reader();
		ArrayList<JSONObject> stocksAsJSON = reader.read("stocks.txt");
		int size = stocksAsJSON.size();
		
		for (int i = 0; i < size; i++) {
			stocks.add(Stock.fromJSON(stocksAsJSON.get(i)));
		}
		return stocks;
	}

	private void pickBuyableSquares(ArrayList<Square> outerSquares,ArrayList<Square> middleSquares, ArrayList<Square> innerSquares) {
		ArrayList<BuyableSquare> buyableSquares = getBuyableSquares();
		Square square;
		
		for (int i = 0; i < outerSquares.size(); i++) {
			square = outerSquares.get(i);
			
			if (square instanceof BuyableSquare) {
				buyableSquares.add((BuyableSquare) square);
			}
		}
		
		for (int i = 0; i < middleSquares.size(); i++) {
			square = middleSquares.get(i);
			
			if (square instanceof BuyableSquare) {
				buyableSquares.add((BuyableSquare) square);
			}
		}
		
		for (int i = 0; i < innerSquares.size(); i++) {
			square = innerSquares.get(i);
			
			if (square instanceof BuyableSquare) {
				buyableSquares.add((BuyableSquare) square);
			}
		}
	}
	
	public BuyableSquare getBuyableSquare(String name) {
		BuyableSquare buyableSquare = null;
		ArrayList<BuyableSquare> buyableSquares = getBuyableSquares();
		int size = buyableSquares.size();
		
		for (int i = 0; i < size; i++) {
			BuyableSquare square = buyableSquares.get(i);
			
			if (square.getName().equals(name)) {
				buyableSquare = square;
				break;
			}
		}
		
		return buyableSquare;
	}
	
	public boolean isUnownedBuyableSquareLeft() {
		boolean result = false;
		
		if (getBuyableSquares().size() == 0) {
			result = true;
		}
		
		return result;
	}
	
	public void addBuyableSquare(BuyableSquare square) {
		square.setOwner(null);
		getBuyableSquares().add(square);
	}
	
	public void removeBuyableSquare(BuyableSquare square) {
		getBuyableSquares().remove(square);
	}
	
	public ArrayList<BuyableSquare> getBuyableSquares() {
		return buyableSquares;
	}

	public void setBuyableSquares(ArrayList<BuyableSquare> buyableSquares) {
		this.buyableSquares = buyableSquares;
	}
	
	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public ArrayList<Stock> getStocks() {
		return stocks;
	}
	
	public boolean isUnownedStockLeft() {
		boolean result = false;
		
		if (getStocks().size() == 0) {
			result = true;
		}
		
		return result;
	}
	
	public Stock getStock(String companyName) {
		Stock stockWanted = null;
		ArrayList<Stock> stocks = getStocks();
		int size = stocks.size();
		
		for (int i = 0; i < size; i++) {
			Stock stock = stocks.get(i);
			
			if (stock.getCompanyName().equals(companyName)) {
				stockWanted = stock;
				break;
			}
		}
		
		return stockWanted;
	}
	
	public void addStock(Stock stock) {
		getStocks().add(stock);
	}
	
	public void removeStock(Stock stock) {
		getStocks().remove(stock);
	}
	
	public void setPoolMoney(int poolMoney) {
		this.poolMoney = poolMoney;
		notifyBankObservers();
	}
	
	public void notifyBankObservers() {
		ArrayList<BankObserver> bankObservers = getBankObservers();
		int size = bankObservers.size();
		
		for (int i = 0; i < size; i++) {
			bankObservers.get(i).update(this);
		}
	}
	
	public void addBankObserver(BankObserver bankObserver) {
		getBankObservers().add(bankObserver);
	}

	public ArrayList<BankObserver> getBankObservers() {
		return bankObservers;
	}

	public void setBankObservers(ArrayList<BankObserver> bankObservers) {
		this.bankObservers = bankObservers;
	}

	public int getPoolMoney() {
		return poolMoney;
	}
	
	public void receivePayment(int payment) {
		setPoolMoney(getPoolMoney() + payment);
	}
}
