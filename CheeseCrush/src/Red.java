import java.awt.*;
import javax.swing.*;

public class Red extends Cheese {
	private Image img;
//	private int imgX;
//	private int imgY;
	
	public Red(String status) {
		super(status);
		if (status.equals("normal")) {
			img = new ImageIcon("redLeicester.png").getImage().getScaledInstance(450, 350, java.awt.Image.SCALE_SMOOTH);
		}
//		imgX = 100;
//		imgY = 100;
	}
	
	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		g.drawImage(img, imgX, imgY, this);
	}
}
