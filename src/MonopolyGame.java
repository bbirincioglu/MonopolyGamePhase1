import java.util.ArrayList;

import javax.swing.JFrame;

import domain.GameOptions;
import domain.Reader;
import domain.Square;


public class MonopolyGame extends JFrame {
	private GameBoard gameBoard;
	
	public MonopolyGame() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ArrayList<Square> outerSquares = Reader.readSQ("outer.txt");
		ArrayList<Square> middleSquares = Reader.readSQ("middle.txt");
		ArrayList<Square> innerSquares = Reader.readSQ("inner.txt");
		GameOptions gameOptions = Reader.readGO("gameOptions.txt");
		int playerNum = gameOptions.getPlayerNum();
		setGameBoard(new GameBoard(outerSquares, middleSquares, innerSquares, playerNum));
		setContentPane(getGameBoard());
		pack();
		setVisible(true);
	}
	
	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}
}
