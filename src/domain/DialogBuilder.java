package domain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MonopolyGame;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	
	public static String buildBuyOrAuctionDialog(Player currentPlayer) {
		String choice;
		BuyableSquare square = (BuyableSquare) currentPlayer.getCurrentLocation();
		
		JButton nameButton = new JButton("Name: " + square.getName());
		nameButton.setPreferredSize(new Dimension(100, 40));
		nameButton.setEnabled(false);
		
		JButton priceButton = new JButton("Price: $" + square.getPrice());
		priceButton.setPreferredSize(nameButton.getPreferredSize());
		priceButton.setEnabled(false);
		
		JPanel informationPanel = new JPanel();
		informationPanel.setLayout(new GridLayout(3, 1));
		informationPanel.add(nameButton);
		informationPanel.add(priceButton);
		
		class CheckBoxListener implements ActionListener {
			public CheckBoxListener() {
				
			}
			
			public void actionPerformed(ActionEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getSource();
				currentPlayer.setBargainSelected(checkBox.isSelected());
			}
		}
		
		if (currentPlayer.hasBargain()) {
			JCheckBox bbCheckBox = new JCheckBox();
			bbCheckBox.setText("Use Bargain Business (Pay only $100 to buy land)");
			bbCheckBox.addActionListener(new CheckBoxListener());
			informationPanel.add(bbCheckBox);
		}
		
		String centerText = "WHAT DO YOU WANT TO DO?";
		JLabel centerTextLabel = new JLabel(centerText);
		centerTextLabel.setFont(new Font("Serif", Font.BOLD, 16));
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.add(informationPanel, BorderLayout.NORTH);
		dialogPanel.add(centerTextLabel, BorderLayout.CENTER);
		
		String[] optionsAsString = new String[]{"Buy"};
		int result = JOptionPane.showOptionDialog(getMainFrame(), dialogPanel, "Buy Or Start Auction", JOptionPane.YES_NO_OPTION, 0, null, optionsAsString, null);
		
		if (result == 0) {
			choice = "Buy";
		} else {
			choice = "Auction";
		}
		
		return choice;
	}
	
	public static void setMainFrame(MonopolyGame mainFrame) {
		DialogBuilder.mainFrame = mainFrame;
	}
	
	public static MonopolyGame getMainFrame() {
		return mainFrame;
	}
}
