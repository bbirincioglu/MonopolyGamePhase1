package gui;
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
		//int playerNum = GameOptions.getPlayerNum();
		
		setGameBoard(new GameBoard());
		setContentPane(getGameBoard());
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}
}
