import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class MonopolyBoard extends JPanel {
	public MonopolyBoard(ArrayList<String> outerSquares, ArrayList<String> middleSquares, ArrayList<String> innerSquares) {
		super();
		setLayout(new GridLayout(15, 15));
		int rowNum = 15;
		int columnNum = 15;
		
		int outerIndex = 0;
		int middleIndex = 0;
		int innerIndex = 0;
		
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < columnNum; j++) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(100, 50));
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				panel.setLayout(new BorderLayout());
				JLabel colorLabel = new JLabel();
				colorLabel.setBackground(Color.BLACK);
				
				if (i == 0 || i == rowNum - 1 || j == 0 || j == columnNum - 1) {
					String square = outerSquares.get(outerIndex);
					//panel.setText(square);
					panel.setBackground(Color.RED);
					
					if (i == 0) {
						JLabel label = new JLabel("Connecticut Avenue");
						Font font = new Font("Sans serif", Font.BOLD, 10);
						label.setFont(font);
						label.setOpaque(true);
						label.setBackground(Color.CYAN);
						//panel.setLayout(new GridBagLayout());
						panel.setLayout(new BorderLayout());
						GridBagConstraints constraints = new GridBagConstraints();
						constraints.fill = GridBagConstraints.SOUTH;
						
						JPanel colorPanel = new JPanel();
						colorPanel.setBackground(Color.CYAN);
						panel.add(colorPanel, BorderLayout.SOUTH);
						label = new JLabel("asdfasdfdasfadsf");
						label.setFont(font);
						label.setOpaque(true);
						label.setBackground(Color.MAGENTA);
						panel.add(label, BorderLayout.NORTH);
						
						JPanel piecePanel = new JPanel();
						piecePanel.setLayout(new GridLayout(2, 4));
						piecePanel.add(new JLabel("1"));
						piecePanel.add(new JLabel("2"));
						piecePanel.add(new JLabel("3"));
						piecePanel.add(new JLabel("4"));
						piecePanel.add(new JLabel("5"));
						piecePanel.add(new JLabel("6"));
						piecePanel.add(new JLabel("7"));
						piecePanel.add(new JLabel("8"));
						panel.add(piecePanel, BorderLayout.CENTER);
						//panel.add(new Button("center"), BorderLayout.CENTER);
						//panel.add(new JButton("north"), BorderLayout.NORTH);
					} else if (i == rowNum - 1) {
						
					} else if (j == 0) {
						panel.setLayout(new BorderLayout());
						
						JLabel label = new JLabel("Connecticut Avenue");
						Font font = new Font("Sans serif", Font.BOLD, 10);
						label.setUI(new VerticalLabelUI(true));
						label.setFont(font);
						label.setOpaque(true);
						label.setBackground(Color.CYAN);
						panel.add(label, BorderLayout.WEST);
						
						JPanel colorPanel = new JPanel();
						colorPanel.setBackground(Color.CYAN);
						JTextField field1 = new JTextField();
						field1.setText("1h");
						colorPanel.add(field1);
						panel.add(colorPanel, BorderLayout.EAST);
						
						JPanel piecePanel = new JPanel();
						piecePanel.setLayout(new GridLayout(2, 4));
						piecePanel.add(new JLabel("1"));
						piecePanel.add(new JLabel("2"));
						piecePanel.add(new JLabel("3"));
						piecePanel.add(new JLabel("4"));
						piecePanel.add(new JLabel("5"));
						piecePanel.add(new JLabel("6"));
						piecePanel.add(new JLabel("7"));
						piecePanel.add(new JLabel("8"));
						panel.add(piecePanel, BorderLayout.CENTER);
					} else if (j == columnNum - 1) {
						
					}
					
					outerIndex = outerIndex + 1;
				} else if (i == 1 || i == rowNum - 2 || j == 1 || j == columnNum - 2) {
					panel.setBackground(Color.GRAY);
				} else if (i == 2 || i == rowNum - 3 || j == 2 || j == columnNum - 3) {
					String square = middleSquares.get(middleIndex);
					panel.setBackground(Color.YELLOW);
					middleIndex = middleIndex + 1;
				} else if (i == 3 || i == rowNum - 4 || j == 3 || j == columnNum - 4) {
					panel.setBackground(Color.GRAY);
				} else if (i == 4 || i == rowNum - 5 || j == 4 || j == columnNum - 5) {
					String square = innerSquares.get(innerIndex);
					JLabel label = new JLabel("Connecticut Avenue");
					Font font = new Font("Sans serif", Font.BOLD, 10);
					label.setFont(font);
					panel.add(label, BorderLayout.SOUTH);
					panel.setBackground(Color.GREEN);
					innerIndex = innerIndex + 1;
				} else {
					panel.setBackground(Color.GRAY);
				}
				
				add(panel);
			}
		}
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPanel = (JPanel) frame.getContentPane();
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		contentPanel.add(this, constraints);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		JPanel playersPanel = new JPanel();
		playersPanel.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		SteppedComboBox comboBox = new SteppedComboBox(new String[]{"as;dlfkjas;dlfkjasdf, asdf, asdlfkjasdlfkjasdlfkjasldfkjs"});
		comboBox.addItem("adanali");
		comboBox.setPopupWidth(200);
		comboBox.setPreferredSize(new Dimension(25, 25));
		playersPanel.add(comboBox, constraints);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		playersPanel.add(new JComboBox(), constraints);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		playersPanel.add(new JComboBox(), constraints);
		playersPanel.setBackground(Color.ORANGE);
		constraints.gridx = 0;
		constraints.gridy = 3;
		playersPanel.add(new JComboBox(), constraints);
		playersPanel.setBackground(Color.ORANGE);
		
		contentPanel.add(playersPanel, constraints);
		//contentPanel.add(new JButton("asdf"));
		frame.pack();
		frame.setVisible(true);
	}
}
