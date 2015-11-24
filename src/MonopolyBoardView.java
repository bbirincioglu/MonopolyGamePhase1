import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Square;
import domain.SquareObserver;


public class MonopolyBoardView extends JPanel {
	private static final String[] LOCATIONS = {BorderLayout.SOUTH, BorderLayout.WEST, BorderLayout.NORTH, BorderLayout.EAST};
	private static final int ROW_NUM = 15;
	private static final int COLUMN_NUM = 15;
	private ArrayList<SquareView> outerSquareViews;
	private ArrayList<SquareView> middleSquareViews;
	private ArrayList<SquareView> innerSquareViews;
	
	private GridBagConstraints constraints;
	
	public MonopolyBoardView(ArrayList<Square> outerSquares, ArrayList<Square> middleSquares, ArrayList<Square> innerSquares) {
		super();
		setLayout(new GridBagLayout());
		setConstraints(composeConstraints());
		initializeChildren(outerSquares, middleSquares, innerSquares);
		addChildren();
	}
	
	private GridBagConstraints composeConstraints() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		return constraints;
	}
	
	private void addChildren() {
		ArrayList<SquareView> outerSquareViews = getOuterSquareViews();
		ArrayList<SquareView> middleSquareViews = getMiddleSquareViews();
		ArrayList<SquareView> innerSquareViews = getInnerSquareViews();
		
		int outerY = outerSquareViews.size() / 4;
		int outerX = outerY;
		int outerIndex = 0;
		int outerSeperator = outerY;
		
		int middleY = outerY - 2;
		int middleX = middleY;
		int middleIndex = 0;
		int middleSeperator = 10;
		
		int innerY = middleY - 2;
		int innerX = innerY;
		int innerIndex = 0;
		int innerSeperator = 6;
		
		for (int i = ROW_NUM - 1; i >= 0; i--) {
			for (int j = COLUMN_NUM - 1; j >= 0; j--) {
				SquareView squareView;
				GridBagConstraints constraints = new GridBagConstraints();
				
				if (i == 0 || i == ROW_NUM - 1 || j == 0 || j == COLUMN_NUM - 1) {
					squareView = outerSquareViews.get(outerIndex);
					constraints.gridx = outerX;
					constraints.gridy = outerY;
					//System.out.println(outerY + "," + outerX);
					if (0 <= outerIndex && outerIndex < outerSeperator) {
						outerX--;
					} else if (outerSeperator <= outerIndex && outerIndex < outerSeperator * 2) {
						outerY--;
					} else if (outerSeperator * 2 <= outerIndex && outerIndex < outerSeperator * 3) {
						outerX++;
					} else if (outerSeperator * 3 <= outerIndex && outerIndex < outerSeperator * 4) {
						outerY++;
					}
					
					outerIndex++;
					add(squareView, constraints);
				} else if (i == 1 || i == ROW_NUM - 2 || j == 1 || j == COLUMN_NUM - 2) {
					JPanel emptyPanel = new JPanel();
					emptyPanel.setBackground(Color.GRAY);
					emptyPanel.setPreferredSize(new Dimension(100, 50));
					constraints.gridx = j;
					constraints.gridy = i;
					add(emptyPanel, constraints);
				} else if (i == 2 || i == ROW_NUM - 3 || j == 2 || j == COLUMN_NUM - 3) {
					squareView = middleSquareViews.get(middleIndex);
					constraints.gridx = middleX;
					constraints.gridy = middleY;
					System.out.println(middleY + "," + middleX);
					if (0 <= middleIndex && middleIndex < middleSeperator) {
						middleX--;
					} else if (middleSeperator <= middleIndex && middleIndex < middleSeperator * 2) {
						middleY--;
					} else if (middleSeperator * 2 <= middleIndex && middleIndex < middleSeperator * 3) {
						middleX++;
					} else if (middleSeperator * 3 <= middleIndex && middleIndex < middleSeperator * 4) {
						middleY++;
					}
					
					middleIndex++;
					add(squareView, constraints);
				} else if (i == 3 || i == ROW_NUM - 4 || j == 3 || j == COLUMN_NUM - 4) {
					JPanel emptyPanel = new JPanel();
					emptyPanel.setBackground(Color.GRAY);
					emptyPanel.setPreferredSize(new Dimension(100, 50));
					constraints.gridx = j;
					constraints.gridy = i;
					add(emptyPanel, constraints);
				} else if (i == 4 || i == ROW_NUM - 5 || j == 4 || j == COLUMN_NUM - 5) {
					squareView = innerSquareViews.get(innerIndex);
					constraints.gridx = innerX;
					constraints.gridy = innerY;
					
					if (0 <= innerIndex && innerIndex < innerSeperator) {
						innerX--;
					} else if (innerSeperator <= innerIndex && innerIndex < innerSeperator * 2) {
						innerY--;
					} else if (innerSeperator * 2 <= innerIndex && innerIndex < innerSeperator * 3) {
						innerX++;
					} else if (innerSeperator * 3 <= innerIndex && innerIndex < innerSeperator * 4) {
						innerY++;
					}
					
					innerIndex++;
					add(squareView, constraints);
				} else {
					JPanel emptyPanel = new JPanel();
					emptyPanel.setBackground(Color.GRAY);
					emptyPanel.setPreferredSize(new Dimension(100, 50));
					constraints.gridx = j;
					constraints.gridy = i;
					add(emptyPanel, constraints);
				}	
			}
		}
		
		
	}
	
	private void initializeChildren(ArrayList<Square> outerSquares, ArrayList<Square> middleSquares, ArrayList<Square> innerSquares) {
		setOuterSquareViews(new ArrayList<SquareView>());
		setMiddleSquareViews(new ArrayList<SquareView>());
		setInnerSquareViews(new ArrayList<SquareView>());
		
		initialize(getOuterSquareViews(), outerSquares);
		initialize(getMiddleSquareViews(), middleSquares);
		initialize(getInnerSquareViews(), innerSquares);
	}
	
	private void initialize(ArrayList<SquareView> squareViews, ArrayList<Square> squares) {
		int size = squares.size();
		int seperator = size / 4;
		
		for (int i = 0; i < size; i++) {
			Square square = squares.get(i);
			String location = null;
			
			if (0 <= i && i < seperator) {
				location = LOCATIONS[0];
			} else if (seperator <= i && i < seperator * 2) {
				location = LOCATIONS[1];
			} else if (seperator * 2 <= i && i < seperator * 3) {
				location = LOCATIONS[2];
			} else if (seperator * 3 <= i && i < seperator * 4) {
				location = LOCATIONS[3];
			}
			
			SquareView squareView = new SquareView(square, location);
			squareViews.add(squareView);
		}
	}
	
	public ArrayList<SquareView> getOuterSquareViews() {
		return outerSquareViews;
	}

	public void setOuterSquareViews(ArrayList<SquareView> outerSquareViews) {
		this.outerSquareViews = outerSquareViews;
	}

	public ArrayList<SquareView> getMiddleSquareViews() {
		return middleSquareViews;
	}

	public void setMiddleSquareViews(ArrayList<SquareView> middleSquareViews) {
		this.middleSquareViews = middleSquareViews;
	}

	public ArrayList<SquareView> getInnerSquareViews() {
		return innerSquareViews;
	}

	public void setInnerSquareViews(ArrayList<SquareView> innerSquareViews) {
		this.innerSquareViews = innerSquareViews;
	}

	public static String[] getLocations() {
		return LOCATIONS;
	}

	public static int getRowNum() {
		return ROW_NUM;
	}

	public static int getColumnNum() {
		return COLUMN_NUM;
	}
	
	public class PiecePanel extends JPanel {
		public PiecePanel() {
			super();
			setLayout(new GridLayout(4, 2));
		}
		
		public void addPieceView(PieceView pieceView) {
			add(pieceView);
		}
		
		public void removePieceView(PieceView pieceView) {
			remove(pieceView);
		}
	}
	
	public class SquareView extends JPanel implements SquareObserver {
		private static final int WIDTH = 100;
		private static final int HEIGHT = 50;
		private JLabel nameLabel;
		private JLabel colorLabel;
		private PiecePanel piecePanel;
		
		public SquareView(Square square, String location) {
			super();
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setLayout(new BorderLayout());
			initializeChildren(square, location);
			addChildren(location);
		}
		
		private void addChildren(String location) {
			add(getNameLabel(), location);
			add(getColorLabel(), getOppositeLocation(LOCATIONS, location));
			add(getPiecePanel(), BorderLayout.CENTER);
		}
		
		private String getOppositeLocation(String[] locations, String location) {
			String oppositeLocation = null;
			
			for (int i = 0; i < locations.length; i++) {
				if (locations[i].equals(location)) {
					oppositeLocation = locations[(i + 2) % locations.length];
					break;
				}
			}
			
			return oppositeLocation;
		}
		
		private void initializeChildren(Square square, String location) {
			setNameLabel(new JLabel(square.getName()));
			Font font = new Font("Sans serif", Font.BOLD, 10);
			getNameLabel().setFont(font);
			getNameLabel().setHorizontalAlignment(JLabel.CENTER);
			setColorLabel(new JLabel("1home"));
			getColorLabel().setFont(font);
			getColorLabel().setHorizontalAlignment(JLabel.CENTER);
			setPiecePanel(new PiecePanel());
			
			if (location.equals(LOCATIONS[0])) {
				setBackground(Color.GREEN);
			} else if (location.equals(LOCATIONS[1])) {
				getNameLabel().setUI(new VerticalLabelUI(true));
				getColorLabel().setUI(new VerticalLabelUI(true));
				setBackground(Color.YELLOW);
			} else if (location.equals(LOCATIONS[2])) {
				setBackground(Color.GREEN);
			} else if (location.equals(LOCATIONS[3])) {
				getNameLabel().setUI(new VerticalLabelUI(false));
				getColorLabel().setUI(new VerticalLabelUI(false));
				setBackground(Color.YELLOW);
			}
		}
		
		public PiecePanel getPiecePanel() {
			return piecePanel;
		}

		public void setPiecePanel(PiecePanel piecePanel) {
			this.piecePanel = piecePanel;
		}

		public JLabel getNameLabel() {
			return nameLabel;
		}

		public void setNameLabel(JLabel nameLabel) {
			this.nameLabel = nameLabel;
		}

		public JLabel getColorLabel() {
			return colorLabel;
		}

		public void setColorLabel(JLabel colorLabel) {
			this.colorLabel = colorLabel;
		}
		
		public void addPieceView(PieceView pieceView) {
			getPiecePanel().addPieceView(pieceView);
		}
		
		public void removePieceView(PieceView pieceView) {
			getPiecePanel().removePieceView(pieceView);
		}

		@Override
		public void update(Square square) {
			// TODO Auto-generated method stub
			if (square instanceof ColorSquare) {
				ColorSquare colorSquare = (ColorSquare) square;
				int buildingNum = colorSquare.getBuildingNum();
				
				if (0 <= buildingNum && buildingNum <= 4) {
					getColorLabel().setText(buildNum + " h");
				} else if (buildingNum == 5){
					getColorLabel().setText((buildNum - 4) + " H");
				} else {
					getColorLabel().setText((buildNum - 5) + " S");
				}
			} else {
				
			}
		}
	}

	public GridBagConstraints getConstraints() {
		return constraints;
	}

	public void setConstraints(GridBagConstraints constraints) {
		this.constraints = constraints;
	}
	
	public SquareView findSquareView(String squareName) {
		SquareView squareView = null;
		squareView = searchSquareView(squareName, getOuterSquareViews());
		
		if (squareView == null) {
			squareView = searchSquareView(squareName, getMiddleSquareViews());
			
			if (squareView == null) {
				squareView = searchSquareView(squareName, getInnerSquareViews());
			}
		}
		
		return squareView;
	}
	
	private SquareView searchSquareView(String squareName, ArrayList<SquareView> squareViews) {
		SquareView wantedSquareView = null;
		
		for (int i = 0; i <squareViews.size(); i++) {
			SquareView squareView = squareViews.get(i);
			
			if (squareView.getNameLabel().getText().equals(squareName)) {
				wantedSquareView = squareView;
				break;
			}
		}
		
		return wantedSquareView;
	}
}
