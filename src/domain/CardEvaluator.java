package domain;

import java.awt.Color;
import java.util.ArrayList;

public class CardEvaluator {
	private Player currentPlayer;
	
	private void evaluateCard(Card card){
		String content = card.getContent();
		String[] actions = actions(content);
		String[] details = details(content);
		String action1 = actions[0];
		String action2 = (actions.length==2) ? actions[1] : null; 
		if(action1.equals("jail")){
			GoToJail jail=(GoToJail) GameController.getInstance().getMonopolyBoard().getOuterSquares().get(43);
			jail.landedOn(getCurrentPlayer().getPiece());
		}else if (action1.equals("free")){
			getCurrentPlayer().setInJail(false);
		}else if(action1.equals("advance all")){
			ArrayList<Player> players = GameController.getInstance().getPlayers();
			for (int i = 0; i < players.size(); i++) {
				players.get(i).move(GameController.getInstance().getMonopolyBoard().getOuterSquares().get(10));
			}
		}else if(action1.equals("advance") && action2==null){
			if(details[0].equals("closest utility")){
				getCurrentPlayer().move(getCurrentPlayer().getCurrentLocation().getClosestUtility());
			}else if(details[0].equals("closest railroad")){
				new NearestRailRoad().picked(getCurrentPlayer());
			}else if (details[0].equals("3")){
				getCurrentPlayer().move(-3);
			}else if(details[0].equals("stock exchange")){
				getCurrentPlayer().move(GameController.getInstance().getMonopolyBoard().getInnerSquares().get(13));
			}else if(details[0].equals("closest unowned")){
				Square closestOwned = getCurrentPlayer().getCurrentLocation().getClosestOwned();
				if(closestOwned!=null) getCurrentPlayer().move(closestOwned);
				else getCurrentPlayer().move(getCurrentPlayer().getCurrentLocation().getClosestUnowned());
			}else if(details[0].equals("roll one")){
				getCurrentPlayer().move(GameController.getInstance().getMonopolyBoard().getMiddleSquares().get(31));
			}else if(details[0].equals("black and white cab")){
				getCurrentPlayer().move(GameController.getInstance().getMonopolyBoard().getMiddleSquares().get(23));
			}else if(details[0].equals("current location")){
				getCurrentPlayer().move(getCurrentPlayer().getCurrentLocation());
			}
		}else if(action1.equals("pay") && action2==null){
			if(details[0].equals("per")){
				if(details[1].equals("25")){
					if(details[2].equals("house")){
						ArrayList<BuyableSquare> squares = getCurrentPlayer().getSquares();
						int totalNum = 0;
						for (int i = 0; i < squares.size(); i++) {
							if(((ColorSquare) squares.get(i))!=null)
							totalNum+= ((ColorSquare) squares.get(i)).getBuildingNum();
						}
						getCurrentPlayer().setMoney(getCurrentPlayer().getMoney()-25*totalNum);
					}else if (details[2].equals("unmortgaged property")){
						getCurrentPlayer().setMoney(getCurrentPlayer().getMoney()-25*getCurrentPlayer().getSquares().size());
					}else if (details[2].equals("40")){
						ArrayList<BuyableSquare> squares = getCurrentPlayer().getSquares();
						int totalNum = 0;
						for (int i = 0; i < squares.size(); i++) {
							if(((ColorSquare) squares.get(i))!=null)
							totalNum+= ((ColorSquare) squares.get(i)).getBuildingNum();
						}
						getCurrentPlayer().setMoney(getCurrentPlayer().getMoney()-25*totalNum);
						
					}
				}
			}
		}else if(action1.equals("downgrade")){
			ArrayList<BuyableSquare> s = getCurrentPlayer().getSquares();
			for (int i = 0; i < s.size(); i++) {
				int bn = ((ColorSquare) s.get(i)).getBuildingNum();
				((ColorSquare) s.get(i)).setBuildingNum(bn-1);
			}
		}
			
			
	}
	private String[] actions(String content){
		String[] parts = content.split(".");
		String[] actions = new String[parts.length-1];
		for (int i = 0; i < actions.length; i++) {
			String[] descriptions = parts[i].split(",");
			actions[i]= descriptions[0];
		}
		return actions;
	}
	private String[] details(String content){
		String[] parts = content.split(".");
		String[] details = new String[parts.length-1];
		for (int i = 0; i < details.length; i++) {
			String part = parts[i];
			details[i]=part.substring(part.indexOf(",")+1,part.length());
		}
		return details;
	}
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
