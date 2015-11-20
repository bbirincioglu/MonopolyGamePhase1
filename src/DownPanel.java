import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.CPObserver;
import domain.Die;
import domain.Player;


public class DownPanel extends JPanel {
	private GridBagConstraints constraints;
	private PlayersPanel playersPanel;
	private CPPanel cpPanel;
	private DicePanel diePanel;
	
	public DownPanel() {
		super();
		setConstraints(composeConstraints());
		setLayout(new GridLayout(1, 3));
		add(new CPPanel());
		add(new PlayersPanel());
		add(new DicePanel());
	}
	
	private GridBagConstraints composeConstraints() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		return constraints;
	}

	public GridBagConstraints getConstraints() {
		return constraints;
	}

	public void setConstraints(GridBagConstraints constraints) {
		this.constraints = constraints;
	}
	
	public class CPPanel extends JPanel implements CPObserver {
		private JLabel cpLabel;
		
		public CPPanel() {
			super();
			GameController gameController = GameController.getInstance();
			gameController.addCPObserver(this);
			this.cpLabel = new JLabel(gameController.getPlayers().get(0).getName());
		}

		@Override
		public void update(String name) {
			// TODO Auto-generated method stub
			getCpLabel().setText(name);
		}

		public JLabel getCpLabel() {
			return cpLabel;
		}

		public void setCpLabel(JLabel cpLabel) {
			this.cpLabel = cpLabel;
		}
	}
	
	public class PlayersPanel extends JPanel {
		private static final int ROW_NUM = 2;
		private static final int COLUMN_NUM = 4;
		
		public PlayersPanel() {
			super();
			setLayout(new GridLayout(ROW_NUM, COLUMN_NUM));
			ArrayList<Player> players = GameController.getInstance().getPlayers();
			
			for (int i = 0; i < players.size(); i++) {
				Player player = players.get(i);
				PlayerView playerView = new PlayerView(player);
				add(playerView);
			}
		}
	}
	
	public class PlayerView extends JPanel {
		private static final int COMBO_BOX_WIDTH = 20;
		private static final int COMBO_BOX_HEIGHT = 20;
		private JLabel nameLabel;
		private JLabel moneyLabel;
		private ComboBoxPanel comboBoxPanel;
		
		public PlayerView(Player player) {
			super();
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setLayout(new BorderLayout());
			player.addPlayerObserver(this);
			initializeChildren(player.getName(), player.getMoney());
			addChildren();
		}
		
		private void initializeChildren(String name, int money) {
			setNameLabel(composeDefaultLabel(name));
			setMoneyLabel(composeDefaultLabel("$" + money));
			setComboBoxPanel(new ComboBoxPanel());
		}
		
		private JLabel composeDefaultLabel(String text) {
			JLabel label = new JLabel();
			label.setText(text);
			label.setFont(new Font("Sans serif", Font.BOLD, 10));
			label.setHorizontalAlignment(JLabel.CENTER);
			return label;
		}
		
		private void addChildren() {
			add(getNameLabel(), BorderLayout.SOUTH);
			add(getMoneyLabel(), BorderLayout.NORTH);
			add(getComboBoxPanel(), BorderLayout.CENTER);
		}

		public JLabel getNameLabel() {
			return nameLabel;
		}

		public void setNameLabel(JLabel nameLabel) {
			this.nameLabel = nameLabel;
		}

		public JLabel getMoneyLabel() {
			return moneyLabel;
		}

		public void setMoneyLabel(JLabel moneyLabel) {
			this.moneyLabel = moneyLabel;
		}
		
		public ComboBoxPanel getComboBoxPanel() {
			return comboBoxPanel;
		}

		public void setComboBoxPanel(ComboBoxPanel comboBoxPanel) {
			this.comboBoxPanel = comboBoxPanel;
		}
		
		private class ComboBoxPanel extends JPanel {
			private static final int ROW_NUM = 2;
			private static final int COLUMN_NUM = 4;
			private final String[] LABELS = {"COLORS", "UTILITIES", "CABS", "RAILROADS"};
			private ArrayList<SteppedComboBox> comboBoxes;
			
			public ComboBoxPanel() {
				super();
				setLayout(new GridLayout(2, 4));
				setComboBoxes(new ArrayList<SteppedComboBox>());
				
				for (int i = 0; i < ROW_NUM; i++) {
					for (int j = 0; j < COLUMN_NUM; j++) {
						if (i == 0) {
							JLabel label = composeDefaultLabel(LABELS[j]);
							add(label);
						} else {
							SteppedComboBox comboBox = new SteppedComboBox(new String[]{});
							comboBox.setName(LABELS[j]);
							comboBox.setPreferredSize(new Dimension(50, 10));
							comboBox.setPopupWidth(200);
							getComboBoxes().add(comboBox);
							add(comboBox);
						}
					}
				}
			}

			public ArrayList<SteppedComboBox> getComboBoxes() {
				return comboBoxes;
			}

			public void setComboBoxes(ArrayList<SteppedComboBox> comboBoxes) {
				this.comboBoxes = comboBoxes;
			}
		}
	}
	
	private class DicePanel extends JPanel {
		public DicePanel() {
			super();
			setLayout(new GridLayout(2, 2));
			GameController gameController = GameController.getInstance();
			Die die1 = gameController.getDie1();
			Die die2 = gameController.getDie2();
			Die speedDie = gameController.getSpeedDie();
			
			RDieView rDieView1 = new RDieView(die1);
			RDieView rDieView2 = new RDieView(die2);
			SDieView sDieView = new SDieView(speedDie);
			RollButton rollButton = new RollButton();
			
			add(rDieView1);
			add(rDieView2);
			add(sDieView);
			add(rollButton);
		}
	}
}
