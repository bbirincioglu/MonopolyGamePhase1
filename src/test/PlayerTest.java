package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	private GameController gameController=GameController.getInstance();
	private MonopolyBoard monopolyBoard=gameController.getMonopolyBoard();
	private Player testerPlayer=gameController.getPlayers().get(0);
	
	
	@Test
	public void testGetWealth() {
		int wealth=testerPlayer.getWealth();
		assertTrue("should preserve representation invariant", testerPlayer.repOK());
		
	}
	@Test
	public void testMoveStep(){
		Square currentLocation=testerPlayer.getCurrentLocation();
		String direction=testerPlayer.getDirection();
		Square expectedSquare;
		if(direction.equals("CLOCKWISE"))
			 expectedSquare=currentLocation.getNext().getNext();
		else
			expectedSquare=currentLocation.getPrevious().getPrevious();						
		int stepNum =2;
		testerPlayer.move(2);
		assertEquals(testerPlayer.getCurrentLocation(), expectedSquare);
		assertTrue(testerPlayer.repOK());
	}
	@Test
	public void testMoveSquare(){
		Square someSquare=monopolyBoard.getInnerSquares().get(5);
		testerPlayer.move(someSquare);
		assertEquals(testerPlayer.getCurrentLocation(), someSquare);
		assertTrue(testerPlayer.repOK());
	}
	@Test
	public void testMoveImmediate(){
		Square someSquare=monopolyBoard.getInnerSquares().get(5);
		testerPlayer.moveImmediate(someSquare);
		assertEquals(testerPlayer.getCurrentLocation(), someSquare);
		assertTrue(testerPlayer.repOK());
	}
	@Test
	public void testSelectChanceCard(){
	testerPlayer.selectChanceCard(monopolyBoard);
	assertTrue(testerPlayer.repOK());
	}
	
	@Test
	public void testSelectCommunityCard(){
	testerPlayer.selectCommunityCard(monopolyBoard);
	assertTrue(testerPlayer.repOK());
	}
	@Test
	public void testMakePaymentToPlayer(){
		int payment=50;
		int currentMoney=testerPlayer.getMoney();
		Player player=gameController.getPlayers().get(1);
		int currentMoney2=player.getMoney();
		testerPlayer.makePayment(player, payment);
		assertEquals(currentMoney-50, testerPlayer.getMoney());
		assertEquals(currentMoney2+50, player.getMoney());
		assertTrue(testerPlayer.repOK());	
	}
	@Test
	public void testMakePaymentToBank(){
		int payment=50;
		int currentMoney=testerPlayer.getMoney();
		Bank bank=monopolyBoard.getBank();
		testerPlayer.makePayment(bank, payment);
		assertEquals(currentMoney-50, testerPlayer.getMoney());
		assertTrue(testerPlayer.repOK());	
	}
	
	@Test
	public void testReceivePayment(){
		int payment=50;
		int currentMoney=testerPlayer.getMoney();
		testerPlayer.receivePayment(player, payment);
		assertEquals(currentMoney+50, testerPlayer.getMoney());
		assertTrue(testerPlayer.repOK());	
	}
	@Test
	public void testBuySquareFromBank() {
		Bank bank=monopolyBoard.getBank();
		BuyableSquare testerSquare=monopolyBoard.getBank().getBuyableSquares().get(0);
		int payment=testerSquare.getPrice();
		testerPlayer.buySquare(bank, testerSquare, payment);
		//how do we test it 
		assertTrue(testerPlayer.repOK());
	}
	
	@Test
	public void testBuySquareToBank(){
		Player player=gameController.getPlayers().get(1);
		Bank bank=monopolyBoard.getBank();
		BuyableSquare testerSquare=monopolyBoard.getBank().getBuyableSquares().get(0);
		player.getSquares().add(testerSquare);
		int currentMoney=testerPlayer.getMoney();
		testerPlayer.buySquare(player, testerSquare, 50);
		//how do we test it to see if the transaction is completed without fault

		assertTrue(testerPlayer.repOK());
		assertEquals(testerPlayer.getMoney(), currentMoney-50);

	}
	@Test
	public void testSellSquareToPlayer(){
		Player player=gameController.getPlayers().get(1);
		Bank bank=monopolyBoard.getBank();
		BuyableSquare testerSquare=monopolyBoard.getBank().getBuyableSquares().get(0);
		testerPlayer.getSquares().add(testerSquare);
		int currentMoney=testerPlayer.getMoney();
		testerPlayer.sellSquare(player, testerSquare, 50);
		assertTrue(testerPlayer.repOK());
		assertEquals(testerPlayer.getMoney(), currentMoney+50);
		
	}
	@Test
	public void testBuyHouse(){
		BuyableSquare square=monopolyBoard.getBank().getBuyableSquares().get(0);
		int i=1;
		while(!(square instanceof ColorSquare) ){
			 square=monopolyBoard.getBank().getBuyableSquares().get(i);
			 i++;
		}
		testerPlayer.buyHouse((ColorSquare)square);
		assertTrue(testerPlayer.repOK());
	}
	
	@Test
	public void testBuyHotel(){
		BuyableSquare square=monopolyBoard.getBank().getBuyableSquares().get(0);
		int i=1;
		while(!(square instanceof ColorSquare) ){
			 square=monopolyBoard.getBank().getBuyableSquares().get(i);
			 i++;
		}
		testerPlayer.buyHouse((ColorSquare)square);
		testerPlayer.buyHouse((ColorSquare)square);
		testerPlayer.buyHouse((ColorSquare)square);
		testerPlayer.buyHotel((ColorSquare)square);
		assertTrue(testerPlayer.repOK());
	}
	
	@Test
	public void testBuySkyscraper(){
		BuyableSquare square=monopolyBoard.getBank().getBuyableSquares().get(0);
		int i=1;
		while(!(square instanceof ColorSquare) ){
			 square=monopolyBoard.getBank().getBuyableSquares().get(i);
			 i++;
		}
		
		testerPlayer.buySkyscraper((ColorSquare)square);
		assertTrue(testerPlayer.repOK());
	}
	
	
}
