import java.awt.*;
import javax.swing.*;

public class Orange extends Cheese {
	private Image img;
	
	public Orange(String status) {
		super(status);
		if (status.equals("normal")) {
			img = new ImageIcon("orangeCheddar.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("orangeBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("orangeBottle.png").getImage();
		} else if (status.equals("striped")) {
			img = new ImageIcon("orangeStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("orangeWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
}