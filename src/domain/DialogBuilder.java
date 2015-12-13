package domain;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import gui.ComponentBuilder;
import gui.MonopolyGame;
import gui.SteppedComboBox;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogBuilder {
	private static MonopolyGame mainFrame;
	
	public DialogBuilder() {
	
	}
	
	public static void forcePlayerToSellPropertyToGetOutOfJailDialog(Player currentPlayer) {
		//new SellPropertyToGetOutOfJailDialog(getMainFrame(), currentPlayer);
	}
	
	public static void informativeDialog(String information) {
		JOptionPane.showMessageDialog(getMainFrame(), information);
	}
	
	public static int busDialog(int die1Value, int die2Value) {
		Integer[] options = new Integer[3];
		options[0] = die1Value;
		options[1] = die2Value;
		options[2] = die1Value + die2Value;
		
		int choice = JOptionPane.showOptionDialog(getMainFrame(), "Select A Value", "Bus Dialog", 0, 0, null, options, 0);
		return options[choice];
	}
	
	public static String buyOrAuctionDialog(Player currentPlayer, BuyableSquare square) {
		String choice;
		String squareName = square.getName();
		String squarePrice = "$" + square.getPrice();
		String playerName = currentPlayer.getName();
		String question = playerName.toUpperCase() + ", WHAT DO YOU WANT TO DO?";
		
		JButton squareNameButton = ComponentBuilder.composeDefaultButton(squareName, 200, 50, null, false);
		JButton squarePriceButton = ComponentBuilder.composeDefaultButton(squarePrice, 200, 50, null, false);
		JLabel questionLabel = ComponentBuilder.composeDefaultLabel(question);
		JPanel dialogPanel = ComponentBuilder.composeDummyContainer(new JComponent[]{squareNameButton, squarePriceButton, questionLabel}, new GridLayout(3, 1), null);
		
		String[] optionsAsString = new String[]{"Buy", "Auction"};
		int result = JOptionPane.showOptionDialog(getMainFrame(), dialogPanel, "Buy Or Start Auction", JOptionPane.YES_NO_OPTION, 0, null, optionsAsString, null);
		
		if (result == 0) {
			choice = "Buy";
		} else {
			choice = "Auction";
		}
		
		return choice;
	}
	
	public static int[] auctionDialog(ArrayList<Player> players, Object object) {
		int[] bids = new int[players.size()];
		
		class ButtonListener implements ActionListener {
			private int currentPlayerIndex;
			private ArrayList<JPanel> playerPanels;
			
			public ButtonListener() {
				this.currentPlayerIndex = 0;
				this.playerPanels = playerPanels;
			}
			
			public void setCurrentPlayerIndex(int currentPlayerIndex) {
				this.currentPlayerIndex = currentPlayerIndex;
			}
			
			public int getCurrentPlayerIndex() {
				return currentPlayerIndex;
			}
			
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				JPanel playerPanel = (JPanel) button.getParent();
				JTextField moneyTextField = (JTextField) playerPanel.getComponent(0);
				int bid = Integer.valueOf(moneyTextField.getText());
				bids[getCurrentPlayerIndex()] = bid;
				setCurrentPlayerIndex(getCurrentPlayerIndex() + 1);
				
				if (getCurrentPlayerIndex() == getPlayerPanels().size()) {
					disablePanel(getPlayerPanels().get(getCurrentPlayerIndex() - 1));
				} else {
					disableAllPanels();
					enablePanel(getPlayerPanels().get(getCurrentPlayerIndex()));
				}
			}
			
			public void disableAllPanels() {
				ArrayList<JPanel> playerPanels = getPlayerPanels();
				
				for (int i = 0; i < playerPanels.size(); i++) {
					disablePanel(playerPanels.get(i));
				}
			}
			
			public void enablePanel(JPanel panel) {
				int size = panel.getComponentCount();
				Component[] children = panel.getComponents();
				
				for (int i = 0; i < size; i++) {
					Component child = children[i];
					
					if (child instanceof JComponent) {
						((JComponent) child).setEnabled(true);
					}
				}
			}
			
			public void disablePanel(JPanel panel) {
				int size = panel.getComponentCount();
				Component[] children = panel.getComponents();
				
				for (int i = 0; i < size; i++) {
					Component child = children[i];
					
					if (child instanceof JComponent) {
						((JComponent) child).setEnabled(false);
					}
				}
			}
			
			public void setPlayerPanels(ArrayList<JPanel> playerPanels) {
				this.playerPanels = playerPanels;
				disableAllPanels();
				enablePanel(playerPanels.get(getCurrentPlayerIndex()));
			}
			
			public ArrayList<JPanel> getPlayerPanels() {
				return playerPanels;
			}
		}
		
		String name = null;
		String price = null;
		
		if (object instanceof BuyableSquare) {
			BuyableSquare square = (BuyableSquare) object;
			name = square.getName().toUpperCase();
			price = "$" + square.getPrice();
		} else if (object instanceof Stock){
			Stock stock = (Stock) object;
			name = stock.getCompanyName().toUpperCase();
			price = "$" + stock.getParValue();
		}
		
		JButton nameButton = ComponentBuilder.composeDefaultButton(name, 0, 0, null, false);
		JButton priceButton = ComponentBuilder.composeDefaultButton(price, 0, 0, null, false);
		JPanel nestedContainer = ComponentBuilder.composeDummyContainer(new JComponent[]{nameButton, priceButton}, new GridLayout(2, 1), null);
		ArrayList playerPanels = new ArrayList<JPanel>();
		
		ButtonListener buttonListener = new ButtonListener();
		
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			String playerName = player.getName();
			String playerMoney = "$" + player.getMoney();
			
			JLabel playerNameLabel = ComponentBuilder.composeDefaultLabel(playerName);
			JLabel playerMoneyLabel = ComponentBuilder.composeDefaultLabel(playerMoney);
			JButton bidButton = ComponentBuilder.composeDefaultButton("Make Bid", 0, 0, buttonListener, true);
			JTextField bidTextField = ComponentBuilder.composeDefaultTextField(40, 20, "0", null);
			playerPanels.add(ComponentBuilder.composeDummyContainer(new JComponent[]{bidTextField, bidButton, playerMoneyLabel, playerNameLabel}, new GridLayout(4, 1), null));
		}
		
		buttonListener.setPlayerPanels(playerPanels);
		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new BorderLayout());
		mainContainer.add(nestedContainer, BorderLayout.NORTH);
		
		nestedContainer = ComponentBuilder.composeDummyContainer(playerPanels, null, null);
		mainContainer.add(nestedContainer, BorderLayout.CENTER);
		JOptionPane.showMessageDialog(mainContainer, mainContainer);
		JDialog dialog = ComponentBuilder.composeDefaultDialog(mainContainer);
		return bids;
	}
	
	public static String pickAnUnownedSquareDialog(Bank bank) {
		String squareName = null;
		ArrayList<BuyableSquare> squares = bank.getBuyableSquares();
		String[] squareNames = new String[squares.size()];
		int size = squares.size();
		
		for (int i = 0; i < size; i++) {
			squareNames[i] = squares.get(i).getName();
		}
		
		SteppedComboBox comboBox = ComponentBuilder.composeDefaultSteppedComboBox(100, 20, 200, squareNames, squareNames[0], null);
		JLabel informativeLabel = ComponentBuilder.composeDefaultLabel("PLEASE PICK A SQUARE FOR AUCTION.");
		JPanel container = ComponentBuilder.composeDummyContainer(new JComponent[]{comboBox, informativeLabel}, new GridLayout(2, 1), null);
		JOptionPane.showMessageDialog(getMainFrame(), container);
		squareName = comboBox.getSelectedItem().toString();
		System.out.println(squareName);
		return squareName;
	}
	
	public static void setMainFrame(MonopolyGame mainFrame) {
		DialogBuilder.mainFrame = mainFrame;
	}
	
	public static MonopolyGame getMainFrame() {
		return mainFrame;
	}
}
