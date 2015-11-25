package domain;

import java.util.ArrayList;

import org.json.JSONObject;


public abstract class Square {
	private String name;
	private Square next;
	private Square previous;
	private ArrayList<SquareObserver> squareObservers;
	private ArrayList<Piece> pieces;
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Square getNext() {
		return next;
	}
	public void setNext(Square next) {
		this.next = next;
	}
	public Square getPrevious() {
		return previous;
	}
	public void setPrevious(Square previous) {
		this.previous = previous;
	}
	public ArrayList<SquareObserver> getSquareObservers() {
		return squareObservers;
	}
	public void setSquareObservers(ArrayList<SquareObserver> squareObservers) {
		this.squareObservers = squareObservers;
	}
	public Square(String name){
		this.name=name;
		this.previous=null;
		this.next=null;
		this.squareObservers=new ArrayList<SquareObserver>();
	}
	public void addSquareObserver(SquareObserver squareObserver){
		this.squareObservers.add(squareObserver);
	}
	public void notifySquareObservers(){
		for(SquareObserver squareObserver : squareObservers){
			squareObserver.update(this);
		}
	}
	public abstract void landedOn(Piece piece);
	public abstract void passedOn(Piece piece);
	
	public void addPiece(Piece piece){
		pieces.add(piece);
	}
	public void removePiece(Piece piece){
		pieces.remove(piece);
	}
}
