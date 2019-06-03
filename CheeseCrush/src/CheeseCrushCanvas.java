// six cheeses: red leicester, orange cheddar, yellow camembert, green cherni vit, blue gorgonzola, purple ricotta
// normal combo 60 pt
// striped 120 pt
// wrapped 200 pt
// string cheese 120 pt -- perhaps NAW
// indv elimination 60 pt
// bomb 200 pt
// color bomb 280 pt
// any blocker 20 pt
// bottle 0 pt
// LEVEL 5, LEVEL 41 (replace w/ chocolates)

// TO DO U SON OF A BITCH
// FIX WHY STRIPES AIN'T SHOWING UP
// DO T WRAPS
// ACTIVATE STRIPES
// ACTIVATE WRAPPED
// ACTIVATE BOMBS
// IMPLEMENT REPEATED CLEARBOARDSSSSSSS
// IMPLEMENT SCORING
// ADD YOUR SCORE IS TO WIN
// IF TIME, SHUFFLE

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class CheeseCrushCanvas extends JComponent {
	private final int WIDTH;
	private final int HEIGHT;
	private Cheese[][] cheeseGrid;
	private int score;
	private int moves;
	private boolean game;
	private Color grayBlue;
	private Color grayerBlue;
	private Color bluerGray;
	private Color wine;
//	private Cheese one;
//	private Cheese two;
	private int clickRow;
	private int clickCol;
	private int releaseRow;
	private int releaseCol;
	private ArrayList<String> matchRows;
	private ArrayList<String> matchCols;
	private int wineHeight;
	private int wineY;
	private int winWidth;
	private int winHeight;
	private String winMsg;

	public CheeseCrushCanvas(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		score = 0;
		moves = 30;
		game = true;
		grayBlue = new Color(178, 201, 216);
		grayerBlue = new Color(178, 195, 206);
		bluerGray = new Color(148, 145, 255);
		wine = new Color(208, 83, 127);
		cheeseGrid = new Cheese[9][9];
		fillGrid(cheeseGrid);
//		one = null;
//		two = null;
		clickRow = 0;
		clickCol = 0;
		releaseRow = 0;
		releaseCol = 0;
		matchRows = new ArrayList<String>();
		matchCols = new ArrayList<String>();
		wineHeight = 4; // +50 if bottle
		wineY = 498; // -50 if bottle
		winWidth = 0;
		winHeight = 0;
		winMsg = "";
	}

	public void checkPress(int x, int y) {
		if (game) {
			if (x >= 500 && x <= 950 && y >= 50 && y <= 500) {
				clickRow = (y - 50) / 50;
				clickCol = (x - 500) / 50;
//				one = cheeseGrid[clickRow][clickCol];
				System.out.println("X: " + x + "\nY: " + y);
				System.out.println("Row: " + clickRow + "\nCol: " + clickCol);
//				System.out.println(one);
			}
		}
		return;
	}

	public void checkRelease(int x, int y) {
		if (game) {
			if (x >= 500 && x <= 950 && y >= 50 && y <= 500) {
				releaseRow = (y - 50) / 50;
				releaseCol = (x - 500) / 50;
//				two = cheeseGrid[releaseRow][releaseCol];
				System.out.println("X: " + x + "\nY: " + y);
				System.out.println("Row: " + releaseRow + "\nCol: " + releaseCol);
//				System.out.println(two);
				testSwap(clickRow, clickCol, releaseRow, releaseCol);
				count(clickRow, clickCol, releaseRow, releaseCol);
//				swap(clickRow, clickCol, releaseRow, releaseCol);
			}
		}
		return;
	}

	private void testSwap(int unoRow, int unoCol, int dosRow, int dosCol) {
		if (moves == 0) {
			game = false;
			gameOver();
		}
		if (game && (((unoRow == dosRow + 1 || unoRow == dosRow - 1) && unoCol == dosCol)
				|| ((unoCol == dosCol + 1 || unoCol == dosCol - 1)) && unoRow == dosRow)) {
			swap(unoRow, unoCol, dosRow, dosCol);
		}
	}
	// if ((cheeseGrid[unoRow][unoCol].equals(cheeseGrid[unoRow][unoCol-1]) && cheeseGrid[unoRow][unoCol].equals(cheeseGrid[unoRow][unoCol+1])) || (cheeseGrid[unoRow][unoCol].equals(cheeseGrid[unoRow-1][unoCol]) && cheeseGrid[unoRow][unoCol].equals(cheeseGrid[unoRow+1][unoCol])) || (cheeseGrid[dosRow][dosCol].equals(cheeseGrid[dosRow][dosCol-1]) && cheeseGrid[dosRow][dosCol].equals(cheeseGrid[dosRow][dosCol+1]) && (cheeseGrid[dosRow][dosCol].equals(cheeseGrid[dosRow-1][dosCol]) && cheeseGrid[dosRow][dosCol].equals(cheeseGrid[dosRow+1][dosCol]))))

	private void swap(int unoRow, int unoCol, int dosRow, int dosCol) {
		Cheese temp = cheeseGrid[unoRow][unoCol];
		System.out.println("Uno 1: " + cheeseGrid[unoRow][unoCol]);
		cheeseGrid[unoRow][unoCol] = cheeseGrid[dosRow][dosCol];
		System.out.println("Uno 2: " + cheeseGrid[unoRow][unoCol]);
		System.out.println("Dos 1: " + cheeseGrid[dosRow][dosCol]);
		cheeseGrid[dosRow][dosCol] = temp;
		System.out.println("Dos 2: " + cheeseGrid[dosRow][dosCol]);
		
		moves--;
		repaint();
	}

	public void reset() {
		score = 0;
		moves = 60;
		winWidth = 0;
		winHeight = 0;
		winMsg = "";
		wineY = 498;
		wineHeight = 4;
		fillGrid(cheeseGrid);
		repaint();
	}

	private void count(int unoRow, int unoCol, int dosRow, int dosCol) {
//		unoRow = (Integer)unoRow != null ? unoRow : 0;
//		unoCol = (Integer)unoCol != null ? unoCol : 0;
//		dosRow = (Integer)dosRow != null ? dosRow : 0;
//		dosCol = (Integer)dosCol != null ? dosCol : 0;
		int horizCount = 0;
		int vertCount = 0;
		Cheese startCheese;
		int startCol;
		int startRow;
		matchRows.clear();
		matchCols.clear();

		// clickRow
		horizCount = 0;
		startCheese = cheeseGrid[unoRow][0];
		startCol = 0;
		String matchPair = "";
		System.out.println("Starting cheese: " + startCheese);
		for (int j = 0; j < cheeseGrid[0].length; j++) {
			if (cheeseGrid[unoRow][j].equals(startCheese)) {
				horizCount++;
				System.out.println("Horiz Count: " + horizCount);
				if (j < cheeseGrid[0].length - 1) {
					if (horizCount >= 3 && !cheeseGrid[unoRow][j+1].equals(cheeseGrid[unoRow][j])) {
						startCol = j - (horizCount - 1);
						matchPair = "" + unoRow + startCol + horizCount;
						if (matchRows.indexOf(matchPair) == -1) {
							matchRows.add(matchPair);
						}
					}
				} else if (j == cheeseGrid[0].length - 1) {
					if (horizCount >= 3) {
						startCol = j - (horizCount - 1);
						matchPair = "" + unoRow + startCol + horizCount;
						if (matchRows.indexOf(matchPair) == -1) {
							matchRows.add(matchPair);
						}
					}
				}
			} else {
				horizCount = 1;
				startCheese = cheeseGrid[unoRow][j];
				System.out.println("Start Cheese: " + startCheese);
			}
			System.out.println("Match Pair: " + matchPair);
		}
		
		// releaseRow
		horizCount = 0;
		startCheese = cheeseGrid[dosRow][0];
		startCol = 0;
		matchPair = "";
		System.out.println("Starting cheese: " + startCheese);
		for (int j = 0; j < cheeseGrid[0].length; j++) {
			if (cheeseGrid[dosRow][j].equals(startCheese)) {
				horizCount++;
				System.out.println("Horiz Count: " + horizCount);
				if (horizCount >= 3) {
					startCol = j - (horizCount - 1);
					matchPair = "" + dosRow + startCol + horizCount;
					if (matchRows.indexOf(matchPair) == -1)
						matchRows.add(matchPair);
				}
			} else {
				horizCount = 1;
				startCheese = cheeseGrid[dosRow][j];
				System.out.println("Start Cheese: " + startCheese);
			}
			System.out.println("Match pair: " + matchPair);
		}
		System.out.println("Rows with matches: " + matchRows);
		
		// clickCol
		vertCount = 0;
		startCheese = cheeseGrid[unoCol][0];
		startRow = 0;
		matchPair = "";
		System.out.println("Starting cheese: " + startCheese);
		for (int i = 0; i < cheeseGrid.length; i++) {
			if (cheeseGrid[i][unoCol].equals(startCheese)) {
				vertCount++;
				System.out.println("Vert Count: " + vertCount);
				if (vertCount >= 3) {
					startRow = i - (vertCount - 1);
					matchPair = "" + unoCol + startRow + vertCount;
					if (matchCols.indexOf(matchPair) == -1)
						matchCols.add(matchPair);
				}
			} else {
				vertCount = 1;
				startCheese = cheeseGrid[i][unoCol];
				System.out.println("Start Cheese: " + startCheese);
			}
			System.out.println("Match pair: " + matchPair);
		}
		System.out.println("Columns with matches: " + matchCols);
		
		// releaseCol
		vertCount = 0;
		startCheese = cheeseGrid[dosCol][0];
		startRow = 0;
		matchPair = "";
		System.out.println("Starting cheese: " + startCheese);
		for (int i = 0; i < cheeseGrid.length; i++) {
			if (cheeseGrid[i][dosCol].equals(startCheese)) {
				vertCount++;
				System.out.println("Vert Count: " + vertCount);
				if (vertCount >= 3) {
					startRow = i - (vertCount - 1);
					matchPair = "" + dosCol + startRow + vertCount;
					if (matchCols.indexOf(matchPair) == -1)
						matchCols.add(matchPair);
				}
			} else {
				vertCount = 1;
				startCheese = cheeseGrid[i][dosCol];
				System.out.println("Start Cheese: " + startCheese);
			}
			System.out.println("Match pair: " + matchPair);
		}
		System.out.println("Columns with matches: " + matchCols);
		
		clearBoard();
		// WHAATTTT the HecQ
//		count();
//		while (matchRows.size() != 0 || matchCols.size() != 0) {
//			clearBoard();
//			count();
//		}
	}
	
	// general match finding
	private void count() {
		// horizontal matches
		int horizCount = 0;
		int startCol = 0;
		Cheese startCheese = cheeseGrid[0][0];
		matchRows.clear();
		String matchPair = "";
		for (int i = 0; i < cheeseGrid.length; i++) {
			for (int j = 0; j < cheeseGrid[0].length; j++) {
				if (cheeseGrid[i][j].equals(startCheese)) {
					horizCount++;
					System.out.println("Horiz Count: " + horizCount);
					if (j < cheeseGrid[0].length - 1) {
						if (horizCount >= 3 && !cheeseGrid[i][j+1].equals(cheeseGrid[i][j])) {
							startCol = j - (horizCount - 1);
							matchPair = "" + i + startCol + horizCount;
							if (matchRows.indexOf(matchPair) == -1) {
								matchRows.add(matchPair);
							}
						}
					} else if (j == cheeseGrid[0].length - 1) {
						if (horizCount >= 3) {
							startCol = j - (horizCount - 1);
							matchPair = "" + i + startCol + horizCount;
							if (matchRows.indexOf(matchPair) == -1) {
								matchRows.add(matchPair);
							}
						}
					}
				} else {
					horizCount = 1;
					startCheese = cheeseGrid[i][j];
					System.out.println("Start Cheese: " + startCheese);
				}
			}
		}
		
		// vertical matches
		int vertCount = 0;
		startCheese = cheeseGrid[0][0];
		int startRow = 0;
		matchCols.clear();
		matchPair = "";
		for (int j = 0; j < cheeseGrid[0].length; j++) {
			for (int i = 0; i < cheeseGrid.length; i++) {
				if (cheeseGrid[i][j].equals(startCheese)) {
					vertCount++;
					System.out.println("Vert Count: " + vertCount);
					if (i < cheeseGrid.length - 1) {
						if (vertCount >= 3 && !cheeseGrid[i+1][j].equals(cheeseGrid[i][j])) {
							startRow = i - (vertCount - 1);
							matchPair = "" + i + startRow + vertCount;
							if (matchCols.indexOf(matchPair) == -1) {
								matchCols.add(matchPair);
							}
						}
					} else if (i == cheeseGrid.length - 1) {
						if (vertCount >= 3) {
							startRow = i - (vertCount - 1);
							matchPair = "" + i + startRow + vertCount;
							if (matchCols.indexOf(matchPair) == -1) {
								matchCols.add(matchPair);
							}
						}
					}
				} else {
					vertCount = 1;
					startCheese = cheeseGrid[i][j];
					System.out.println("Start Cheese: " + startCheese);
				}
			}
		}
	}
	
	private void clearBoard() {		
		// clear matches and change wine
		// wrapped cheeses
		for (String groo : matchRows) {
			for (String oop : matchCols) {
				if (groo.substring(1,2).equals(oop.substring(0,1)) && groo.substring(2,3).equals("3") && oop.substring(2,3).equals("3")) {
					String color = cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(oop.substring(0,1))].getColor();
					if (color.equals("red")) {
						cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(oop.substring(0,1))] = new Red("wrapped");
					} else if (color.equals("orange")) {
						cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(oop.substring(0,1))] = new Orange("wrapped");
					} else if (color.equals("yellow")) {
						cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(oop.substring(0,1))] = new Yellow("wrapped");
					} else if (color.equals("green")) {
						cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(oop.substring(0,1))] = new Green("wrapped");
					} else if (color.equals("blue")) {
						cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(oop.substring(0,1))] = new Blue("wrapped");
					} else if (color.equals("purple")) {
						cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(oop.substring(0,1))] = new Purple("wrapped");
					}
					for (int k = 1; k < Integer.parseInt(groo.substring(2,3)); k++) {
						cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(groo.substring(1,2)) + k] = new Blank("blank");
					}
					for (int k = 1; k < Integer.parseInt(oop.substring(2,3)); k++) {
						cheeseGrid[Integer.parseInt(oop.substring(1,2)) + k][Integer.parseInt(oop.substring(0,1))] = new Blank("blank");

					}
				}
			}
		}
		// rows for 3-5
		for (String grp : matchRows) {
			for (int k = 0; k < Integer.parseInt(grp.substring(2,3)); k++) {
				String color = cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2))].getColor();
				if (cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2)) + k].getStatus().equals("bottle")) {
					wineY -= 50;
					wineHeight += 50;
				}
				repaint();
				if (wineHeight == 454) {
					game = false;
					gameOver();
				}
				cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2)) + k] = new Blank("blank");
				if (Integer.parseInt(grp.substring(2,3)) == 4) {
					if (color.equals("red")) {
						cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2))] = new Red("vertical");	
					} else if (color.equals("orange")) {
						cheeseGrid[Integer.parseInt(grp.substring(0,1))][releaseCol] = new Orange("vertical");
					} else if (color.equals("yellow")) {
						cheeseGrid[Integer.parseInt(grp.substring(0,1))][releaseCol] = new Yellow("vertical");
					} else if (color.equals("green")) {
						cheeseGrid[Integer.parseInt(grp.substring(0,1))][releaseCol] = new Green("vertical");
					} else if (color.equals("blue")) {
						cheeseGrid[Integer.parseInt(grp.substring(0,1))][releaseCol] = new Blue("vertical");
					} else if (color.equals("purple")) {
						cheeseGrid[Integer.parseInt(grp.substring(0,1))][releaseCol] = new Purple("vertical");
					}
				} else if (Integer.parseInt(grp.substring(2,3)) == 5) {
					cheeseGrid[Integer.parseInt(grp.substring(0,1))][releaseCol] = new Cheese("bomb");
				}
			}
		}
		// columns for 3-5
		for (String grp : matchCols) {
			for (int k = 0; k < Integer.parseInt(grp.substring(2,3)); k++) {
				String color = cheeseGrid[Integer.parseInt(grp.substring(1,2))][Integer.parseInt(grp.substring(0,1))].getColor();
				if (cheeseGrid[Integer.parseInt(grp.substring(1,2)) + k][Integer.parseInt(grp.substring(0,1))].getStatus().equals("bottle")) {
					wineY -= 50;
					wineHeight += 50;
				}
				repaint();
				if (wineHeight == 454) {
					game = false;
					gameOver();
				}
				cheeseGrid[Integer.parseInt(grp.substring(1,2)) + k][Integer.parseInt(grp.substring(0,1))] = new Blank("blank");
				if (Integer.parseInt(grp.substring(2,3)) == 4) {
					if (color.equals("red")) {
						cheeseGrid[releaseRow][Integer.parseInt(grp.substring(0,1))] = new Red("horizontal");	
					} else if (color.equals("orange")) {
						cheeseGrid[releaseRow][Integer.parseInt(grp.substring(0,1))] = new Orange("horizontal");
					} else if (color.equals("yellow")) {
						cheeseGrid[releaseRow][Integer.parseInt(grp.substring(0,1))] = new Yellow("horizontal");
					} else if (color.equals("green")) {
						cheeseGrid[releaseRow][Integer.parseInt(grp.substring(0,1))] = new Green("horizontal");
					} else if (color.equals("blue")) {
						cheeseGrid[releaseRow][Integer.parseInt(grp.substring(0,1))] = new Blue("horizontal");
					} else if (color.equals("purple")) {
						cheeseGrid[releaseRow][Integer.parseInt(grp.substring(0,1))] = new Purple("horizontal");
					}
				} else if (Integer.parseInt(grp.substring(2,3)) == 5) {
					cheeseGrid[releaseRow][Integer.parseInt(grp.substring(0,1))] = new Cheese("bomb");
				}
			}
		}
		repaint();
		
		// cheeses drop down
		for (int j = 0; j < cheeseGrid[0].length; j++) {
			if (cheeseGrid[0][j].getColor().equals("blank")) {
				cheeseGrid[0][j] = chooseRandom();
			}
		}
		for (int i = 1; i < cheeseGrid.length; i++) {
			for (int j = 0; j < cheeseGrid[0].length; j++) {
				while (cheeseGrid[i][j].getColor().equals("blank")) {
					for (i = i; i >= 1; i--) {
						cheeseGrid[i][j] = cheeseGrid[i-1][j];
					}
					cheeseGrid[0][j] = chooseRandom();
				}
			}
		}
		repaint();
	}
	
	// end game
	private void gameOver() {
		if (wineHeight == 454) {
			winWidth = WIDTH;
			winHeight = HEIGHT;
			winMsg = "You've popped all the bottles!!!";
			repaint();
		} else if (moves == 0) {
			winWidth = WIDTH;
			winHeight = HEIGHT;
			winMsg = "You lose! Press \"r\" to try again!";
		}
	}

	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;

		// background
		g.setColor(grayerBlue);
		g.fillRect(500, 50, 450, 450);
		g.setColor(grayBlue);
		g.setStroke(new BasicStroke(3));
		for (int i = 500; i < 951; i += 50) {
			g.drawLine(i, 50, i, 500);
		}
		for (int i = 50; i < 501; i += 50) {
			g.drawLine(500, i, 950, i);
		}

		// wine level
		g.setColor(wine);
		g.fillRect(500, wineY, 450, wineHeight);

		// score + moves
		g.setColor(bluerGray);
		g.setFont(new Font("Dialog", Font.BOLD, 30));
		g.drawString("Score: " + score, 250, 270);
		g.drawString("Moves: " + moves, 250, 305);

		// title + instructions
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50));
		g.drawString("Cheese Crush!!!", 550, 555);
		g.drawString("Pop the wine bottles!", 505, 610);

		// cheese grid
		int x = 500;
		int y = 50;
		for (Cheese[] row : cheeseGrid) {
			for (Cheese chz : row) {
				g.drawImage(chz.getImg(), x, y, this);
				x += 50;
			}
			x = 500;
			y += 50;
		}
		
		// win screen
		g.setColor(wine);
		g.fillRect(0, 0, winWidth, winHeight);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		// TWEAKKKKK
		g.drawString(winMsg, 280, 320);
	}

	// fill grid with cheeses
	private void fillGrid(Cheese[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = chooseRandom();
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 1; j < grid[0].length - 1; j++) {
				while (grid[i][j].equals(grid[i][j - 1]) && grid[i][j].equals(grid[i][j + 1])) {
					grid[i][j] = chooseRandom();
				}
			}
		}
		for (int i = 1; i < grid.length - 1; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				while (grid[i][j].equals(grid[i - 1][j]) && grid[i][j].equals(grid[i + 1][j])) {
					grid[i][j] = chooseRandom();
				}
			}
		}
		// randomly scatter bottles
		for (int k = 0; k < 9; k++) {
			int row = (int) (Math.random() * 9);
			int col = (int) (Math.random() * 9);
			if (grid[row][col].getStatus().equals("bottle")) {
				k--;
			} else {
				String color = grid[row][col].getColor();
				if (color.equals("red")) {
					grid[row][col] = new Red("bottle");
				} else if (color.equals("orange")) {
					grid[row][col] = new Orange("bottle");
				} else if (color.equals("yellow")) {
					grid[row][col] = new Yellow("bottle");
				} else if (color.equals("green")) {
					grid[row][col] = new Green("bottle");
				} else if (color.equals("blue")) {
					grid[row][col] = new Blue("bottle");
				} else if (color.equals("purple")) {
					grid[row][col] = new Purple("bottle");
				}
			}
		}
	}

	// choose a random cheese to put in grid
	private Cheese chooseRandom() {
		int choice = (int) (Math.random() * 12) + 1;
		if (choice == 1 || choice == 2) {
			return new Red("normal");
		} else if (choice == 3 || choice == 4) {
			return new Orange("normal");
		} else if (choice == 5 || choice == 6) {
			return new Yellow("normal");
		} else if (choice == 7 || choice == 8) {
			return new Green("normal");
		} else if (choice == 9 || choice == 10) {
			return new Blue("normal");
		} else {
			return new Purple("normal");
		}
	}
}
