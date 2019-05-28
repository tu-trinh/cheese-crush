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
	
	public CheeseCrushCanvas(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		score = 0;
		moves = 60;
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
	}
	
	public void checkPress(int x, int y) {
		if (game) {
			if (x >= 500 && x <= 950 && y >= 50 && y <= 500) {
				clickRow = (y-50)/50;
				clickCol = (x-500)/50;
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
				releaseRow = (y-50)/50;
				releaseCol = (x-500)/50;
//				two = cheeseGrid[releaseRow][releaseCol];
				System.out.println("X: " + x + "\nY: " + y);
				System.out.println("Row: " + releaseRow + "\nCol: " + releaseCol);
//				System.out.println(two);
				swap(clickRow, clickCol, releaseRow, releaseCol);
			}
		}
		return;
	}
	
	private void swap(int unoRow, int unoCol, int dosRow, int dosCol) {
		if (game) {
			Cheese temp = cheeseGrid[unoRow][unoCol];
			System.out.println("Uno 1: " + cheeseGrid[unoRow][unoCol]);
			cheeseGrid[unoRow][unoCol] = cheeseGrid[dosRow][dosCol];
			System.out.println("Uno 2: " + cheeseGrid[unoRow][unoCol]);
			System.out.println("Dos 1: " + cheeseGrid[dosRow][dosCol]);
			cheeseGrid[dosRow][dosCol] = temp;
			System.out.println("Dos 2: " + cheeseGrid[dosRow][dosCol]);
			
			// next two lines: only if legit match
			moves--;
			repaint();
			
			// also how to call paintComponent again to edit wine rectangle??????????????????????????????
			
			// horizontal matches
			int horizCount = 0;
			int startCol = 0;
			for (int i = dosRow; i < dosRow + 1; i++) {
				Cheese cur = cheeseGrid[dosRow][0];
				for (int j = 0; j < cheeseGrid[0].length; j++) {
					if (cheeseGrid[i][j].equals(cur)) {
						horizCount++;
					} else {
						cur = cheeseGrid[i][j];
						startCol = j;
						horizCount = 0;
					}
				}
				switch (horizCount) {
					case 5:
						cheeseGrid[i][startCol] = null;
						cheeseGrid[i][startCol+1] = null;
						cheeseGrid[i][startCol+2] = null;
						cheeseGrid[i][startCol+3] = null;
						cheeseGrid[i][startCol+4] = null;
						repaint();
						break;
					case 4:
						// begone thot
						break;
					case 3:
						// begone thot
						break;
					default:
						// switch back, or just call swap in the cases and not here
						// call to paintComponent to say no can do buckaroo
				}
			}
			for (int i = unoRow; i < unoRow + 1; i++) {
				Cheese cur = cheeseGrid[unoRow][0];
				for (int j = 0; j < cheeseGrid[0].length; j++) {
					if (cheeseGrid[i][j].equals(cur)) {
						horizCount++;
					} else {
						cur = cheeseGrid[i][j];
						startCol = j;
						horizCount = 0;
					}
				}
				if (horizCount == 5) {
					// begone thot
				} else if (horizCount == 4) {
					// begone thot
				} else if (horizCount == 3) {
					// begone thot
				} else {
					// can't move!
				}
			}
			// vertical matches
			int vertCount = 0;
			for (int i = dosCol; i < dosCol + 1; i++) {
				Cheese cur = cheeseGrid[0][dosCol];
				for (int j = 0; j < cheeseGrid.length; j++) {
					if (cheeseGrid[i][j].equals(cur)) {
						vertCount++;
					} else {
						cur = cheeseGrid[i][j];
						vertCount = 0;
					}
				}
				if (vertCount == 5) {
					// begone thot
				} else if (vertCount == 4) {
					// begone thot
				} else if (vertCount == 3) {
					// begone thot
				} else {
					// can't move!
				}
			}
			for (int i = unoCol; i < unoCol + 1; i++) {
				Cheese cur = cheeseGrid[0][unoCol];
				for (int j = 0; j < cheeseGrid.length; j++) {
					if (cheeseGrid[i][j].equals(cur)) {
						vertCount++;
					} else {
						cur = cheeseGrid[i][j];
						vertCount = 0;
					}
				}
				if (vertCount == 5) {
					// begone thot
				} else if (vertCount == 4) {
					// begone thot
				} else if (vertCount == 3) {
					// begone thot
				} else {
					// can't move!
				}
			}
			if (moves == 0) {
				game = false;
			}
		}
		return;
	}
	
	public void reset() {
		score = 0;
		moves = 60;
		fillGrid(cheeseGrid);
		repaint();
	}
	
	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D)gr;
		
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
		g.setColor(wine);
//		g.fillRect(500, 450, 450, 50);
		g.drawLine(500, 500, 950, 500);
		
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
	}
	
	// fill grid with cheeses
	private void fillGrid(Cheese[][] grid) {
		// initial filling of grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = chooseRandom();
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 1; j < grid[0].length - 1; j++) {
				while (grid[i][j].equals(grid[i][j-1]) && grid[i][j].equals(grid[i][j+1])) {
					grid[i][j] = chooseRandom();
				}
			}
		}
		for (int i = 1; i < grid.length - 1; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				while (grid[i][j].equals(grid[i-1][j]) && grid[i][j].equals(grid[i+1][j])) {
					grid[i][j] = chooseRandom();
				}
			}
		}
		String rows = "";
		String cols = "";
		// supposed to run 9 times, why sometimes only 7 or 8?????????????????????????????????????????
		for (int k = 0; k < 9; k++) {
			int row = (int)(Math.random() * 9);
			int col = (int)(Math.random() * 9);
			if (rows.indexOf(row) == -1 && cols.indexOf(col) == -1) {
				rows += row;
				cols += col;
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
		int bottleCount = 0;
		for (Cheese[] row : grid) {
			for (Cheese chz : row) {
				if (chz.getStatus().equals("bottle")) {
					bottleCount++;
				}
			}
		}
		System.out.println(bottleCount);
		// subsequent fillings
//		for (int i = 1; i < grid.length - 1; i++) {
//			for (int j = 1; j < grid[0].length - 1; j++) {
//				// horizontal match (3)
//				if (grid[i][j].equals(grid[i][j-1]) && grid[i][j].equals(grid[i][j+1])) {
//					
//				}
//				
//				// vertical match (3)
//				if (grid[i][j].equals(grid[i-1][j]) && grid[i][j].equals(grid[i+1][j])) {
//					
//				}	
//			}
//		}
	}
	
	// choose a random cheese to put in grid
	private Cheese chooseRandom() {
		int choice = (int)(Math.random() * 12) + 1;
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
