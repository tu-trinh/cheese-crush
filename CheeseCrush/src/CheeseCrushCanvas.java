// six cheeses: red leicester, orange cheddar, yellow camembert, green cherni vit, blue gorgonzola, purple ricotta
// normal combo 60 pt
// striped 120 pt
// wrapped 200 pt
// string cheese 120 pt
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
//	private Image img;
	private int imgX;
	private int imgY;
	private Color grayBlue;
	private Cheese[][] cheeseGrid;
	
	public CheeseCrushCanvas(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		// img = Color object;
		imgX = 100;
		imgY = 100;
		// somewhere to the right, then score to the left
		// 2D arrays of candies
		grayBlue = new Color(178, 201, 206);
	}
	
	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		g.setColor(grayBlue);
		// draw the background here
//		g.drawLine(50,50,150,150);
//		g.drawImage(img, imgX, imgY, this);
	}
}
