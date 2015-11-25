package domain;

import javax.swing.JOptionPane;

public class DialogBuilder {
	private static final MonopolyGame mainFrame;
	
	public DialogBuilder() {
	
	}
	
	public static void buildInformativeDialog(String information) {
		JOptionPane.showMessageDialog(parentComponent, information);
	}
}
