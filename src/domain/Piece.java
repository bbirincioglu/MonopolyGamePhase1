package domain;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Ali Furkan
 * Piece class is for the representation of the tools on the game board.
 * -tag todo:a:"To Do:" Superhuman the man of steel.
 * @Effects: Superhuman the man of steel.
 * @effects: Superhuman the man of steel.
 */
public class Piece {
	//@Overview: Piece is the moving tool of the player.
<<<<<<< Updated upstream
	
=======
>>>>>>> Stashed changes
	private ArrayList<PieceObserver> pieceObservers;
	private Player owner;
	private Square currentLocation;
	private String direction;
	
	/**
	 * Piece is the moving tool of the player.
	 * Constructor of the piece
<<<<<<< Updated upstream
	 * @requires owner is not null
	 * @effects creates the piece of the given owner
	 * @param owner The owner player of the piece
	 * 
=======
	 * @param owner
>>>>>>> Stashed changes
	 */
	
	public Piece(Player owner) {
		//@requires: owner is not null
<<<<<<< Updated upstream
		//@effects: initialize the piece for the given owner
		
=======
		//@effects: initialize the piece for the given owner 
>>>>>>> Stashed changes
		setPieceObservers(new ArrayList<PieceObserver>());
		setOwner(owner);
		setCurrentLocation(null);
		setDirection(Direction.CLOCKWISE);
	}
<<<<<<< Updated upstream
	
	/**
	 * Moves the piece on the board according the integer stepNum given
	 * @modifies this
	 * @effects The pieces location changes according to stepNum, moves one by one
	 * @param stepNum The number of steps wanted to go on the board
	 * @see move(Square) , moveImmediate(Square)
	 */
	
