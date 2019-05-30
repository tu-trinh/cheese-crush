import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class ClickbaitCanvas extends JComponent {
	private final int WIDTH;
	private final int HEIGHT;
	private Image im;
	private int imgX;
	private int imgY;
	private int score;

	public ClickbaitCanvas(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		im = new ImageIcon("gouda.jpg").getImage();
//		imgX = 100;
//		imgY = 250;
		moveImage();
		score = 0;
	}
	
	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		g.drawImage(im, imgX, imgY, this);
		g.drawString("Score: " + score, 10, 20);
	}
	
	public void moveImage() {
		imgX = (int)(Math.random() * (WIDTH - im.getWidth(this)));
		imgY = (int)(Math.random() * (HEIGHT - im.getHeight(this)));
		repaint();
	}
	
	public void checkClick(int x, int y) {
		if (x >= imgX && x <= imgX + im.getWidth(this) && y >= imgY && y <= imgY + im.getHeight(this)) {
			score++;
			repaint();
		}
	}
	
	public void resetScore() {
		score = 0;
		repaint();
	}
}