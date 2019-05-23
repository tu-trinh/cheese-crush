import java.awt.*;
import javax.swing.*;

public class Blue extends Cheese {
	private Image img;
	
	public Blue(String status) {
		super(status);
		if (status.equals("normal")) {
			img = new ImageIcon("blueGorgonzola.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("blueBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("blueBottle.png").getImage();
		} else if (status.equals("striped")) {
			img = new ImageIcon("blueStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("blueWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
}