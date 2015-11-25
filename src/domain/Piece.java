package domain;

import java.util.ArrayList;

public class Piece {
	private ArrayList<PieceObserver> pieceObservers;
	private Player owner;
	private Square currentLocation;
	
	public Piece(Player owner) {
		setPieceObservers(new ArrayList<PieceObserver>());
		setOwner(owner);
		this.currentLocation = null;
	}
	
	public void move(int stepNum) {
		while (stepNum > 0) {
			move(getCurrentLocation().getNext());
			stepNum = stepNum - 1;
			
			if (stepNum > 0) {
				//getCurrentLocation().passedOn(this);
			}
			
			try {
				Thread.sleep(250);
			} catch (Exception e) {
				
			}
		}
		
		//getCurrentLocation().landedOn(this);
	}
	
	public void move(Square square) {
		if (getCurrentLocation() != null) {
			getCurrentLocation().removePiece(this);
		}
		
		setCurrentLocation(square);
		getCurrentLocation().addPiece(this);
	}
	
	public void notifyPieceObservers() {
		for (int i = 0; i < getPieceObservers().size(); i++) {
			PieceObserver pieceObserver = getPieceObservers().get(i);
			pieceObserver.update(this);
		}
	}
	
	public void addPieceObserver(PieceObserver pieceObserver) {
		getPieceObservers().add(pieceObserver);
	}

	public ArrayList<PieceObserver> getPieceObservers() {
		return pieceObservers;
	}

	public void setPieceObservers(ArrayList<PieceObserver> pieceObservers) {
		this.pieceObservers = pieceObservers;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Square getCurrentLocation() {
		return currentLocation;
	}

	private void setCurrentLocation(Square currentLocation) {
		this.currentLocation = currentLocation;
		notifyPieceObservers();
	}
}
