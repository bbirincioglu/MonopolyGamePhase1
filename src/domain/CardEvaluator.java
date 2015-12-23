package domain;

import java.awt.Color;
import java.util.ArrayList;

public class CardEvaluator {
	private Player currentPlayer;
	
	public void evaluateCard(Player currentPlayer, Card card){
		String content = card.getContent();
		String[] actions = actions(content);
		String[] details = details(content);
		String action1 = actions[0];
		String action2 = (actions.length==2) ? actions[1] : null; 
		if(action1.equals("jail")){
			GoToJail jail=(GoToJail) GameController.getInstance().getMonopolyBoard().getOuterSquares().get(43);
			jail.landedOn(currentPlayer.getPiece());
		}else if (action1.equals("free")){
			currentPlayer.setInJail(false);
		}else if(action1.equals("advance all")){
			ArrayList<Player> players = GameController.getInstance().getPlayers();
			for (int i = 0; i < players.size(); i++) {
				players.get(i).move(GameController.getInstance().getMonopolyBoard().getOuterSquares().get(10));
			}
		}else if(action1.equals("advance")){
			if(details[0].equals("closest utility")){
				//currentPlayer.move(currentPlayer.getCurrentLocation().getClosestUtility());
			}else if(details[0].equals("closest railroad")){
				new NearestRailRoad().picked(currentPlayer);
			}else if (details[0].equals("3")){
				currentPlayer.move(3);
			}else if(details[0].equals("stock exchange")){
				currentPlayer.move(GameController.getInstance().getMonopolyBoard().getInnerSquares().get(13));
			}else if(details[0].equals("closest unowned")){
				Square closestOwned = GameController.getInstance().getClosestSquareToPayRent(currentPlayer);
				if(closestOwned!=null) currentPlayer.move(closestOwned);
				else currentPlayer.move(GameController.getInstance().getClosestSquareToBuy(currentPlayer));
			}else if(details[0].equals("roll one")){
				currentPlayer.move(GameController.getInstance().getMonopolyBoard().getMiddleSquares().get(31));
			}else if(details[0].equals("black and white cab")){
				currentPlayer.move(GameController.getInstance().getMonopolyBoard().getMiddleSquares().get(23));
			}else if(details[0].equals("current location")){
				currentPlayer.move(currentPlayer.getCurrentLocation());
			}else if(details[0].equals("tax refund")){
				currentPlayer.setMoney(currentPlayer.getMoney()+GameController.getInstance().getMonopolyBoard().getBank().getPoolMoney());
				GameController.getInstance().getMonopolyBoard().getBank().receivePayment(GameController.getInstance().getMonopolyBoard().getBank().getPoolMoney() * -1);
			}else if(details[0].equals("directly")){
				
			}
		}else if(action1.equals("pay") && action2==null){
			if(details[0].equals("per")){
				if(details[1].equals("25")){
					if(details[2].equals("house")){
						ArrayList<BuyableSquare> squares = currentPlayer.getSquares();
						int totalNum = 0;
						for (int i = 0; i < squares.size(); i++) {
							if(((ColorSquare) squares.get(i))!=null)
							totalNum+= ((ColorSquare) squares.get(i)).getBuildingNum();
						}
						currentPlayer.setMoney(currentPlayer.getMoney()-25*totalNum);
					}else if (details[2].equals("unmortgaged property")){
						currentPlayer.setMoney(currentPlayer.getMoney()-25*currentPlayer.getSquares().size());
					}else if (details[2].equals("40")){
						ArrayList<BuyableSquare> squares = currentPlayer.getSquares();
						int totalNum = 0;
						for (int i = 0; i < squares.size(); i++) {
							if(((ColorSquare) squares.get(i))!=null)
							totalNum+= ((ColorSquare) squares.get(i)).getBuildingNum();
						}
						currentPlayer.setMoney(currentPlayer.getMoney()-25*totalNum);
						
					}
				}
			}else if(details[0].equals("each")){
				ArrayList<Player> players = GameController.getInstance().getPlayers();
				for (int i = 0; i < players.size(); i++) {
					if(i!=GameController.getInstance().getCurrentPlayerIndex()){
						players.get(i).setMoney(players.get(i).getMoney()+50);
					}else 
						players.get(i).setMoney(players.get(i).getMoney()-50*7);
				}
			}else if(details[0].equals("50")){
				currentPlayer.setMoney(currentPlayer.getMoney()-50);
				GameController.getInstance().getMonopolyBoard().getBank().receivePayment(50);
			}else if(details[0].equals("150")){
				currentPlayer.setMoney(currentPlayer.getMoney()-150);
				GameController.getInstance().getMonopolyBoard().getBank().receivePayment(150);
			}
		}else if(action1.equals("downgrade")){
			ArrayList<BuyableSquare> s = currentPlayer.getSquares();
			for (int i = 0; i < s.size(); i++) {
				int bn = ((ColorSquare) s.get(i)).getBuildingNum();
				((ColorSquare) s.get(i)).setBuildingNum(bn-1);
			}
		}else if(action1.equals("collect")){
			if(details[0].equals("100")){
				currentPlayer.setMoney(currentPlayer.getMoney()+100);
			}else if(details[0].equals("150")){
				currentPlayer.setMoney(currentPlayer.getMoney()+150);
			}else if(details[0].equals("each")){
				ArrayList<Player> players = GameController.getInstance().getPlayers();
				for (int i = 0; i < players.size(); i++) {
					if(i!=GameController.getInstance().getCurrentPlayerIndex()){
						players.get(i).setMoney(players.get(i).getMoney()-10);
					}else 
						players.get(i).setMoney(players.get(i).getMoney()+10*7);
				}
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
}
