import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import domain.Square;

public class GameBoard extends JPanel {
	private MonopolyBoardView monopolyBoardView;
	private DownPanel downPanel;
	
	public GameBoard(ArrayList<Square> outerSquares, ArrayList<Square> middleSquares, ArrayList<Square> innerSquares, int playerNum) {
		super();
		setLayout(new GridBagLayout());
		setMonopolyBoardView(new MonopolyBoardView(outerSquares, middleSquares, innerSquares));
		setDownPanel(new DownPanel());
		add(getMonopolyBoardView(), getMonopolyBoardView().getConstraints());
		add(getDownPanel(), getDownPanel().getConstraints());
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
