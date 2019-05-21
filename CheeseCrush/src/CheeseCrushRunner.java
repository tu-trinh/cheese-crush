import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		
//		class CheeseMouseListener implements MouseListener {
//			public void mouseClicked(MouseEvent e) {
//			}
//
//			public void mousePressed(MouseEvent e) {
//				canvas.checkClick(e.getX(), e.getY());
//			}
//
//			public void mouseReleased(MouseEvent e) {				
//			}
//			
//			public void mouseEntered(MouseEvent e) {
//			}
//			
//			public void mouseExited(MouseEvent e) {
//			}	
//		}
//		canvas.addMouseListener(new CheeseMouseListener());
		
//		JPanel panel = new JPanel();
		
//		canvas.add(panel); // panel is inside the canvas
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}
