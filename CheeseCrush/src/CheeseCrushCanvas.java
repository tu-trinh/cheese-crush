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

public class CheeseCrushCanvas extends JComponent {
	private final int WIDTH;
	private final int HEIGHT;
	private Cheese[][] cheeseGrid;
	private int score;
	private Color grayBlue;
	private Color grayerBlue;
	private Color bluerGray;
	
	public CheeseCrushCanvas(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		score = 0;
		grayBlue = new Color(178, 201, 216);
		grayerBlue = new Color(178, 195, 206);
		bluerGray = new Color(148, 145, 255);
		cheeseGrid = new Cheese[9][9];
		fillGrid(cheeseGrid);
	}
	
	public void checkPress(int x, int y) {
		Cheese target = cheeseGrid[(x - 500)/50][(y - 50)/50];
		
	}
	
	public void reset() {
		score = 0;
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
		
		// score
		g.setColor(bluerGray);
		g.setFont(new Font("Dialog", Font.BOLD, 30));
		g.drawString("Score: " + score, 250, 290);
		
		// title
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50));
		g.drawString("Cheese Crush!!!", 545, 580);
		
		// cheese/grid
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
	
	// remember to make all the other cheeses !!
	private void fillGrid(Cheese[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = chooseRandom();
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				// vertical match (3)
				if (grid[i][j].equals(grid[i-1][j]) && grid[i][j].equals(grid[i+1][j])) {
					
				}
				// horizontal match (3)
				if (grid[i][j].equals(grid[i][j-1]) && grid[i][j].equals(grid[i][j+1])) {
					
				}
			}
		}
	}
	
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