	public void move(int stepNum) {
		//@modifies: this
		//@effects: moves this according the stepNum given
=======
	/**
	 * moves this according the stepNum given
	 * @param stepNum
	 */
	public void move(int stepNum) {
		//@modifies: this
		//@effects: moves this according the stepNum given
		//DIRECTION CONTROL ETMEYI WHILE ICINE DE KOYARDIM. FAKAT TIME COMPLEXITY 2 KATINA CIKIYOR. BOYLESI DAHA IYI.
>>>>>>> Stashed changes
		
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
<<<<<<< Updated upstream
	
	/**
	 * Moves the piece to the given square.
	 * @requires square is not null
	 * @modifies this
	 * @effects The pieces location becomes the square given, moves one by one
	 * @param square The destination square
	 * @see move(int),moveImmediate(Square)
	 */
	
=======
	/**
	 * moves this to the square.
	 * @param square
	 */
>>>>>>> Stashed changes
	public void move(Square square) {
		//@requires: square is not null 
		//@modifies: this
		//@effects: moves this to the square.
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	
	/**
	 * Moves the piece to the square directly without moving one by one
	 * @requires square is not null
	 * @modifies This
	 * @effects Changes the location of the piece directly, without moving one by one
	 * @param square The destination square
	 * @see move(int),move(Square)
	 */
	
=======
	/**
	 * moves this to the square directly
	 * @param square
	 */
>>>>>>> Stashed changes
	public void moveImmediate(Square square) {
		//@requires: square is not null 
		//@modifies: this
		//@effects: moves this to the square directly.
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
		if (getCurrentLocation() != null) {
			getCurrentLocation().removePiece(this);
		}
		
		setCurrentLocation(square);
		getCurrentLocation().addPiece(this);
	}
	/**
	 * notifies the observer of the piece
	 */
	
	/**
	 * Notifies the observer of the piece so that
	 * the pieces on the gui responds to the changes in the domain
	 */
	
	public void notifyPieceObservers() {
		//@effects: notifies the observer of the piece
<<<<<<< Updated upstream
		
=======

>>>>>>> Stashed changes
		for (int i = 0; i < getPieceObservers().size(); i++) {
			PieceObserver pieceObserver = getPieceObservers().get(i);
			pieceObserver.update(this);
		}
	}
<<<<<<< Updated upstream
	
	/**
	 * Adds pieceObserver to the observers of the piece.
	 * @requires pieceObserver != null && pieceObservers != null 
	 * @modifies this
	 * @effects adds the parameter to the observer list
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
	 * @return pieceObservers list
	 */
	
	public ArrayList<PieceObserver> getPieceObservers() {
		//@effects: returns the observers of the piece.
		
		return pieceObservers;
	}
	
	/**
	 * sets pieceObserver as the observers of the piece.
	 * @modifies: this
	 * @effects: sets pieceObserver as the observers of the piece.
	 * @param pieceObservers list
	 */

	public void setPieceObservers(ArrayList<PieceObserver> pieceObservers) {
		//@modifies: this
		//@effects: sets pieceObserver as the observers of the piece.
		
		this.pieceObservers = pieceObservers;
	}
	
	/**
	 * returns the owner of the piece.
	 * @effects: returns the owner of the piece.
	 * @return the owner player
	 */

	public Player getOwner() {
		//@effects: returns the owner of the piece.
		
		return owner;
	}
	
	/**
	 * sets owner as the owner of the piece.
	 * @modifies: this
	 * @effects: sets owner as the owner of the piece.
	 * @param owner the new owner player
	 */
	
	public void setOwner(Player owner) {
=======
	/**
	 * adds pieceObserver to the observers of the piece.
	 * @param pieceObserver
	 */
	public void addPieceObserver(PieceObserver pieceObserver) {
		//@requires: pieceObserver is not null 
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
		//@requires: pieceObserver is not null 
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
		//@requires: owner is not null 
>>>>>>> Stashed changes
		//@modifies: this
		//@effects: sets owner as the owner of the piece.
		this.owner = owner;
	}
<<<<<<< Updated upstream
	
	/**
	 * returns the current location of the piece.
	 * @effects: returns the current location of the piece.
	 * @return the current location 
	 */

	public Square getCurrentLocation() {
		//@effects: returns the current location of the piece.
		
		return currentLocation;
	}
	
	/**
	 * sets currentLocation as the current location of the piece.
	 * @modifies: this
	 * @effects: sets currentLocation as the current location of the piece.
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
	 * @modifies: this
	 * @effects: sets direction as the movement direction of the piece.
	 * @param direction
	 * @see Direction
	 */
	
	public void setDirection(String direction) {
		//@modifies: this
		//@effects: sets direction as the movement direction of the piece.
		
=======
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
		//@requires: currentLocation is not null 
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
		//@requires: direction is not null 
		//@modifies: this
		//@effects: sets direction as the movement direction of the piece.
>>>>>>> Stashed changes
		this.direction = direction;
	}
	/**
	 * returns the movement direction of the piece.
	 * @return
	 */
	
	/**
	 * returns the movement direction of the piece.
	 * @effects: returns the movement direction of the piece.
	 * @return the current direction
	 * @see Direction
	 */
	
	public String getDirection() {
		//@effects: returns the movement direction of the piece.
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
		return direction;
	}
	public JSONObject toJSON() throws JSONException{
		JSONObject js = new JSONObject();
		js.put("pieceObversers", getPieceObservers());
		js.put("owner",getOwner());
		js.put("currentLocation", getCurrentLocation());
		js.put("direction", getDirection());
		return js;
	}
	
	public String toString(){
		try {
			return toJSON().toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public boolean repOk(){
		if(getDirection()==null || getOwner()==null || getPieceObservers()== null)
			return false;
		return true;
	}
	
	
	public class Direction {
		//@overview:Subclass of piece which defines the movement direction of piece as either clockwise or counter_clockwise.
		public static final String CLOCKWISE = "CLOCKWISE";
		public static final String COUNTER_CLOCKWISE = "COUNTER_CLOCKWISE";
	}
}