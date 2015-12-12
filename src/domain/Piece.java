package domain;

import java.util.ArrayList;

public class Piece {
	private ArrayList<PieceObserver> pieceObservers;
	private Player owner;
	private Square currentLocation;
	private String direction;
	
	public Piece(Player owner) {
		setPieceObservers(new ArrayList<PieceObserver>());
		setOwner(owner);
		setCurrentLocation(null);
		setDirection(Direction.CLOCKWISE);
	}
	
	public void move(int stepNum) {
		//DIRECTION CONTROL ETMEYI WHILE ICINE DE KOYARDIM. FAKAT TIME COMPLEXITY 2 KATINA CIKIYOR. BOYLESI DAHA IYI.
		
		if (getDirection().equals(Direction.CLOCKWISE)) {
			while (stepNum > 0) {
				moveImmediate(getCurrentLocation().getNext());
				stepNum = stepNum - 1;
				
				if (stepNum > 0) {
					getCurrentLocation().passedOn(this);
				}
				
				try {
					Thread.sleep(250);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			while (stepNum > 0) {
				moveImmediate(getCurrentLocation().getPrevious());
				stepNum = stepNum - 1;
				
				if (stepNum > 0) {
					getCurrentLocation().passedOn(this);
				}
				
				try {
					Thread.sleep(250);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		getCurrentLocation().landedOn(this);
	}
	
	public void move(Square square) {
		if (getDirection().equals(Direction.CLOCKWISE)) {
			while (!getCurrentLocation().equals(square)) {
				moveImmediate(getCurrentLocation().getNext());
				
				if (!getCurrentLocation().equals(square)) {
					getCurrentLocation().passedOn(this);
				}
				
				try {
					Thread.sleep(250);
				} catch (Exception e) {
					
				}
			}
		} else {
			while (!getCurrentLocation().equals(square)) {
				moveImmediate(getCurrentLocation().getPrevious());
				
				if (!getCurrentLocation().equals(square)) {
					getCurrentLocation().passedOn(this);
				}
			}
		}
		
		getCurrentLocation().landedOn(this);
	}
	
	public void moveImmediate(Square square) {
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
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public class Direction {
		public static final String CLOCKWISE = "CLOCKWISE";
		public static final String COUNTER_CLOCKWISE = "COUNTER_CLOCKWISE";
	}
}
