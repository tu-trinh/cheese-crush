import java.awt.*;
import javax.swing.*;

public class CheeseCrushRunner {
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Cheese Crush!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CheeseCrushCanvas canvas = new CheeseCrushCanvas(WIDTH, HEIGHT);
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}