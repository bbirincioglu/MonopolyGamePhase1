import java.awt.Container;

import javax.swing.JLabel;

import domain.Piece;
import domain.PieceObserver;
import domain.Square;


public class PieceView extends JLabel implements PieceObserver {
	public PieceView(Piece piece) {
		super();
		piece.addPieceObserver(this);
		String playerName = piece.getOwner().getName();
		setText(playerName.substring(playerName.length() - 1, playerName.length()));
	}

	@Override
	public void update(Piece piece) {
		// TODO Auto-generated method stub
		Square square = piece.getCurrentLocation();
		String squareName = square.getName();
		MonopolyBoardView monopolyBoardView = findMonopolyBoardView();
		monopolyBoardView.findSquareView(squareName).addPieceView(this);
	}
	
	private MonopolyBoardView findMonopolyBoardView() {
		Container parent = getParent();
		
		while (!(parent instanceof MonopolyBoardView)) {
			parent = parent.getParent();
		}
		
		return (MonopolyBoardView) parent;
	}
}
