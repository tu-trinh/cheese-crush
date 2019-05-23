import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

// current plan: create array of color objects all extending the cheese class. put this in the canvas. then draw it out in the paint component.
// https://mathbits.com/MathBits/Java/Graphics/Color.htm

// place bottles in correct locations
// place locks in correct locations
// combinations code
// score code
// reset
// shuffle
// animate
// music
// sparkles :P

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
		
		class CheeseMouseMotionListener implements MouseMotionListener {
			public void mouseDragged(MouseEvent e) {
			}
			
			public void mouseMoved(MouseEvent e) {
			}
		}
		canvas.addMouseMotionListener(new CheeseMouseMotionListener());
		
		class CheeseMouseListener implements MouseListener {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				canvas.checkPress(e.getX(), e.getY());
			}

			public void mouseReleased(MouseEvent e) {				
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}	
		}
		canvas.addMouseListener(new CheeseMouseListener());
		
		class ClickbaitKeyListener implements KeyListener {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'r') {
					canvas.reset();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		}
		canvas.addKeyListener(new ClickbaitKeyListener());
		canvas.setFocusable(true);
		canvas.requestFocus();
		
//		JPanel panel = new JPanel();
		
//		canvas.add(panel); // panel is inside the canvas
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}