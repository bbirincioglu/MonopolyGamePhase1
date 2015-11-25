package gui;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import domain.MonopolyBoard;
import domain.Square;

public class GameBoard extends JPanel {
	private MonopolyBoardView monopolyBoardView;
	private DownPanel downPanel;
	
	public GameBoard() {
		super();
		setLayout(new GridBagLayout());
		
		//GameController gameController = GameController.getInstance();
		MonopolyBoard monopolyBoard = new MonopolyBoard();
		
		setMonopolyBoardView(new MonopolyBoardView(monopolyBoard));
		//setDownPanel(new DownPanel());
		
		add(getMonopolyBoardView(), getMonopolyBoardView().getConstraints());
		//add(getDownPanel(), getDownPanel().getConstraints());
	}

	public MonopolyBoardView getMonopolyBoardView() {
		return monopolyBoardView;
	}

	public void setMonopolyBoardView(MonopolyBoardView monopolyBoardView) {
		this.monopolyBoardView = monopolyBoardView;
	}

	public DownPanel getDownPanel() {
		return downPanel;
	}

	public void setDownPanel(DownPanel downPanel) {
		this.downPanel = downPanel;
	}
}
