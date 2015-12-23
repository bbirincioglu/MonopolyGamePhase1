package domain;

import java.util.ArrayList;

public class Piece {
	//@Overview: Piece is the moving tool of the player.
	
	private ArrayList<PieceObserver> pieceObservers;
	private Player owner;
	private Square currentLocation;
	private String direction;
	
	/**
	 * Piece is the moving tool of the player.
	 * Constructor of the piece
	 * @param owner
	 */
	
	public Piece(Player owner) {
		//@requires: owner is not null
		//@effects: initialize the piece for the given owner
		
		setPieceObservers(new ArrayList<PieceObserver>());
		setOwner(owner);
		setCurrentLocation(null);
		setDirection(Direction.CLOCKWISE);
	}
	
	/**
	 * moves this according the stepNum given
	 * @param stepNum
	 */
	
	public void move(int stepNum) {
		//@modifies: this
		//@effects: moves this according the stepNum given
		
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
	
	/**
	 * moves this to the square.
	 * @param square
	 */
	
	public void move(Square square) {
		//@requires: square is not null 
		//@modifies: this
		//@effects: moves this to the square.
		
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
	
	/**
	 * moves this to the square directly
	 * @param square
	 */
	
	public void moveImmediate(Square square) {
		//@requires: square is not null 
		//@modifies: this
		//@effects: moves this to the square directly.
		
		if (getCurrentLocation() != null) {
			getCurrentLocation().removePiece(this);
		}
		
		setCurrentLocation(square);
		getCurrentLocation().addPiece(this);
	}
	
	/**
	 * notifies the observer of the piece
	 */
	
	public void notifyPieceObservers() {
		//@effects: notifies the observer of the piece
		
		for (int i = 0; i < getPieceObservers().size(); i++) {
			PieceObserver pieceObserver = getPieceObservers().get(i);
			pieceObserver.update(this);
		}
	}
	
	/**
	 * adds pieceObserver to the observers of the piece.
	 * @param pieceObserver
	 */
	
	public void addPieceObserver(PieceObserver pieceObserver) {
		//@requires: pieceObserver != null && pieceObservers != null 
		//@modifies: this
		//@effects: adds pieceObserver to the observers of the piece.
		
		getPieceObservers().add(pieceObserver);
	}
	
	/**
	 * returns the observers of the piece.
	 * @return
	 */
	
	public ArrayList<PieceObserver> getPieceObservers() {
		//@effects: returns the observers of the piece.
		
		return pieceObservers;
	}
	
	/**
	 * sets pieceObserver as the observers of the piece.
	 * @param pieceObservers
	 */

	public void setPieceObservers(ArrayList<PieceObserver> pieceObservers) {
		//@modifies: this
		//@effects: sets pieceObserver as the observers of the piece.
		
		this.pieceObservers = pieceObservers;
	}
	
	/**
	 * returns the owner of the piece.
	 * @return
	 */

	public Player getOwner() {
		//@effects: returns the owner of the piece.
		
		return owner;
	}
	
	/**
	 * sets owner as the owner of the piece.
	 * @param owner
	 */
	
	public void setOwner(Player owner) {
		//@modifies: this
		//@effects: sets owner as the owner of the piece.
		this.owner = owner;
	}
	
	/**
	 * returns the current location of the piece.
	 * @return
	 */

	public Square getCurrentLocation() {
		//@effects: returns the current location of the piece.
		
		return currentLocation;
	}
	
	/**
	 * sets currentLocation as the current location of the piece.
	 * @param currentLocation 
	 * @requires currentLocation is not null 
	 */

	private void setCurrentLocation(Square currentLocation) {
		//@modifies: this
		//@effects: sets currentLocation as the current location of the piece.
		
		this.currentLocation = currentLocation;
		notifyPieceObservers();
	}
	
	/**
	 * sets direction as the movement direction of the piece.
	 * @param direction
	 */
	
	public void setDirection(String direction) {
		//@modifies: this
		//@effects: sets direction as the movement direction of the piece.
		
		this.direction = direction;
	}
	
	/**
	 * returns the movement direction of the piece.
	 * @return
	 */
	
	public String getDirection() {
		//@effects: returns the movement direction of the piece.
		return direction;
	}
	
	public class Direction {
		public static final String CLOCKWISE = "CLOCKWISE";
		public static final String COUNTER_CLOCKWISE = "COUNTER_CLOCKWISE";
	}
}
