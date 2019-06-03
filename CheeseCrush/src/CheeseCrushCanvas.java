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
				clearBoard();
				// WHAATTTT the HecQ
//				count();
//				while (matchRows.size() != 0 || matchCols.size() != 0) {
//					clearBoard();
//					count();
//				}
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
		cheeseGrid[unoRow][unoCol] = cheeseGrid[dosRow][dosCol];
		cheeseGrid[dosRow][dosCol] = temp;
		
		moves--;
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
		int special = 0;
		int specRow = 0;
		int specCol = 0;
		String matchPair = "";
		for (int j = 0; j < cheeseGrid[0].length; j++) {
			if (cheeseGrid[unoRow][j].equals(startCheese)) {
				horizCount++;
				if (cheeseGrid[unoRow][j].getStatus().equals("wrapped")) {
					special = 1;
					specRow = unoRow;
					specCol = j;
				} else if (cheeseGrid[unoRow][j].getStatus().equals("horizontal")) {
					special = 2;
					specRow = unoRow;
					specCol = j;
				} else if (cheeseGrid[unoRow][j].getStatus().equals("vertical")) {
					special = 3;
					specRow = unoRow;
					specCol = j;
				} else if (cheeseGrid[unoRow][j].getStatus().equals("bomb")) {
					special = 4;
					specRow = unoRow;
					specCol = j;
				}
				if (j < cheeseGrid[0].length - 1) {
					if (horizCount >= 3 && !cheeseGrid[unoRow][j+1].equals(cheeseGrid[unoRow][j])) {
						startCol = j - (horizCount - 1);
						matchPair = "" + unoRow + startCol + horizCount + special + specRow + specCol;
						if (matchRows.indexOf(matchPair) == -1) {
							matchRows.add(matchPair);
						}
					}
				} else if (j == cheeseGrid[0].length - 1) {
					if (horizCount >= 3) {
						startCol = j - (horizCount - 1);
						matchPair = "" + unoRow + startCol + horizCount + special + specRow + specCol;
						if (matchRows.indexOf(matchPair) == -1) {
							matchRows.add(matchPair);
						}
					}
				}
			} else {
				horizCount = 1;
				startCheese = cheeseGrid[unoRow][j];
			}
		}
		
		// releaseRow
		horizCount = 0;
		startCheese = cheeseGrid[dosRow][0];
		startCol = 0;
		matchPair = "";
		special = 0;
		specRow = 0;
		specCol = 0;
		for (int j = 0; j < cheeseGrid[0].length; j++) {
			if (cheeseGrid[dosRow][j].equals(startCheese)) {
				horizCount++;
				if (cheeseGrid[dosRow][j].getStatus().equals("wrapped")) {
					special = 1;
					specRow = dosRow;
					specCol = j;
				} else if (cheeseGrid[dosRow][j].getStatus().equals("horizontal")) {
					special = 2;
					specRow = dosRow;
					specCol = j;
				} else if (cheeseGrid[dosRow][j].getStatus().equals("vertical")) {
					special = 3;
					specRow = dosRow;
					specCol = j;
				} else if (cheeseGrid[dosRow][j].getStatus().equals("bomb")) {
					special = 4;
					specRow = dosRow;
					specCol = j;
				}
				if (j < cheeseGrid[0].length - 1) {
					if (horizCount >= 3 && !cheeseGrid[dosRow][j+1].equals(cheeseGrid[dosRow][j])) {
						startCol = j - (horizCount - 1);
						matchPair = "" + dosRow + startCol + horizCount + special + specRow + specCol;
						if (matchRows.indexOf(matchPair) == -1) {
							matchRows.add(matchPair);
						}
					}
				} else if (j == cheeseGrid[0].length - 1) {
					if (horizCount >= 3) {
						startCol = j - (horizCount - 1);
						matchPair = "" + dosRow + startCol + horizCount + special + specRow + specCol;
						if (matchRows.indexOf(matchPair) == -1) {
							matchRows.add(matchPair);
						}
					}
				}
			} else {
				horizCount = 1;
				startCheese = cheeseGrid[dosRow][j];
			}
		}
		System.out.println("Rows with matches: " + matchRows);
		
		// clickCol
		vertCount = 0;
		startCheese = cheeseGrid[0][unoCol];
		System.out.println("Starting cheese: " + startCheese);
		startRow = 0;
		matchPair = "";
		special = 0;
		specRow = 0;
		specCol = 0;
		for (int i = 0; i < cheeseGrid.length; i++) {
			if (cheeseGrid[i][unoCol].equals(startCheese)) {
				vertCount++;
				System.out.println(vertCount);
				if (cheeseGrid[i][unoCol].getStatus().equals("wrapped")) {
					special = 1;
					specRow = i;
					specCol = unoCol;
				} else if (cheeseGrid[i][unoCol].getStatus().equals("horizontal")) {
					special = 2;
					specRow = i;
					specCol = unoCol;
				} else if (cheeseGrid[i][unoCol].getStatus().equals("vertical")) {
					special = 3;
					specRow = i;
					specCol = unoCol;
				} else if (cheeseGrid[i][unoCol].getStatus().equals("bomb")) {
					special = 4;
					specRow = i;
					specCol = unoCol;
				}
				if (i < cheeseGrid.length - 1) {
					if (vertCount >= 3 && !cheeseGrid[i+1][unoCol].equals(cheeseGrid[i][unoCol])) {
						startRow = i - (vertCount - 1);
						matchPair = "" + unoCol + startRow + vertCount + special + specRow + specCol;
						if (matchCols.indexOf(matchPair) == -1) {
							matchCols.add(matchPair);
						}
					}
				} else if (i == cheeseGrid.length - 1) {
					if (vertCount >= 3) {
						startRow = i - (vertCount - 1);
						matchPair = "" + unoCol + startRow + vertCount + special + specRow + specCol;
						if (matchCols.indexOf(matchPair) == -1) {
							matchCols.add(matchPair);
						}
					}
				}
			} else {
				vertCount = 1;
				startCheese = cheeseGrid[i][unoCol];
				System.out.println("Start cheese: " + startCheese);
			}
		}
		
		// releaseCol
		vertCount = 0;
		startCheese = cheeseGrid[0][dosCol];
		System.out.println("Starting cheese: " + startCheese);
		startRow = 0;
		matchPair = "";
		special = 0;
		specRow = 0;
		specCol = 0;
		for (int i = 0; i < cheeseGrid.length; i++) {
			if (cheeseGrid[i][dosCol].equals(startCheese)) {
				vertCount++;
				System.out.println(vertCount);
				if (cheeseGrid[i][dosCol].getStatus().equals("wrapped")) {
					special = 1;
					specRow = i;
					specCol = dosCol;
				} else if (cheeseGrid[i][dosCol].getStatus().equals("horizontal")) {
					special = 2;
					specRow = i;
					specCol = dosCol;
				} else if (cheeseGrid[i][dosCol].getStatus().equals("vertical")) {
					special = 3;
					specRow = i;
					specCol = dosCol;
				} else if (cheeseGrid[i][dosCol].getStatus().equals("bomb")) {
					special = 4;
					specRow = i;
					specCol = dosCol;
				}
				if (i < cheeseGrid.length - 1) {
					if (vertCount >= 3 && !cheeseGrid[i+1][dosCol].equals(cheeseGrid[i][dosCol])) {
						startRow = i - (vertCount - 1);
						matchPair = "" + dosCol + startRow + vertCount + special + specRow + specCol;
						if (matchRows.indexOf(matchPair) == -1) {
							matchRows.add(matchPair);
						}
					}
				} else if (i == cheeseGrid.length - 1) {
					if (vertCount >= 3) {
						startRow = i - (vertCount - 1);
						matchPair = "" + dosCol + startRow + vertCount + special + specRow + specCol;
						if (matchRows.indexOf(matchPair) == -1) {
							matchRows.add(matchPair);
						}
					}
				}
			} else {
				vertCount = 1;
				startCheese = cheeseGrid[i][dosCol];
				System.out.println("Start cheese: " + startCheese);
			}
		}
		System.out.println("Columns with matches: " + matchCols);
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
				}
			}
		}
	}
	
	private void clearBoard() {		
		// clear matches and change wine
		// special cheese activation
		for (String grp : matchRows) {
			if (Integer.parseInt(grp.substring(3,4)) != 0) {
				if (Integer.parseInt(grp.substring(3,4)) == 1) {
					cheeseGrid[Integer.parseInt(grp.substring(4,5))][Integer.parseInt(grp.substring(5,6))] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5))][Integer.parseInt(grp.substring(5,6)) + 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5))][Integer.parseInt(grp.substring(5,6)) - 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) + 1][Integer.parseInt(grp.substring(5,6))] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) - 1][Integer.parseInt(grp.substring(5,6))] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) + 1][Integer.parseInt(grp.substring(5,6)) + 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) + 1][Integer.parseInt(grp.substring(5,6)) - 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) - 1][Integer.parseInt(grp.substring(5,6)) + 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) - 1][Integer.parseInt(grp.substring(5,6)) - 1] = new Blank("blank");
					score += 240;
				}
				if (Integer.parseInt(grp.substring(3,4)) == 2) {
					for (int j = 0; j < cheeseGrid[0].length; j++) {
						cheeseGrid[Integer.parseInt(grp.substring(4,5))][j] = new Blank("blank");
					}
					score += 540;
				}
				if (Integer.parseInt(grp.substring(3,4)) == 3) {
					for (int i = 0; i < cheeseGrid.length; i++) {
						cheeseGrid[i][Integer.parseInt(grp.substring(5,6))] = new Blank("blank");
					}
					score += 540;
				}
				if (Integer.parseInt(grp.substring(3,4)) == 4) {
					String color = cheeseGrid[Integer.parseInt(grp.substring(4,5))][Integer.parseInt(grp.substring(5,6))].getColor();
					int count = 0;
					for (int i = 0; i < cheeseGrid.length; i++) {
						for (int j = 0; j < cheeseGrid[0].length; j++) {
							if (cheeseGrid[i][j].getColor().equals(color)) {
								count++;
								cheeseGrid[i][j] = new Blank("blank");
							}
						}
					}
					score += count * 60;
				}
			}
		}
		for (String grp : matchCols) {
			if (Integer.parseInt(grp.substring(3,4)) != 0) {
				if (Integer.parseInt(grp.substring(3,4)) == 1) {
					cheeseGrid[Integer.parseInt(grp.substring(4,5))][Integer.parseInt(grp.substring(5,6))] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5))][Integer.parseInt(grp.substring(5,6)) + 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5))][Integer.parseInt(grp.substring(5,6)) - 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) + 1][Integer.parseInt(grp.substring(5,6))] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) - 1][Integer.parseInt(grp.substring(5,6))] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) + 1][Integer.parseInt(grp.substring(5,6)) + 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) + 1][Integer.parseInt(grp.substring(5,6)) - 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) - 1][Integer.parseInt(grp.substring(5,6)) + 1] = new Blank("blank");
					cheeseGrid[Integer.parseInt(grp.substring(4,5)) - 1][Integer.parseInt(grp.substring(5,6)) - 1] = new Blank("blank");
					score += 240;
				}
				if (Integer.parseInt(grp.substring(3,4)) == 2) {
					for (int j = 0; j < cheeseGrid[0].length; j++) {
						cheeseGrid[Integer.parseInt(grp.substring(4,5))][j] = new Blank("blank");
					}
					score += 540;
				}
				if (Integer.parseInt(grp.substring(3,4)) == 3) {
					for (int i = 0; i < cheeseGrid.length; i++) {
						cheeseGrid[i][Integer.parseInt(grp.substring(5,6))] = new Blank("blank");
					}
					score += 540;
				}
				if (Integer.parseInt(grp.substring(3,4)) == 4) {
					String color = cheeseGrid[Integer.parseInt(grp.substring(4,5))][Integer.parseInt(grp.substring(5,6))].getColor();
					int count = 0;
					for (int i = 0; i < cheeseGrid.length; i++) {
						for (int j = 0; j < cheeseGrid[0].length; j++) {
							if (cheeseGrid[i][j].getColor().equals(color)) {
								count++;
								cheeseGrid[i][j] = new Blank("blank");
							}
						}
					}
					score += count * 60;
				}
			}
		}
		
		// wrapped cheeses
		for (String groo : matchRows) {
			for (String oop : matchCols) {
				if ((Integer.parseInt(groo.substring(1,2)) + (Integer.parseInt(groo.substring(2,3)) - 1) >= Integer.parseInt(oop.substring(0,1)) || Integer.parseInt(oop.substring(1,2)) + (Integer.parseInt(oop.substring(2,3)) - 1) >= Integer.parseInt(groo.substring(0,1))) && groo.substring(2,3).equals("3") && oop.substring(2,3).equals("3")) {
					String color = cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(oop.substring(0,1))].getColor();
					System.out.println(color);
					System.out.println(Integer.parseInt(groo.substring(0,1)) + "," + Integer.parseInt(oop.substring(0,1)));
					if (color.equals("red")) {
						cheeseGrid[releaseRow][releaseCol] = new Red("wrapped");
					} else if (color.equals("orange")) {
						cheeseGrid[releaseRow][releaseCol] = new Orange("wrapped");
					} else if (color.equals("yellow")) {
						cheeseGrid[releaseRow][releaseCol] = new Yellow("wrapped");
					} else if (color.equals("green")) {
						cheeseGrid[releaseRow][releaseCol] = new Green("wrapped");
					} else if (color.equals("blue")) { 
						cheeseGrid[releaseRow][releaseCol] = new Blue("wrapped");
					} else if (color.equals("purple")) {
						cheeseGrid[releaseRow][releaseCol] = new Purple("wrapped");
					}
					for (int k = 1; k < Integer.parseInt(groo.substring(2,3)); k++) {
						cheeseGrid[Integer.parseInt(groo.substring(0,1))][Integer.parseInt(groo.substring(1,2)) + k] = new Blank("blank");
					}
					for (int k = 1; k < Integer.parseInt(oop.substring(2,3)); k++) {
						cheeseGrid[Integer.parseInt(oop.substring(1,2)) + k][Integer.parseInt(oop.substring(0,1))] = new Blank("blank");

					}
					score += 200;
				}
			}
		}
		// rows for 3-5
		for (String grp : matchRows) {
			for (int k = 0; k < Integer.parseInt(grp.substring(2,3)); k++) {
//				String color = cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2))].getColor();
				if (cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2)) + k].getStatus().equals("bottle")) {
					wineY -= 50;
					wineHeight += 50;
				}
				repaint();
				if (wineHeight == 454) {
					game = false;
					gameOver();
				}
				if (Integer.parseInt(grp.substring(2,3)) == 4) {
					String color2 = cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2))].getColor();
					System.out.println("color2 is " + color2);
					if (color2.equals("red")) {
						cheeseGrid[releaseRow][releaseCol] = new Red("vertical");	
					} else if (color2.equals("orange")) {
						cheeseGrid[releaseRow][releaseCol] = new Orange("vertical");
					} else if (color2.equals("yellow")) {
						cheeseGrid[releaseRow][releaseCol] = new Yellow("vertical");
					} else if (color2.equals("green")) {
						cheeseGrid[releaseRow][releaseCol] = new Green("vertical");
					} else if (color2.equals("blue")) {
						cheeseGrid[releaseRow][releaseCol] = new Blue("vertical");
					} else if (color2.equals("purple")) {
						cheeseGrid[releaseRow][releaseCol] = new Purple("vertical");
					}
					score += 120;
				} else if (Integer.parseInt(grp.substring(2,3)) == 5) {
					cheeseGrid[releaseRow][releaseCol] = new Cheese("bomb");
					score += 280;
				}
				if (!cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2)) + k].getStatus().equals("wrapped") && !cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2)) + k].getStatus().equals("horizontal") && !cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2)) + k].getStatus().equals("vertical") && !cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2)) + k].getStatus().equals("bomb")) {
					cheeseGrid[Integer.parseInt(grp.substring(0,1))][Integer.parseInt(grp.substring(1,2)) + k] = new Blank("blank");
					score += 60;
				}
			}
		}
		// columns for 3-5
		for (String grp : matchCols) {
			for (int k = 0; k < Integer.parseInt(grp.substring(2,3)); k++) {
//				String color = cheeseGrid[Integer.parseInt(grp.substring(1,2))][Integer.parseInt(grp.substring(0,1))].getColor();
				if (cheeseGrid[Integer.parseInt(grp.substring(1,2)) + k][Integer.parseInt(grp.substring(0,1))].getStatus().equals("bottle")) {
					wineY -= 50;
					wineHeight += 50;
				}
				repaint();
				if (wineHeight == 454) {
					game = false;
					gameOver();
				}
				if (Integer.parseInt(grp.substring(2,3)) == 4) {
					String color2 = cheeseGrid[Integer.parseInt(grp.substring(1,2))][Integer.parseInt(grp.substring(0,1))].getColor();
					if (color2.equals("red")) {
						cheeseGrid[releaseRow][releaseCol] = new Red("horizontal");	
					} else if (color2.equals("orange")) {
						cheeseGrid[releaseRow][releaseCol] = new Orange("horizontal");
					} else if (color2.equals("yellow")) {
						cheeseGrid[releaseRow][releaseCol] = new Yellow("horizontal");
					} else if (color2.equals("green")) {
						cheeseGrid[releaseRow][releaseCol] = new Green("horizontal");
					} else if (color2.equals("blue")) {
						cheeseGrid[releaseRow][releaseCol] = new Blue("horizontal");
					} else if (color2.equals("purple")) {
						cheeseGrid[releaseRow][releaseCol] = new Purple("horizontal");
					}
					score += 120;
				} else if (Integer.parseInt(grp.substring(2,3)) == 5) {
					cheeseGrid[releaseRow][releaseCol] = new Cheese("bomb");
					score += 280;
				}
				if (!cheeseGrid[Integer.parseInt(grp.substring(1,2)) + k][Integer.parseInt(grp.substring(0,1))].getStatus().equals("wrapped") && !cheeseGrid[Integer.parseInt(grp.substring(1,2)) + k][Integer.parseInt(grp.substring(0,1))].getStatus().equals("horizontal") && !cheeseGrid[Integer.parseInt(grp.substring(1,2)) + k][Integer.parseInt(grp.substring(0,1))].getStatus().equals("vertical") && !cheeseGrid[Integer.parseInt(grp.substring(1,2)) + k][Integer.parseInt(grp.substring(0,1))].getStatus().equals("bomb")) {
					cheeseGrid[Integer.parseInt(grp.substring(1,2)) + k][Integer.parseInt(grp.substring(0,1))] = new Blank("blank");
					score += 60;
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
			winMsg = "yEEEET! Your score is " + score + "!";
			repaint();
		} else if (moves == 0) {
			winWidth = WIDTH;
			winHeight = HEIGHT;
			winMsg = "You lose! Press \"r\" to try again!";
		}
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
