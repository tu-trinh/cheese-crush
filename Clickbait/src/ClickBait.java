import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class ClickBait {
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static int delay = 3000;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Click Bait");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClickbaitCanvas canvas = new ClickbaitCanvas(WIDTH, HEIGHT);
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.add(canvas);
		frame.pack();
		
		class TimerListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				canvas.moveImage();
			}
		}
		Timer t = new Timer(delay, new TimerListener());
		t.start();
		
		class ClickbaitMouseListener implements MouseListener {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				canvas.checkClick(e.getX(), e.getY());
			}

			public void mouseReleased(MouseEvent e) {				
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}	
		}
		canvas.addMouseListener(new ClickbaitMouseListener());
		
		class ClickbaitKeyListener implements KeyListener {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'r') {
					canvas.resetScore();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					System.out.println("Up arrow pressed!");
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		}
		canvas.addKeyListener(new ClickbaitKeyListener());
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		frame.setVisible(true);
	}
}