import java.awt.*;
import javax.swing.*;

// current plan: create array of color objects all extending the cheese class. put this in the canvas. then draw it out in the paint component.
// https://mathbits.com/MathBits/Java/Graphics/Color.htm

public class CheeseCrushRunner {
	private static final int WIDTH = 1240; //1280
	private static final int HEIGHT = 640; //720
	
	public static void main(String[] args) {
		// frame is for the JFrame
		JFrame frame = new JFrame("Cheese Crush!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// canvas is to enable animations
		CheeseCrushCanvas canvas = new CheeseCrushCanvas(WIDTH, HEIGHT);
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// panel is for literally the entire game play
		JPanel panel = new JPanel();
		
		canvas.add(panel); // panel is inside the canvas
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}
