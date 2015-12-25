package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {
	private GameController gameController=GameController.getInstance();
	private MonopolyBoard monopolyBoard=gameController.getMonopolyBoard();
	private Player testerPlayer=gameController.getPlayers().get(0);
	private Square testerSquare=monopolyBoard.getInnerSquares().get(5);
	
	@Test
	public void testRemovePiece() {
		testerSquare.addPiece(testerPlayer.getPiece());
		testerSquare.removePiece(testerPlayer.getPiece());
		assertTrue(testerSquare.repOK());
		
		fail("removePiece() method violates representation invariant for Square class");
	}
	public void testAddPiece(){
		testerSquare.addPiece(testerPlayer.getPiece());
		assertTrue(testerSquare.repOK());
		fail("addPiece() method violates representation invariant for Square class");
	}

}
