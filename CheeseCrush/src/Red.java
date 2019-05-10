import java.awt.*;
import javax.swing.*;

public class Red extends JComponent {
	private Image img;
	private int imgX;
	private int imgY;
	
	public Red() {
		img = new ImageIcon("redLeicester.jpg").getImage();
		imgX = 100;
		imgY = 100;
	}
	
	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		g.drawImage(img, imgX, imgY, this);
	}
}